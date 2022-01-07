package model;

/**
 * Classe qui hérite de FossilEnergy et qui modélise une centrale à gaz
 */
public class GasPowerPlant extends FossilEnergy {

    public static final double MEAN = 80.0;

    public GasPowerPlant(Box baseBox) {
        super(baseBox);
    }

    /**
     * simule la production d'énergie qui est constante
     */
    @Override
    public void genElectricity() {
        this.elecricityProduced = MEAN;
    }
}
