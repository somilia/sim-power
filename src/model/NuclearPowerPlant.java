package model;

/**
 * Classe qui hérite de FossilEnergy et qui modélise un bâtiment de type centrale nucléaire
 */
public class NuclearPowerPlant extends FossilEnergy {

    public static final double MEAN = 180.0;//< valeur de la production d'énergie constante

    public NuclearPowerPlant(Box baseBox) {
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
