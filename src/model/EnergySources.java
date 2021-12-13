package model;

public abstract class EnergySources extends Building {

    protected int elecricityProduced;
    protected  Box baseBox;
    protected BuildingType type;

    public EnergySources(Box baseBox) {
        this.baseBox = baseBox;
    }

    public abstract double genElectricity();

    public int getElecricityProduced() {
        return elecricityProduced;
    }

    public BuildingType getType() {
        return type;
    }
}
