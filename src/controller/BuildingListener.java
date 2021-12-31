package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildingListener implements ActionListener {

    private GameController gameController;

    public BuildingListener(GameController gameController){
        this.gameController = gameController;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        gameController.getPlaceBuildingListener().mouseClicked();
    }
}
