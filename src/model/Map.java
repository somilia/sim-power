package model;

import patterns.Observable;

public class Map extends Observable implements Runnable{

    public final int NB_BOX_X = 32;
    public final int NB_BOX_Y = 18;
    public static final int UPDATE_DATA_FREQUENCY = 5;



    private int population;
    private int populationAvailable;
    private int populationMax;
    private int energyProduced;
    private double energyPrice;
    private double pollutionRate;///< le taux de pollution correspond à la proportion d'élétricité venant d'energies fossiles
    private int userMoney;


    private Box[][] boxList;



    public Map() {

        population = 0;
        energyProduced = 0;
        energyPrice = 0;
        pollutionRate = 0;

        boxList = new Box[NB_BOX_X][NB_BOX_Y];
        for(int i=0;i<NB_BOX_X;i++){
            for (int j=0;j<NB_BOX_Y;j++){
                boxList[i][j] = new Box();
            }
        }

    }

    private void updatePopulationAvailable(){

        if(pollutionRate<65 && energyPrice < 0.75){
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
        this.energyPrice = population/energyProduced;
        //TODO
    }
    private void updatePollutionRate(double nbOfFossilEnergy, double nbOfRenewableEnergy){
        this.pollutionRate = nbOfFossilEnergy/(nbOfFossilEnergy+nbOfRenewableEnergy);
        //TODO
    }



    public void buildBuilding() {
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

        int sumEnergyProduced=0;

        double nbOfFossilEnergy =0;
        double nbOfRenewableEnergy=0;

        for (Box[] array :boxList) {
            for (Box box:array) {
                if (box.getContainHome()){
                    userMoney += box.getHome().genMoney();
                }
                if(box.getContainEnergySource()){
                    sumEnergyProduced+=box.getEnergySource().getElecricityProduced();
                    if(box.getEnergySource().getType()==BuildingType.FOSSIL){
                        nbOfFossilEnergy++;
                    }
                    else if(box.getEnergySource().getType()==BuildingType.RENEWABLE){
                        nbOfRenewableEnergy++;
                    }
                }
            }
        }
        this.energyProduced = sumEnergyProduced;

        updatePopulationAvailable();
        updatePopulation();

        updateEnergyPrice();
        updatePollutionRate(nbOfFossilEnergy, nbOfRenewableEnergy);

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
