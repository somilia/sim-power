package model;

/**
 * Classe qui modélise une habitation
 * Ses classes filles pourront accueillir de la population et générer de l'argent en fonction du nombre d'habitants
 */
public abstract class Home {

    protected int nbOfHabitants;//< nombre d'habitant de dans l'habitation
    protected int maxNbOfHabitants;//< nombre maximum d'habitants que peut accueillir l'habitation

    protected long lastMoneyCollect;//< date de la dernière récole d'argent
    protected long moneyFrequency;//< fréquence à laquelle il est possible de récolter de l'argent

    Home(){
        this.lastMoneyCollect = System.currentTimeMillis();
    }

    /**
     * simule la production d'argent de la part des habitants de l'habitation
     * @return int : la valeur du montant généré
     */
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
