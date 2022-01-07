package controller;

import model.BuildingType;
import model.Map;
import view.BuildingViewable;
import view.MapPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PlaceBuildingListener implements MouseMotionListener {

    public static int INITIAL_POSITION_X = 16;//< position initiale de la vue avant que l'utilisateur ai bougé la souris
    public static int INITIAL_POSITION_Y = 8;


    private GameController gameController;

    private BuildingType buildingType; //< BuildingType de la vue manipulée
    private BuildingViewable buildingView;//< Vue qui va être déplacée grâce à ce listener

    private boolean isPlacingBuilding;//< Vrai si on est entrain de placer la vue
    private boolean boxIsAvailable;//< Vrai si la Box sur laquelle est posée la souris est libre

    int previousPosX;//< dernière position où a été placé la vue
    int previousPosY;

    public PlaceBuildingListener(GameController gameController) {
        this.gameController = gameController;
        this.isPlacingBuilding = false;
        this.boxIsAvailable = false;
        this.previousPosX = INITIAL_POSITION_X;
        this.previousPosY = INITIAL_POSITION_Y;
    }

    public void activatePlacingBuilding(BuildingViewable buildingView, BuildingType buildingType) {
        this.buildingView = buildingView;
        this.buildingType = buildingType;
        this.isPlacingBuilding = true;
    }


    /**
     * Est appelé à chaque fois qu'on bouge la souris sur le composent auquel à été ajouté ce listener.
     * Lorsque le placement de la vue du bâtiment est activée (isPlacingBuilding=true), on place la vue du bâtiment sur la case sur laquelle est posée la souris, et on retire cette même vue de la case précédente.
     * Si le bâtiment peut être construit sur la case sur laquelle est placé la vue du bâtiment, alors on met boxIsAvailable à true.
     */
    @Override
    public void mouseMoved(MouseEvent e) {

        if (isPlacingBuilding) {

            gameController.getMapPanel().addBuilding(buildingView, e.getX() / (MapPanel.MAP_PANEL_WIDTH / Map.NB_BOX_X), e.getY() / (MapPanel.MAP_PANEL_HEIGHT / Map.NB_BOX_Y));
            gameController.getMapPanel().removeBuilding(buildingView, previousPosX, previousPosY);
            previousPosX = e.getX() / (MapPanel.MAP_PANEL_WIDTH / Map.NB_BOX_X);
            previousPosY = e.getY() / (MapPanel.MAP_PANEL_HEIGHT / Map.NB_BOX_Y);
            buildingView.setPosition(previousPosX, previousPosY);
            gameController.getMapPanel().repaint();

            //TODO on met en vert l'objet
            // TODO mettre en rouge l'objet
            boxIsAvailable = gameController.getModel().canBuildOnBox(e.getX() / (MapPanel.MAP_PANEL_WIDTH / Map.NB_BOX_X), e.getY() / (MapPanel.MAP_PANEL_HEIGHT / Map.NB_BOX_Y), buildingType);
        }
    }

    /**
     * Est appelé par BuildingListener lorsque l'on clique sur la case ou le veut poser la vue du bâtiment. Sauf que comme la vue du bâtiment est sous la souris, c'est le buidlingListener qui va récupérer l'évènement
     * et appelé cette méthode.
     * Si la méthode mouseMoved() à mis l'attribut boxIsAvailable à true, alors on arrête de placer la vue du bâtiment est on charge le Controller d'ajouter le bâtiment dans le Model
     */
    public boolean mouseClicked() {
        if (boxIsAvailable) {
            isPlacingBuilding = false;
            boxIsAvailable = false;
            gameController.getModel().buildBuilding(previousPosX, previousPosY, buildingType);
            previousPosX=INITIAL_POSITION_X;//on reinitialise les positions initiales pour le placement du prochain bâtiment;
            previousPosY=INITIAL_POSITION_Y;
        }
        return boxIsAvailable;
    }


    public boolean isPlacingBuilding() {
        return isPlacingBuilding;
    }

    /**
     * Inutilisée mais obligé de l'implémenté car présente dans l'interface M ouseMotionListener
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
















