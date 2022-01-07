package model;

/**
 * Classe qui hérite de FossilEnergy et qui modélise un bâtiment de type centrale à charbon
 */
public class CoalPowerPlant extends FossilEnergy {

    public static final double MEAN = 120.0;//Valeur de la production d'énergie qui est constante

    public CoalPowerPlant(Box baseBox) {
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
