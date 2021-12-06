package controller;
import model.Map;
import view.GameFrame;

public class GameController {

    private GameFrame frame;
    private Map map;

    GameController(){
        frame = new GameFrame(this);
        map = new Map();
    }
}
