package model;

public class Box
{
    private int wind;
    private int sun;
    private int water;

    private boolean gas;
    private boolean coal;
    private boolean uranium;

    private boolean containBuilding;
    private  BuildingType buildingType;


    public Box(){
        containBuilding=false;
    }

}