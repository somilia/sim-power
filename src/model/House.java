package model;

public class House extends Home {

    public House(){
        this.nbOfHabitants=0;
        this.maxNbOfHabitants=5;

        this.lastMoneyCollect = System.currentTimeMillis();
        this.moneyFrequency = 20*1000;
    }
    public int genMoney() {
        return nbOfHabitants*250;
    }
}
