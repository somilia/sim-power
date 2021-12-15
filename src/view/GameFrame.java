package view;

import controller.GameController;
import model.Box;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    //private GameController gameController;//pas obliger de le garder en attribut si on utilise pas de controller pour la frame
                                          // on le passe en paramètre juste pour pouvoir le passer
    private MapPanel mapPanel;
    private InformationPanel infoPanel;

    public GameFrame(MapPanel mapPanel) {
        super();
        this.setTitle("Simpower");
        // this.gameController = gameController;
        this.mapPanel = mapPanel;
        this.infoPanel = new InformationPanel();
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JFrame frame=new JFrame();
        //this.add(infoPanel, BorderLayout.NORTH);
        this.add(mapPanel, BorderLayout.SOUTH);
        this.pack();
    }
}