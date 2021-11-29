package model;

public class Map {
    public final int NB_BOX_X = 50;
    public final int NB_BOX_Y = 50;

    private int nbOfHabitants;
    private int energyProduced;
    private float energyPrice;
    private float pollutionRate;

    private Box[][] boxList;
    private int userMoney;


    public Map() {

        nbOfHabitants = 0;
        energyProduced = 0;
        energyPrice = 0;
        pollutionRate = 0;

        boxList = new Box[NB_BOX_X][NB_BOX_Y];

    }

    public void randomWindGen() {
    }

    public void randomSunGen() {
    }

    public void randomWaterGen() {
    }

    public void buildBuilding() {
    }

    public void updateData() {
    }


}
