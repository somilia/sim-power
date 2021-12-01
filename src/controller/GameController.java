package controller;
import view.GameFrame;

public class GameController {

    private GameFrame frame;

    public void runGame() {
        frame = new GameFrame(this);
    }
}
