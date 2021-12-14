package model;

import java.sql.SQLOutput;
import java.util.Random;

public class Box
{
    private static final double MEAN = 0.0;
    private static final double STANDARD_DERIVATION = 0.2;

    private double wind;
    private double sun;
    private double water;

    private boolean gas;
    private boolean coal;
    private boolean uranium;

    private boolean containEnergySource;
    private boolean containHome;

    private EnergySources energySource;
    private Home home;


    public Box(){
        wind = 2;
        sun = 2;
        water = 2;

        containEnergySource=false;
        containHome = false;
        //TODO
    }

    public Box(double wind, double sun, double water, boolean gas, boolean coal, boolean uranium){}

    public void addBuilding(BuildingType type){
        switch (type){
            case SOLAR :
                System.out.println("switch solar contain energy is true");
                this.energySource = new SolarPanel(this);
                this.containEnergySource = true;
                break;
            case WIND:
                this.energySource = new WindTurbine(this);
                this.containEnergySource = true;
                break;
            case WATER:
                this.energySource = new HydrauPowerPlant(this);
                this.containEnergySource = true;
                break;
            case NUCLEAR:
                this.energySource = new NuclearPowerPlant(this);
                this.containEnergySource = true;
                break;
            case GAS:
                this.energySource = new GasPowerPlant(this);
                this.containEnergySource = true;
                break;
            case COAL:
                this.energySource = new CoalPowerPlant(this);
                this.containEnergySource = true;
                break;
            case HOUSE:
                this.home = new House();
                this.containHome = true;
                break;
            case APPARTEMENT:
                this.home = new AppartementBuilding();
                this.containHome = true;
            default:
                throw new IllegalArgumentException("Building type for energySource not valid");
        }
        System.out.println("addbuilding");
    }

    public void randomWindGen() {
        Random r = new Random();
        wind += r.nextGaussian()* STANDARD_DERIVATION +MEAN;
    }

    public void randomSunGen() {
        Random r = new Random();
        sun += r.nextGaussian()* STANDARD_DERIVATION +MEAN;
    }

    public void randomWaterGen() {
        if(water!=0){                   //si water = 0 alors il n'y a pas d"eau sur la case donc pas besoin de la faire varier
            Random r = new Random();
            water += r.nextGaussian()* STANDARD_DERIVATION +MEAN;
        }
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

    public double getWind() {
        return wind;
    }

    public double getSun() {
        return sun;
    }

    public double getWater() {
        return water;
    }

    public boolean isGas() {
        return gas;
    }

    public boolean isCoal() {
        return coal;
    }

    public boolean isUranium() {
        return uranium;
    }
}