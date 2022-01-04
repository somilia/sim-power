package model;

public abstract class Home extends Building {

    protected int nbOfHabitants;
    protected int maxNbOfHabitants;

    protected long lastMoneyCollect;
    protected long moneyFrequency;


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

    public void setLastMoneyCollect(long lastMoneyCollect) {
        this.lastMoneyCollect = lastMoneyCollect;
    }

    public long getLastMoneyCollect() {
        return lastMoneyCollect;
    }

    public long getMoneyFrequency() {
        return moneyFrequency;
    }
}
