package patterns;

import java.util.List;

public abstract class Observable {

    protected List<InformationObserver> informationObserverList;
    protected List<HomeObserver> homeObserverList;

    public void registerInformationObserver(InformationObserver obs){
        this.informationObserverList.add(obs);
    }
    public void unregisterInformationObserver(InformationObserver obs){
        this.informationObserverList.remove(obs);
    }

    public void registerHomeObserver(HomeObserver obs){
        this.homeObserverList.add(obs);
    }
    public void unregisterHomeObserver(HomeObserver obs){
        this.homeObserverList.remove(obs);
    }

    public abstract void notifyInformationObservers();
    public abstract void notifyHomeObservers(int x, int y);
}
