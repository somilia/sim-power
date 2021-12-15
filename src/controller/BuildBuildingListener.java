package controller;

import model.BuildingType;
import model.Map;
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
            //gameController.getMapPanel().instantiateBuilding(this.buildingType);

            /*public void instantiateBuilding(BuildingType buildingType){

                this.
            }*/
        }
        else{
        }
    }
}
