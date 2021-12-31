package view;

import model.Box;
import model.BuildingType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapPanel extends JPanel implements MapViewable{

    public static final int MAP_PANEL_WIDTH = 1024;
    public static final int MAP_PANEL_HEIGHT = 576;
    public static final int MAP_PANEL_COORD_X = 0;
    public static final int MAP_PANEL_COORD_Y = 50;



    private BoxPanel[][] boxPanelsList;


    public MapPanel(Box[][] boxes) {

        super();
        //this.setPreferredSize(new Dimension(MAP_PANEL_WIDTH, MAP_PANEL_HEIGHT));
       // this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.GREEN);

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
    public void addBuilding(BuildingView buildingView, int positionX, int positionY) {
        System.out.println("MapPanel : addBuilding : "+ positionX+" "+positionY);
        this.remove(buildingView);
        this.boxPanelsList[positionX][positionY].add(buildingView);
    }

}

