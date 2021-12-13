package model;

public abstract class FossilEnergy extends EnergySources {

    protected int pollutionRate;

    public FossilEnergy(Box baseBox) {
        super(baseBox);
        this.type = BuildingType.FOSSIL;
    }

    public abstract double genElectricity();



}
