package model;

import patterns.Observable;
import patterns.Observer;

import javax.xml.transform.stream.StreamSource;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map extends Observable implements Runnable{

    public static final int NB_BOX_X = 32;
    public static final int NB_BOX_Y = 18;
    public static final int UPDATE_DATA_FREQUENCY = 5;
    public static final int UPDATE_USER_MONEY_FREQUENCY = 10;
    public static final int UPDATE_POPULATION_AVAILABLE_FEQUENCY = 2*60;
    public static final int STARTING_AMOUNT = 10000;
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
        int x_uranium = xr_uranium.nextInt(32 - 20 + 1) + 20;
        Random yr_uranium = new Random();
        int y_uranium = yr_uranium.nextInt(16- 2 + 1) + 2;


        for(int i=0;i<NB_BOX_X;i++){
            for(int j=0;j<NB_BOX_Y;j++){
                boxList[i][j] = new Box(NB_BOX_Y-j, 1+j, 0, randomFossilGen(i,j,x_gas,y_gas), randomFossilGen(i,j,x_coal,y_coal), randomFossilGen(i,j,x_uranium,y_uranium)); //public Box(double wind, double sun, double water, boolean gas, boolean coal, boolean uranium){}


            }
        }

        this.observerList = new ArrayList<>();

    }
    public boolean randomFossilGen(int i, int j, int x, int y) {
        if(((i+2==x)||(i-2==x) || (i+1==x)||(i-1==x) || (i==x)) && ((j+2==y)||(j-2==y) || (j+1==y)||(j-1==y) || (j==y)) && !(((i+2==x)&&(j+2==y)||(i-2==x)&&(j-2==y)||(i+2==x)&&(j-2==y)||(i-2==x)&&(j+2==y)))){
            return true;
        }else {return false;}
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

        if(pollutionRate<75 && energyPrice < 0.75 && energyPrice!=0 && pollutionRate!=0){
            double newPopulationAvailable = 1/(energyPrice*pollutionRate*4);
            populationAvailable += (int) newPopulationAvailable;
        }
        //TODO
    }

    private void addPopulationMax(int n){
        this.populationMax+=n;
    }

    /**
     * Permet de mettre a jour la population
     * Si il y a de la population disponible, on parcours les Home de la map et si elle ne sont pas remplies, on les remplis jusqu'à ce qu'elles soient pleines ou qu'il n'y est plus de population disponible
     */
    public void updatePopulation(){
        int i=0;
        int j=0;

        while(population<populationMax && populationAvailable>0 && i<NB_BOX_X && j<NB_BOX_Y){

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
            i++;
            j++;
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

        boxList[posX][posY].addBuilding(type);//TODO try catch ?
        userMoney-= type.price;

        if(type == BuildingType.HOUSE || type==BuildingType.APPARTEMENT){
            populationMax += boxList[posX][posY].getHome().getMaxNbOfHabitants();
            System.out.println("populationMax "+populationMax);
            updatePopulation();
            System.out.println("population" +population);
            updateEnergyPrice();
            notifyObservers();
        }
        else if(type==BuildingType.COAL || type==BuildingType.GAS || type==BuildingType.NUCLEAR ){
            this.nbOfFossilEnergy++;
            System.out.println("Build : nb of energy fossil : "+nbOfFossilEnergy);
            updatePollutionRate();
            notifyObservers();
        }
        else if(type== BuildingType.SOLAR || type==BuildingType.WIND || type==BuildingType.WATER){
            this.nbOfRenewableEnergy++;
            System.out.println("Build : nb of energy renewable : "+nbOfRenewableEnergy);
            updatePollutionRate();
            notifyObservers();
        }

        System.out.println("builbuilding");
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

        updatePopulationAvailable();
        updatePopulation();
        updateUserMoney();
        updateEnergyProduced();
        updateEnergyPrice();
        updatePollutionRate();


    }



    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        while(true){

            updateBoxData();
            updateGlobalData();
            notifyObservers();

            try {
                Thread.sleep(UPDATE_DATA_FREQUENCY*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
