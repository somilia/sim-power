package controller;

import view.BuildingViewable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * BuildingListener est un Listener pour les vues de bâtiment.
 * Ainsi il permet de gérer l'évènement de click sur la vue d'un bâtiment.
 */
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

    /**
     *Est appelé lorsque l'on clique sur la vue d'un bâtiment
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(canPlaceBuilding){
            canPlaceBuilding = !gameController.getPlaceBuildingListener().mouseClicked();//Si le bâtiment a finit d'être placé, alors la méthode mouseClicked() renvoie true. Si elle renvoie true alors canPlaceBuilding=false et on ne peut plus le placer.
        }
        if(canCollectMoney){
            gameController.getModel().addMoney(buildingViewable.getPositionX(), buildingViewable.getPositionY());//Si on peut collecter l'argent alors on appelle dit au Controller d'appeler la méthode appropriée du Model
            buildingViewable.setImageNoMoney();
            canCollectMoney=false;
        }

    }


    public void setCanCollectMoney(boolean b){
        this.canCollectMoney = b;
    }
}
