package view;

import patterns.Observer;

public interface InformationViewable extends Observer {

    //public void setController(GameController gc);
    public void updateData(int nbOfHabitants, int energyProduced, int energyPrice, float pollutionRate, int userMoney);

}
