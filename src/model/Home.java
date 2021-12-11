package model;

public abstract class Home extends Building {

    private int nbOfHabitants;
    private int maxNbOfHabitants;

    public abstract int genMoney();

    public int getMaxNbOfHabitants() {
        return maxNbOfHabitants;
    }

    public int getNbOfHabitants() {
        return nbOfHabitants;
    }

    public void addHabitants(int nb){
        this.nbOfHabitants+=nb;
    }
}
