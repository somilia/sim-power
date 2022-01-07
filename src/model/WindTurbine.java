package model;

/**
 * classe qui hérite de RenewableEnergy et qui modélise une éolienne
 */
public class WindTurbine extends RenewableEnergy {

    public static final double MEAN = 30.0;

    public WindTurbine(Box baseBox) {
        super(baseBox);
    }

    @Override
    public void genElectricity() {
        this.elecricityProduced = MEAN* baseBox.getWind();
    }
}
