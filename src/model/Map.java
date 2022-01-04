package model;

import patterns.Observable;
import patterns.Observer;

import java.util.ArrayList;
import java.util.Random;

public class Map extends Observable implements Runnable{

    public static final int NB_BOX_X = 32;
    public static final int NB_BOX_Y = 18;
    public static final int BOUNDS_X= NB_BOX_X /4;
    public static final int BOUNDS_Y= NB_BOX_X /4;

    public static final long UPDATE_BOX_DATA_FREQUENCY = 5*1000;
    public static final long UPDATE_GLOBAL_DATA = 5*1000;
    public static final long UPDATE_USER_MONEY_FREQUENCY = 10*1000;
    public static final long UPDATE_POPULATION_AVAILABLE_FREQUENCY = 2*1000;

    public static final int STARTING_AMOUNT = 100000;
    public static final int STARTING_POPULATION_AVAILABLE = 5;


    private int population;
    private int populationAvailable;
    private int populationMax;
    private double energyProduced;
    private double energyPrice;
    private double pollutionRate;///< le taux de pollution correspond à la proportion d'élétricité venant d'energies fossiles
    private int userMoney;

    private double nbOfFossilEnergy;
    private double nbOfRenewableEnergy;

    private Box[][] boxList;



    public Map() {

        population = 0;
        populationAvailable = STARTING_POPULATION_AVAILABLE;
        populationMax=0;
        energyProduced = 0.0;
        energyPrice = 0.0;
        pollutionRate = 0.0;
        userMoney = STARTING_AMOUNT;
        nbOfFossilEnergy=0.0;
        nbOfRenewableEnergy = 0.0;


        boxList = new Box[NB_BOX_X][NB_BOX_Y];
        Random xr_gas = new Random();
        int x_gas = xr_gas.nextInt(9 - 2 + 1) + 2;   //r.nextInt((max - min) + 1) + min;
        Random yr_gas = new Random();
        int y_gas = yr_gas.nextInt(16- 2 + 1) + 2;

        Random xr_coal = new Random();
        int x_coal = xr_coal.nextInt(19 - 10 + 1) + 10;
        Random yr_coal = new Random();
        int y_coal = yr_coal.nextInt(16- 2 + 1) + 2;

        Random xr_uranium = new Random();
        int x_uranium = xr_uranium.nextInt(30 - 20 + 1) + 20;
        Random yr_uranium = new Random();
        int y_uranium = yr_uranium.nextInt(16- 2 + 1) + 2;


        for(int i=0;i<NB_BOX_X;i++){
            for(int j=0;j<NB_BOX_Y;j++){
                boxList[i][j] = new Box(NB_BOX_Y-j, 1+j, 0, randomFossilGen(i,j,x_gas,y_gas), randomFossilGen(i,j,x_coal,y_coal), randomFossilGen(i,j,x_uranium,y_uranium)); //public Box(double wind, double sun, double water, boolean gas, boolean coal, boolean uranium){}
            }
        }

        randomStartRiverGeneration();

        this.observerList = new ArrayList<>();

    }


/*
    public void randomFossilGen() {
        Random x = new Random();
        Random y = new Random();
        for(int i=0;i<NB_BOX_X;i++){
            for (int j=0;j<NB_BOX_Y;j++){
                boxList[i][j] = new Box(j, NB_BOX_Y-j, 1, true, true, true); //public Box(double wind, double sun, double water, boolean gas, boolean coal, boolean uranium){}


            }
        }
        sun += r.nextGaussian()* STANDARD_DERIVATION +MEAN;
    }
*/
    @Override
    public void notifyObservers() {
        for (Observer obs: observerList) {
            obs.update(population, populationAvailable, populationMax, energyProduced, energyPrice, pollutionRate, userMoney);
        }
    }

    private void updatePopulationAvailable(){
        if(pollutionRate<0.75 && energyPrice < 0.75 && energyPrice>0){//avant il y avait pollution rate!=0 jsp pk
            double newPopulationAvailable = 1/(energyPrice*pollutionRate*4);
            if(newPopulationAvailable>5){
                populationAvailable+=5;
            }
            else{
                populationAvailable += (int) newPopulationAvailable;
            }
        }
    }

    private void addPopulationMax(int n){
        this.populationMax+=n;
    }

