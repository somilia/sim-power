package model;

/**
 * classe qui hérite de RenewableEnergy et qui odélise un panneau solaire
 */
public class SolarPanel extends RenewableEnergy {

    public static final double MEAN = 10.0; //on peut déclarer la moyenne comme ca car la moyenne des caracteristiques dynamiques est egal à 1
    public SolarPanel(Box baseBox) {
        super(baseBox);
    }

    @Override
    public void genElectricity() {
            this.elecricityProduced = MEAN* baseBox.getSun();
    }

}
