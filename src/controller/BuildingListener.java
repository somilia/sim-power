package controller;

import view.BuildingViewable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildingListener implements ActionListener {

    private GameController gameController;
    private BuildingViewable buildingViewable;//< Vue auquel est associé ce listener
    private boolean canCollectMoney;//< si vrai, on peut cliquer sur la vue pour récolter l'argent
    private boolean canPlaceBuilding;//< si vrai, permet de cliquer sur la vue pour le placer

    public BuildingListener(GameController gameController, BuildingViewable buildingViewable){
        this.gameController = gameController;
        this.buildingViewable=buildingViewable;
        this.canCollectMoney=false;
        this.canPlaceBuilding=true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(canPlaceBuilding){
            canPlaceBuilding = !gameController.getPlaceBuildingListener().mouseClicked();
        }
        if(canCollectMoney){
            gameController.getModel().addMoney(buildingViewable.getPositionX(), buildingViewable.getPositionY());
            buildingViewable.setImageNoMoney();
            canCollectMoney=false;
        }

    }


    public void setCanCollectMoney(boolean b){
        this.canCollectMoney = b;
    }
}
