package model;

/**
 *Classe qui hérité de Home et qui modélise une maison.
 */
public class House extends Home {

    public House(){
        super();
        this.nbOfHabitants=0;
        this.maxNbOfHabitants=5;

        this.moneyFrequency = 20*1000;
    }
    public int genMoney() {
        return nbOfHabitants*250;
    }
}
