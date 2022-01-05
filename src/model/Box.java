package model;

import java.util.Random;

public class Box
{
    private static final double MEAN = 0.0;// la moyenne de la variation est à 0 c'est à dire que la valeur moyenne de la production d'éléctricité est celle donnée à l'initialisation
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

    public Box(double _wind, double _sun, double _water, boolean _gas, boolean _coal, boolean _uranium){
        wind = _wind;
        sun = _sun;
        water = _water;
        gas = _gas;
        coal = _coal;
        uranium = _uranium;
    }

    public void addBuilding(BuildingType type){
        switch (type) {
            case SOLAR -> {
                this.energySource = new SolarPanel(this);
                this.containEnergySource = true;
            }
            case WIND -> {
                this.energySource = new WindTurbine(this);
                this.containEnergySource = true;
            }
            case WATER -> {
                this.energySource = new HydroPowerPlant(this);
                this.containEnergySource = true;
            }
            case NUCLEAR -> {
                this.energySource = new NuclearPowerPlant(this);
                this.containEnergySource = true;
            }
            case GAS -> {
                this.energySource = new GasPowerPlant(this);
                this.containEnergySource = true;
            }
            case COAL -> {
                this.energySource = new CoalPowerPlant(this);
                this.containEnergySource = true;
            }
            case HOUSE -> {
                this.home = new House();
                this.containHome = true;
            }
            case APPARTEMENT -> {
                this.home = new AppartementBuilding();
                this.containHome = true;
            }
            default -> throw new IllegalArgumentException("Building type for energySource not valid");
        }
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
        if (water != 0) {                   //si water = 0 alors il n'y a pas d"eau sur la case donc pas besoin de la faire varier
            Random r = new Random();
            water += r.nextGaussian() * STANDARD_DERIVATION + MEAN;
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

    public boolean hasGas() {
        return gas;
    }

    public boolean hasCoal() {
        return coal;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public boolean hasUranium() {
        return uranium;
    }
}