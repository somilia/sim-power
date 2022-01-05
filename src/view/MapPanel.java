package view;

import controller.PlaceBuildingListener;
import model.Box;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel implements MapViewable{

    public static final int MAP_PANEL_WIDTH = 1024;
    public static final int MAP_PANEL_HEIGHT = 576;


    private BoxPanel[][] boxPanelsList;


    public MapPanel(Box[][] boxes) {

        super();

        boxPanelsList = new BoxPanel[32][18];
        initializeMap(boxes);


        //this.repaint();

    }

    /**
     * On choisit des dimensions 16:9 pour le JPanel de la Map
     * @return la Dimension du JPanel de la Map que l'on souhaite
     */
    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(MAP_PANEL_WIDTH, MAP_PANEL_HEIGHT));
    }




    @Override//TODO comprendre -> ordre des deux boucles imbriqués inversées à cause de l'odre de remplissage du GridLayout
    public void initializeMap(Box[][] boxes) {
        GridLayout gridLayout = new GridLayout(18,32, 0, 0);
        this.setLayout(gridLayout);
        for (int j = 0; j < 18; j++) {
            for (int i = 0; i < 32; i++) {
                boxPanelsList[i][j] = new BoxPanel(boxes, i, j);
                this.add(boxPanelsList[i][j]);
            }
        }
    }

    @Override
    public void addBuilding(BuildingViewable buildingView, int positionX, int positionY) {
        this.boxPanelsList[positionX][positionY].addBuilding(buildingView);
    }

    @Override
    public void removeBuilding(BuildingViewable buildingView, int positionX, int positionY) {
        this.boxPanelsList[positionX][positionY].removeBuilding(buildingView);
    }

    @Override
    public void addPlaceBuildingListener(PlaceBuildingListener placeBuildingListener) {
        this.addMouseMotionListener(placeBuildingListener);
    }


    @Override
    public void update(int x, int y) {
        this.boxPanelsList[x][y].getBuildingView().setImageMoneyCollect();
    }


}

