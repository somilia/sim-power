package model;

public class GasPowerPlant extends FossilEnergy {

    public static final double MEAN = 80.0;

    public GasPowerPlant(Box baseBox) {
        super(baseBox);
    }

    @Override
    public double genElectricity() {
        return MEAN;
    }
}
