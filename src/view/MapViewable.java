package view;

import controller.PlaceBuildingListener;
import model.Box;
import patterns.HomeObserver;

/**
 * Cette interface contient toutes les méthodes nécessaires au bon affichage de la Map
 */
public interface MapViewable extends HomeObserver {

    /**
     * Initialise toutes les vues permettant d'afficher la Map
     * @param boxes Matrice de Box du model qui contient toutes les données nécessaires pour afficher les Box correctement
     */
    void initializeMap(Box[][] boxes);

    /**
     * Permet d'ajouter la vue d'un bâtiment à la Map
     * @param buildingView vue du bâtiment
     * @param positionX abscisse de la positon à laquelle doit être placé la vue du bâtiment
     * @param positionY abscisse de la positon à laquelle doit être placé la vue du bâtiment
     */
    void addBuilding(BuildingViewable buildingView, int positionX, int positionY);

    /**
     * Permet de retirer la vue d'un bâtiment à la Map
     * @param buildingView vue du bâtiment
     * @param positionX abscisse de la positon de la vue à retirer
     * @param positionY abscisse de la positon de la vue à retirer
     */
    void removeBuilding(BuildingViewable buildingView, int positionX, int positionY);

    /**
     * Ajoute un Listener permettant de déplacer les vues de bâtiment
     * @param placeBuildingListener Listener
     */
    void addPlaceBuildingListener(PlaceBuildingListener placeBuildingListener);

    /**
     * Met à jour l'affichage de la vue
     */
    void repaint();
}
