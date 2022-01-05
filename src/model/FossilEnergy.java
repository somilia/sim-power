package model;

public abstract class FossilEnergy extends EnergySources {

    public FossilEnergy(Box baseBox) {
        super(baseBox);
        this.type = BuildingType.FOSSIL;
    }

    public abstract void genElectricity();



}
