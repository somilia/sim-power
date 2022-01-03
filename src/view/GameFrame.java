package view;

import controller.GameController;
import model.Box;
import patterns.Observer;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    //private GameController gameController;//pas obliger de le garder en attribut si on utilise pas de controller pour la frame
                                          // on le passe en paramètre juste pour pouvoir le passer
    private MapPanel mapPanel;
    private InformationPanel informationPanel;

    public GameFrame(MapViewable mapPanel, Observer informationPanel) {
        super();
        this.setTitle("Simpower");
        // this.gameController = gameController;
        this.mapPanel = (MapPanel) mapPanel;
        this.informationPanel = (InformationPanel) informationPanel;
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(this.informationPanel, BorderLayout.NORTH);
        this.add(this.mapPanel, BorderLayout.SOUTH);
        this.pack();
    }
}