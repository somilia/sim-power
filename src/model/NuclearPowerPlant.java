package model;

public class NuclearPowerPlant extends FossilEnergy {

    public static final double MEAN = 180.0;

    public NuclearPowerPlant(Box baseBox) {
        super(baseBox);
    }

    @Override
    public double genElectricity() {
        return MEAN;
    }
}
