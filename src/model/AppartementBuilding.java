package model;

public class AppartementBuilding extends Home {

    public AppartementBuilding(){
        this.nbOfHabitants=0;
        this.maxNbOfHabitants=12;

        this.lastMoneyCollect = System.currentTimeMillis();
        this.moneyFrequency = 45*1000;

    }

    public int genMoney() {
        return nbOfHabitants*500;
    }
}
