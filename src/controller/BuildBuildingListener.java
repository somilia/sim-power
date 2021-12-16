package controller;

import model.BuildingType;
import model.Map;
import view.BuildingView;
import view.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildBuildingListener implements ActionListener {

    GameController gameController;
    BuildingType buildingType;

    BuildBuildingListener(GameController gameController, BuildingType buildingType){
        this.gameController = gameController;
        this.buildingType = buildingType;
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(gameController.getModel().hasEnoughMoney(buildingType)) {

            BuildingView tempBuildingView = new BuildingView(buildingType);
            gameController.getMapPanel().add(tempBuildingView);
            gameController.getPlaceBuildingListener().activatePlacingBuilding(tempBuildingView, buildingType);

            System.out.println("has enough money");
        }
        else{
            gameController.getMenu().printErrorMessage("Not enough money");
        }
    }
}
