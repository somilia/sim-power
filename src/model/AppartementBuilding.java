package model;

public class AppartementBuilding extends Home {

    public AppartementBuilding(){
        this.nbOfHabitants=0;
        this.maxNbOfHabitants=5;
    }

    public int genMoney() {
        return nbOfHabitants*50;
    }
}
