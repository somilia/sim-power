package controller;

import javax.swing.*;

/**
 * Classe principale qui contient la méthode main et permet de démarrer le programme
 */
public class RunController {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        new GameController().start();
    }

}
