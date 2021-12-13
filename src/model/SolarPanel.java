package model;

public class SolarPanel extends RenewableEnergy {

    public static final double MEAN = 10.0; //on peut déclarer la moyenne comme ca car la moyenne des caracteristiques dynamiques est egal à 1
    public SolarPanel(Box baseBox) {
        super(baseBox);
    }

    @Override
    public double genElectricity() {
            return MEAN* baseBox.getSun();
    }

}
