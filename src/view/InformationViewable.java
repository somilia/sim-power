package view;

import patterns.Observable;
import patterns.InformationObserver;

public interface InformationViewable extends InformationObserver {

    //public void setController(GameController gc);
    public void updateData(Observable obs);


}
