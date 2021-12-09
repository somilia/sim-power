package controller;
import model.Map;
import view.GameFrame;

public class GameController {

    private GameFrame gameFrame;
    private Map map;

    private BuildingListener buildingListener;
    private BuildBuildingListener buildBuildingListener;
    private placeBuildingListener setBuildingListener;


    GameController(){
        this.gameFrame = new GameFrame();
        this.map = new Map();
    }
}
