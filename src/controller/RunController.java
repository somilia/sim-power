package controller;

import javax.swing.*;

public class RunController {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        //normalement on doit passer la gameFrame et la Map en paramètre -> injection de dependance
        GameController gameController = new GameController();
    }

}
