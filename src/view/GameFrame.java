package view;

import controller.GameController;
import model.Box;
import patterns.Observer;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private MapPanel mapPanel;
    private InformationPanel informationPanel;

    public GameFrame(MapViewable mapPanel, Observer informationPanel) {
        super();
        this.setTitle("Simpower");
        this.mapPanel = (MapPanel) mapPanel;
        this.informationPanel = (InformationPanel) informationPanel;


        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(this.informationPanel, BorderLayout.NORTH);
        this.add(this.mapPanel, BorderLayout.SOUTH);
        this.pack();
    }
}