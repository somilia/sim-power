package view;

/**
 * Interface qui contient toutes les méthodes nécessaires pour afficher la partie correspondent au Box de la Map
 */
public interface BoxViewable {

    /**
     * Ajoute la vue d'un bâtiment à la vue de la Box
     * @param buildingView vue du bâtiment
     */
    void addBuilding(BuildingViewable buildingView);

    /**
     * Retire la vue d'un bâtiment à la vue de la Box
     * @param buildingView vue du bâtiment
     */
    void removeBuilding(BuildingViewable buildingView);

    /**
     * @return la vue du bâtiment qui est sur la vue de la Box
     */
    BuildingViewable getBuildingView();
}
