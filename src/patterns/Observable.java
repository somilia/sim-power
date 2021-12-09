package patterns;

import java.util.List;

public abstract class Observable {

    List<Observer> observerList;

    public void registerObserver(Observer obs){
        this.observerList.add(obs);
    }

    public void unregisterObserver(Observer obs){
        this.observerList.remove(obs);
    }
    public void notifyObservers(){
        for (Observer obs: observerList) {
            obs.update(this);
        }
    }
}
