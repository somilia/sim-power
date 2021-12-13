package patterns;

import java.util.List;

public abstract class Observable {

    public List<Observer> observerList;

    public void registerObserver(Observer obs){
        this.observerList.add(obs);
    }

    public void unregisterObserver(Observer obs){
        this.observerList.remove(obs);
    }
    public abstract void notifyObservers();
}
