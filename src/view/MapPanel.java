package view;

import model.BuildingType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapPanel extends JPanel {

    public static final int MAP_PANEL_WIDTH = 1024;
    public static final int MAP_PANEL_HEIGHT = 576;
    public static final int MAP_PANEL_COORD_X = 0;
    public static final int MAP_PANEL_COORD_Y = 50;

    private List<BuildingView> buildingViewList;

    MapPanel() {
        buildingViewList = new ArrayList<BuildingView>();
        this.setLayout(null);
    }

    /**
     * On choisit des dimensions 16:9 pour le JPanel de la Map
     * @return la Dimension du JPanel de la Map que l'on souhaite
     */
    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(MAP_PANEL_WIDTH, MAP_PANEL_HEIGHT));
    }

    public void addBuilding(BuildingType type, int positionX, int positionY) {
    }
}

