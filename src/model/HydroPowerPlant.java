package model;

public class HydroPowerPlant extends RenewableEnergy {

    private static final double MEAN = 140.0;

    public HydroPowerPlant(Box baseBox) {
        super(baseBox);
    }

    @Override
    public void genElectricity() {
        this.elecricityProduced = MEAN* baseBox.getWater();
    }
}
