package model;

/**
 * représente un bâtiment de type source d'énergie
 * Cette classe est abstraite car on ne doit pas l'instancier et on doit override la méthode genElectricity() pour chaque classe fille
 */
public abstract class EnergySources {

    protected double elecricityProduced;
    protected  Box baseBox;//Box à laquelle appartient ce bâtiment
    protected BuildingType type;

    public EnergySources(Box baseBox) {
        this.baseBox = baseBox;
    }

    /**
     * simule la production d'énergie
     */
    public abstract void genElectricity();

    public double getElectricityProduced() {
        return elecricityProduced;
    }

}
