package model;

public class HydrauPowerPlant extends RenewableEnergy {

    private static final double MEAN = 140.0;

    public HydrauPowerPlant(Box baseBox) {
        super(baseBox);
    }

    @Override
    public double genElectricity() {
        return MEAN* baseBox.getWater();
    }
}
