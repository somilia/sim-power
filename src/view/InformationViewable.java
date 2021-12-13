package view;

import model.Map;
import patterns.Observable;
import patterns.Observer;

public interface InformationViewable extends Observer {

    //public void setController(GameController gc);
    public void updateData(Observable obs);


}
