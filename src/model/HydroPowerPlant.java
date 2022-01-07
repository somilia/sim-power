package model;
/**
 *Classe qui hérité de RenewableEnergy et qui modélise une centrale hydraulique.
 */
public class HydroPowerPlant extends RenewableEnergy {

    private static final double MEAN = 140.0;//< Moyenne de la production d'énergie, car la moyenne de la variable modélisant la force du courant (Box.water) est de 1;

    public HydroPowerPlant(Box baseBox) {
        super(baseBox);
    }

    /**
     * simule la production d'énergie en fonction de la caractéristique dynamique de baseBox
     */
    @Override
    public void genElectricity() {
        this.elecricityProduced = MEAN* baseBox.getWater();
    }
}
