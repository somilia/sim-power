package controller;

import model.BuildingType;
import model.Map;
import view.BuildingViewable;
import view.MapPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PlaceBuildingListener implements MouseMotionListener {

    public static int INITIAL_POSITION_X = 16;
    public static int INITIAL_POSITION_Y = 8;


    private GameController gameController;

    private BuildingType buildingType;
    private BuildingViewable buildingView;

    private boolean isPlacingBuilding;
    private boolean boxIsAvailable;

    int previousPosX;
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



    @Override
    public void mouseMoved(MouseEvent e) {

        if (isPlacingBuilding) {

            gameController.getMapPanel().addBuilding(buildingView, e.getX() / (MapPanel.MAP_PANEL_WIDTH / Map.NB_BOX_X), e.getY() / (MapPanel.MAP_PANEL_HEIGHT / Map.NB_BOX_Y));
            gameController.getMapPanel().removeBuilding(buildingView, previousPosX, previousPosY);
            previousPosX = e.getX() / (MapPanel.MAP_PANEL_WIDTH / Map.NB_BOX_X);
            previousPosY = e.getY() / (MapPanel.MAP_PANEL_HEIGHT / Map.NB_BOX_Y);
            buildingView.setPositionX(previousPosX);
            buildingView.setPositionY(previousPosY);
            gameController.getMapPanel().repaint();

            //TODO on met en vert l'objet
            // TODO mettre en rouge l'objet
            boxIsAvailable = gameController.getModel().canBuildOnBox(e.getX() / (MapPanel.MAP_PANEL_WIDTH / Map.NB_BOX_X), e.getY() / (MapPanel.MAP_PANEL_HEIGHT / Map.NB_BOX_Y), buildingType);
        }
    }


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

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
















