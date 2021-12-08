package controller;
import model.Map;
import view.GameFrame;

public class GameController {

    private GameFrame frame;
    private Map map;

    private BuildingListener buildingListener;
    private BuildBuildingListener buildBuildingListener;
    private placeBuildingListener setBuildingListener;


    GameController(){
        frame = new GameFrame(this);
        map = new Map();
    }
}
