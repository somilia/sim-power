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
    //private BoxButton boxButton;


    MapPanel() {
        super();
        this.setPreferredSize(new Dimension(MAP_PANEL_WIDTH, MAP_PANEL_HEIGHT));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.RED);
        buildingViewList = new ArrayList<BuildingView>();

        GridLayout gridLayout = new GridLayout(18,32, 10, 10);
        this.setLayout(gridLayout);
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 18; j++) {
                BoxButton b = new BoxButton("Index: " + i);
                this.add(b);
            }

        }

        this.repaint();

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

