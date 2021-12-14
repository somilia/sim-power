package model;

public class CoalPowerPlant extends FossilEnergy {

    public static final double MEAN = 120.0;

    public CoalPowerPlant(Box baseBox) {
        super(baseBox);
    }

    @Override
    public void genElectricity() {
        this.elecricityProduced = MEAN;
    }


}
