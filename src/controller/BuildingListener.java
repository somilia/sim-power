package controller;

import view.BuildingViewable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildingListener implements ActionListener {

    private GameController gameController;
    private BuildingViewable buildingViewable;
    private boolean canCollectMoney;
    private boolean canPlaceBuilding;

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

    public void setBuildingViewable(BuildingViewable buildingViewable){
        this.buildingViewable = buildingViewable;
    }

    public void setCanCollectMoney(boolean b){
        this.canCollectMoney = b;
    }
}