    /**
     * Permet de mettre a jour la population
     * Si il y a de la population disponible, on parcours les Home de la map et si elle ne sont pas remplies, on les remplis jusqu'à ce qu'elles soient pleines ou qu'il n'y est plus de population disponible
     */
    public void updatePopulation(){

        for(int i=0; i<NB_BOX_X && population<populationMax && populationAvailable>0;i++){
            for(int j=0;population<populationMax && populationAvailable>0 && j<NB_BOX_Y; j++){

                if (boxList[i][j].getContainHome()){
                    int freeSpace = boxList[i][j].getHome().getMaxNbOfHabitants() - boxList[i][j].getHome().getNbOfHabitants();
                    if(freeSpace>0){
                        if(populationAvailable>freeSpace){
                            boxList[i][j].getHome().addHabitants(freeSpace);
                            populationAvailable-= freeSpace;
                            population+=freeSpace;
                        }else{
                            boxList[i][j].getHome().addHabitants(populationAvailable);
                            population+=populationAvailable;
                            populationAvailable=0;
                        }
                    }
                }
            }
        }
    }


    private void updateEnergyPrice(){
        if(energyProduced==0.0)
            this.energyPrice = 0.0;
        else
            this.energyPrice = (population/(energyProduced))%2;
    }

    private void updatePollutionRate(){
        if(nbOfFossilEnergy==0 && nbOfRenewableEnergy==0)
            this.pollutionRate = 0.0;
        else
            this.pollutionRate = nbOfFossilEnergy/(nbOfFossilEnergy+nbOfRenewableEnergy);
        //TODO change into calculate the ration between the energy produced by fossil and renewable
    }

    private void updateUserMoney(){

        for (Box[] array :boxList) {
            for (Box box:array) {
                if (box.getContainHome()){
                    userMoney += box.getHome().genMoney();
                }
            }
        }
    }

    private void updateEnergyProduced(){

        double sumEnergyProduced=0;

        for (Box[] array :boxList) {
            for (Box box:array) {
                if(box.getContainEnergySource()){
                    box.getEnergySource().genElectricity();
                    sumEnergyProduced+=box.getEnergySource().getElecricityProduced();
                }
            }
        }
        this.energyProduced = sumEnergyProduced;
    }



    public boolean hasEnoughMoney(BuildingType type){
        return type.price <= userMoney;
    }

