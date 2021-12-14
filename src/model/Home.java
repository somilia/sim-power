package model;

public abstract class Home extends Building {

    protected int nbOfHabitants;
    protected int maxNbOfHabitants;


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
