package model;

public abstract class EnergySources extends Building {

    protected double elecricityProduced;
    protected  Box baseBox;
    protected BuildingType type;

    public EnergySources(Box baseBox) {
        this.baseBox = baseBox;
    }

    public abstract void genElectricity();

    public double getElecricityProduced() {
        return elecricityProduced;
    }

    public BuildingType getType() {
        return type;
    }
}
