package model;

public class EnergySources extends Building {

    protected int elecricityProduced;
    protected  Box baseBox;
    protected BuildingType type;

    public EnergySources(Box baseBox) {
        this.baseBox = baseBox;
    }

    public void genElectricity() {
    }

    public int getElecricityProduced() {
        return elecricityProduced;
    }

    public BuildingType getType() {
        return type;
    }
}
