package controller;

import model.BuildingType;
import model.Map;
import view.BuildingView;
import view.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildBuildingListener implements ActionListener {

    public static int INITIAL_POSITION_X = 16;
    public static int INITIAL_POSITION_Y = 8;

    GameController gameController;
    BuildingType buildingType;

    BuildBuildingListener(GameController gameController, BuildingType buildingType){
        this.gameController = gameController;
        this.buildingType = buildingType;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(gameController.getModel().hasEnoughMoney(buildingType)) {

            BuildingView tempBuildingView = new BuildingView(buildingType, gameController);

            gameController.getMapPanel().addBuilding(tempBuildingView, INITIAL_POSITION_X, INITIAL_POSITION_Y);
            gameController.getMapPanel().repaint();
            gameController.getPlaceBuildingListener().activatePlacingBuilding(tempBuildingView, buildingType);
            gameController.getMenu().setVisible(false);
        }
        else{
            gameController.getMenu().printErrorMessage("Not enough money");
        }
    }
}
