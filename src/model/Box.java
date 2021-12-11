package model;

public class Box
{
    private int wind;
    private int sun;
    private int water;

    private boolean gas;
    private boolean coal;
    private boolean uranium;

    private boolean containEnergySource;
    private boolean containHome;

    private EnergySources energySource;
    private Home home;


    public Box(){
        containEnergySource=false;
        containHome = false;
    }

    public void randomWindGen() {
    }

    public void randomSunGen() {
    }

    public void randomWaterGen() {
    }

    public boolean getContainEnergySource(){
        return this.containEnergySource;
    }

    public boolean getContainHome(){
        return this.containHome;
    }

    public EnergySources getEnergySource() {
        return energySource;
    }

    public Home getHome() {
        return home;
    }
}