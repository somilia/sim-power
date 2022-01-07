package view;

import controller.PlaceBuildingListener;
import model.Box;

import javax.swing.*;
import java.awt.*;

/**
 * Element graphique qui contient toutes les vues nécessaires à l'affichage de la Map
 * Hérite de JPanel car contient plusieurs vues
 * implémente MapViewable pour implémenter toutes les méthodes nécessaires au bon affichage de la Map
 */
public class MapPanel extends JPanel implements MapViewable{

    public static final int MAP_PANEL_WIDTH = 1024;
    public static final int MAP_PANEL_HEIGHT = 576;


    private BoxPanel[][] boxPanelsList;//< liste des vues de Box


    public MapPanel(Box[][] boxes) {

        super();

        boxPanelsList = new BoxPanel[32][18];
        initializeMap(boxes);


        //this.repaint();

    }

    /**
     * On choisit des dimensions 16:9 pour le JPanel de la Map
     * @return la Dimension du JPanel de la Map que l'on souhaite
     */
    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(MAP_PANEL_WIDTH, MAP_PANEL_HEIGHT));
    }



    /**
     * Initialise toutes les vues permettant d'afficher la Map
     * @param boxes Matrice de Box du model qui contient toutes les données nécessaires pour afficher les Box correctement
     */
    @Override
    public void initializeMap(Box[][] boxes) {
        GridLayout gridLayout = new GridLayout(18,32, 0, 0);
        this.setLayout(gridLayout);
        for (int j = 0; j < 18; j++) {
            for (int i = 0; i < 32; i++) {
                boxPanelsList[i][j] = new BoxPanel(boxes, i, j);
                this.add(boxPanelsList[i][j]);
            }
        }
    }

    /**
     * Permet d'ajouter la vue d'un bâtiment à la Map
     * @param buildingView vue du bâtiment
     * @param positionX abscisse de la positon à laquelle doit être placé la vue du bâtiment
     * @param positionY abscisse de la positon à laquelle doit être placé la vue du bâtiment
     */
    @Override
    public void addBuilding(BuildingViewable buildingView, int positionX, int positionY) {
        this.boxPanelsList[positionX][positionY].addBuilding(buildingView);
    }

    /**
     * Permet de retirer la vue d'un bâtiment à la Map
     * @param buildingView vue du bâtiment
     * @param positionX abscisse de la positon de la vue à retirer
     * @param positionY abscisse de la positon de la vue à retirer
     */
    @Override
    public void removeBuilding(BuildingViewable buildingView, int positionX, int positionY) {
        this.boxPanelsList[positionX][positionY].removeBuilding(buildingView);
    }

    /**
     * Ajoute un Listener permettant de déplacer les vues de bâtiment
     * @param placeBuildingListener Listener
     */
    @Override
    public void addPlaceBuildingListener(PlaceBuildingListener placeBuildingListener) {
        this.addMouseMotionListener(placeBuildingListener);
    }

    /**
     * Met à jour l'affichage de la vue
     */
    @Override
    public void update(int x, int y) {
        this.boxPanelsList[x][y].getBuildingView().setImageMoneyCollect();
    }


}

