package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    //private GameController gameController;//pas obliger de le garder en attribut si on utilise pas de controller pour la frame
                                          // on le passe en paramètre juste pour pouvoir le passer
    private MapPanel mapPanel;
    private InformationPanel infoPanel;


    public GameFrame() {

        //this.gameController = gameController;
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(mapPanel, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
        this.setResizable(false);
    }
}
