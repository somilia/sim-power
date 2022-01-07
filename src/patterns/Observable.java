package patterns;

import java.util.List;

/**
 * Cette classe abstraite contient toutes les méthodes et attribut qui permettent d'implémenter la partie observable du design patter observer
 */
public abstract class Observable {

    protected List<InformationObserver> informationObserverList;//< liste des informations observers
    protected List<HomeObserver> homeObserverList;//< liste des homes observers

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

    /**
     * Envoie à l'observer les données générales du jeu
     */
    public abstract void notifyInformationObservers();

    /**
     * Envoie à l'observer les données correspondant à la collecte d'argent des habitations
     */
    public abstract void notifyHomeObservers(int x, int y);
}
