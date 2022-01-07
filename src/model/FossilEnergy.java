package model;

/**
 * classe qui hérite de EnergySources et qui représente une source d'énergie fossile, qui ne s'appuie donc pas sur les caractéristiques dynamiques de la Map
 */
public abstract class FossilEnergy extends EnergySources {

    public FossilEnergy(Box baseBox) {
        super(baseBox);
        this.type = BuildingType.FOSSIL;
    }

    /**
     * simule la production d'énergie qui est constante
     */
    public abstract void genElectricity();



}
