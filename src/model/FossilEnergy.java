package model;

public class FossilEnergy extends EnergySources {

    protected int pollutionRate;

    public FossilEnergy(Box baseBox) {
        super(baseBox);
        this.type = BuildingType.FOSSIL;
    }

    public void genElectricity() {
    }


}
