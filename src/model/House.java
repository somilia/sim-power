package model;

public class House extends Home {

    public House(){
        this.nbOfHabitants=0;
        this.maxNbOfHabitants=5;
    }
    public int genMoney() {
        return nbOfHabitants*25;
    }
}