    public boolean boxIsEmpty(int posX, int posY){

        boolean isEmpty = false;
        try{
            isEmpty = !boxList[posX][posY].getContainHome() || !boxList[posX][posY].getContainEnergySource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isEmpty;
    }

    public void buildBuilding(int posX, int posY, BuildingType type) {
        System.out.println("type : "+type);
        boxList[posX][posY].addBuilding(type);//TODO try catch ?
        userMoney-= type.price;

        if(type == BuildingType.HOUSE || type==BuildingType.APPARTEMENT){
            populationMax += boxList[posX][posY].getHome().getMaxNbOfHabitants();
            updatePopulation();
            updateEnergyPrice();
            notifyObservers();
        }
        else if(type==BuildingType.COAL || type==BuildingType.GAS || type==BuildingType.NUCLEAR ){
            this.nbOfFossilEnergy++;
            updatePollutionRate();
            notifyObservers();
        }
        else if(type== BuildingType.SOLAR || type==BuildingType.WIND || type==BuildingType.WATER){
            this.nbOfRenewableEnergy++;
            updatePollutionRate();
            notifyObservers();
        }
    }

    public void updateBoxData() {
        for (Box[] array :boxList) {
            for (Box box:array) {
                box.randomWindGen();
                box.randomWaterGen();
                box.randomSunGen();
            }
        }
    }

    private void updateGlobalData(){

        //updatePopulationAvailable();
        updatePopulation();
        //updateUserMoney();
        updateEnergyProduced();
        updateEnergyPrice();
        updatePollutionRate();


    }




    @Override
    public void run() {

        long boxLastUpdate=System.currentTimeMillis();
        long userMoneyLastUpdate=System.currentTimeMillis();
        long populationAvailableLastUpdate=System.currentTimeMillis();
        long globalDataLastUpdate=System.currentTimeMillis();

        while(true){

            if(System.currentTimeMillis()-boxLastUpdate >= UPDATE_BOX_DATA_FREQUENCY) {
                updateBoxData();
                notifyObservers();
                boxLastUpdate=System.currentTimeMillis();
            }
            if(System.currentTimeMillis()-userMoneyLastUpdate >= UPDATE_USER_MONEY_FREQUENCY) {
                updateUserMoney();
                notifyObservers();
                userMoneyLastUpdate=System.currentTimeMillis();
            }
            if(System.currentTimeMillis()-populationAvailableLastUpdate>= UPDATE_POPULATION_AVAILABLE_FREQUENCY) {
                updatePopulationAvailable();
                notifyObservers();
                populationAvailableLastUpdate=System.currentTimeMillis();
            }
            if(System.currentTimeMillis()-globalDataLastUpdate >= UPDATE_GLOBAL_DATA) {
                updateGlobalData();
                notifyObservers();
                globalDataLastUpdate=System.currentTimeMillis();
            }
            /*try {
                Thread.sleep(UPDATE_BOX_DATA_FREQUENCY *1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }


    public boolean canBuildOnBox(int positionX, int positionY, BuildingType buildingType){

        return switch (buildingType) {
            case SOLAR, WIND,HOUSE,APPARTEMENT -> boxIsEmpty(positionX, positionY);
            case WATER -> boxIsEmpty(positionX,positionY) && boxList[positionX][positionY].getWater()>0;
            case COAL -> boxIsEmpty(positionX, positionY) && boxList[positionX][positionY].hasCoal();
            case GAS -> boxIsEmpty(positionX, positionY) && boxList[positionX][positionY].hasGas();
            case NUCLEAR -> boxIsEmpty(positionX, positionY) && boxList[positionX][positionY].hasUranium();
            default -> throw new IllegalArgumentException("BuildingType not found");
        };
    }

    public boolean randomFossilGen(int i, int j, int x, int y) {
        if(((i+2==x)||(i-2==x) || (i+1==x)||(i-1==x) || (i==x)) && ((j+2==y)||(j-2==y) || (j+1==y)||(j-1==y) || (j==y)) && !(((i+2==x)&&(j+2==y)||(i-2==x)&&(j-2==y)||(i+2==x)&&(j-2==y)||(i-2==x)&&(j+2==y)))){
            return true;
        }else {return false;}
    }

    private void randomStartRiverGeneration(){
        int startingPosX;
        int startingPosY;

        Random r = new Random();
        int a = r.nextInt(4);

        if(a==0){
            Random s0 = new Random();
            startingPosX= s0.nextInt(NB_BOX_X-BOUNDS_X*2)+BOUNDS_X-1;
            startingPosY=0;
            for(startingPosY=0;startingPosY<5;startingPosY++){
                boxList[startingPosX][startingPosY].setWater(1.0);
            }

            randomRiverGeneration(0.5, 0.8, startingPosX, startingPosY);
        }
        else if(a==1){
            Random s1 = new Random();
            startingPosX= s1.nextInt(NB_BOX_X-BOUNDS_X*2)+BOUNDS_X-1;
            startingPosY=NB_BOX_Y-1;
            for(startingPosY=NB_BOX_Y-1;startingPosY>NB_BOX_Y-5;startingPosY--){
                boxList[startingPosX][startingPosY].setWater(1.0);
            }

            randomRiverGeneration(0.5, 0.2, startingPosX, startingPosY);
        }
        else if(a==2){
            Random s2 = new Random();
            int b2 = s2.nextInt(NB_BOX_Y-BOUNDS_Y*2)+BOUNDS_Y-1;
            startingPosX=NB_BOX_X-1;
            startingPosY=b2;
            for(startingPosX=NB_BOX_X-1;startingPosX>NB_BOX_X-5;startingPosX--){
                boxList[startingPosX][startingPosY].setWater(1.0);
            }

            randomRiverGeneration(0.2, 0.5, startingPosX, startingPosY);
        }
        else if(a==3){
            Random s3 = new Random();
            int b3 = s3.nextInt(NB_BOX_Y-BOUNDS_Y*2)+BOUNDS_Y-1;
            startingPosX=0;
            startingPosY=b3;
            for(startingPosX=0;startingPosX<5;startingPosX++){
                boxList[startingPosX][startingPosY].setWater(1.0);
            }

            randomRiverGeneration(0.8, 0.5, startingPosX, startingPosY);


        }
    }

    private void randomRiverGeneration(double probXPlus, double probYPlus, int x, int y){
        if(x>=0 && x<NB_BOX_X && y>=0 && y<NB_BOX_Y){
            boxList[x][y].setWater(1.0);

            Random rand0 = new Random();
            double a = rand0.nextDouble();
            if(a<0.5) {

                Random rand1 = new Random();
                if (rand1.nextDouble() < probXPlus)
                    x++;
                else
                    x--;
            }
            else{

                Random rand2 = new Random();
                if(rand2.nextDouble()<probYPlus)
                    y++;
                else
                    y--;
            }

            /*if(a<=(double)1/NB_BOX_X){
                randomRiverGeneration(probXPlus, probYPlus, ++x, ++y);
            }*/
            randomRiverGeneration(probXPlus, probYPlus, x, y);
        }
    }

    public Box[][] getBoxList() { return boxList; }

    public int getPopulation() {
        return population;
    }

    public int getPopulationAvailable() {
        return populationAvailable;
    }

    public double getEnergyProduced() {
        return energyProduced;
    }

    public double getEnergyPrice() {
        return energyPrice;
    }

    public double getPollutionRate() {
        return pollutionRate;
    }

    public int getUserMoney() {
        return userMoney;
    }


}
