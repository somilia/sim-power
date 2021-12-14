package view;

import model.Box;
import model.BuildingType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapPanel extends JPanel implements MapViewable{

    public static final int MAP_PANEL_WIDTH = 1024;
    public static final int MAP_PANEL_HEIGHT = 576;
    public static final int MAP_PANEL_COORD_X = 0;
    public static final int MAP_PANEL_COORD_Y = 50;

    private List<BuildingView> buildingViewList;
    private BoxButton boxButton;


    MapPanel() {
        super();
        this.setPreferredSize(new Dimension(MAP_PANEL_WIDTH, MAP_PANEL_HEIGHT));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.RED);
        buildingViewList = new ArrayList<BuildingView>();
        // Bien penser à créer les éléments avant de les placer
        /*JButton button = new JButton("Test");
        this.add(button);*/

//        this.boxButton = new BoxButton("Index: " + i);

        GridLayout gridLayout = new GridLayout(4,5, 10, 10);
        this.setLayout(gridLayout);
        for (int i = 0; i < 20; i++) {
            BoxButton b = new BoxButton("Index: " + i);
            this.add(b);

        }
        //this.add(this.boxButton);
        this.repaint();
        //this.setBounds(250,0,250,250);
    }

    /**
     * On choisit des dimensions 16:9 pour le JPanel de la Map
     * @return la Dimension du JPanel de la Map que l'on souhaite
     */
    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(MAP_PANEL_WIDTH, MAP_PANEL_HEIGHT));
    }




    @Override
    public void initializeMap(Box[][] boxes) {

    }

    @Override
    public void addBuilding(BuildingType buildingType, int positionX, int positionY) {

    }

}

