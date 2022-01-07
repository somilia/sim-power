package controller;

import model.BuildingType;
import view.BuildingView;
import view.BuildingViewable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * BuildBuildingListener est un listener pour les bouttons de la fenêtre du menu, qui servent à acheter des bâtiments
 * Cette classe implémente l'interface ActionListener qui est une interface du package java.awt qui permet de récupérer l'évènement lorqu'on clique sur le button auquel on a ajouté ce Listener
 */
public class BuildBuildingListener implements ActionListener {

    public static int INITIAL_POSITION_X = 16;//Position initiale de la vue de bâtiment qu'on va devoir placer avant qu'on ai bougé notre souris
    public static int INITIAL_POSITION_Y = 8;

    GameController gameController;
    BuildingType buildingType;

    BuildBuildingListener(GameController gameController, BuildingType buildingType){
        this.gameController = gameController;
        this.buildingType = buildingType;
    }

    /**
     *Est appelé lorqu'on clique sur un boutton auquel on ajouté une instance de cette classe
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(gameController.getModel().hasEnoughMoney(buildingType) && !gameController.getPlaceBuildingListener().isPlacingBuilding()) {

            BuildingViewable tempBuildingView = new BuildingView(buildingType, gameController);//Si l'utilisateur à assez d'argent, on instancie la vue du bâtiment et on la passe à MapPanel

            gameController.getMapPanel().addBuilding(tempBuildingView, INITIAL_POSITION_X, INITIAL_POSITION_Y);
            gameController.getMapPanel().repaint();
            gameController.getPlaceBuildingListener().activatePlacingBuilding(tempBuildingView, buildingType);//On active le Listener PlaceBuildingListener pour pouvoir placer le bâtiment en bougeant la souris
            gameController.getMenu().setVisible(false);
        }
        else if(!gameController.getModel().hasEnoughMoney(buildingType)){
            gameController.getMenu().printErrorMessage("Not enough money");
        }
    }
}
