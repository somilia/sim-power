package view;

/**
 * Cette interface possède toutes les méthodes nécessaires au bon affichage de la vue des bâtiments
 */
public interface BuildingViewable {

    /**
     * modifie l'icon de la vue pour afficher celui avec de l'argent pour représenter le fait que la collecte d'argent est possible
     */
    void setImageMoneyCollect();

    /**
     * modifie l'icon de la vue pour afficher celui sans argent pour représenter le fait que la collecte d'argent n'est plus possible
     */
    void setImageNoMoney();

    /**
     * @return l'abscisse de la vue
     */
    int getPositionX();

    /**
     * @return l'ordonnées de la vue
     */
    int getPositionY();

    /**
     * modifie la position de la vue (ne déplace pas la vue mais modifie juste les attributs qui contiennent l'abscisse et l'ordonnées)
     * @param x : abscisse de la vue
     * @param y : ordonnées de la vue
     */
    void setPosition(int x, int y);
}
