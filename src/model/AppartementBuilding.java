package model;

/**
 *Classe qui hérité de Home et qui modélise un appartement.
 */
public class AppartementBuilding extends Home {

    public AppartementBuilding(){
        super();
        this.nbOfHabitants=0;
        this.maxNbOfHabitants=12;

        this.moneyFrequency = 45*1000;

    }

    /**
     * Simule l'argent généré par les habitants.
     * @return int : la somme généré proportionnellement au nombre d'habitants de l'appartement
     */
    public int genMoney() {
        return nbOfHabitants*500;
    }
}
