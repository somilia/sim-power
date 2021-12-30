package view;

import model.BuildingType;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BuildingView extends JButton {

    private int posX; //position de la vue, on pourra peut-être identifié le building associé dans le model grace a posX en i et posY en j dans la liste de box
    private int posY;


    public BuildingView(BuildingType type){

       // this.setBackground(Color.RED);
        this.setVisible(true);

        Icon icon;
        switch (type) { // choisir l'image en fonction du type de bâtiment

            case NUCLEAR:
                icon = new ImageIcon("/res/img/nuclear.jpg");
                break;
            case GAS:
                icon = new ImageIcon("/res/img/grass.jpg");
                break;
            case COAL:
                icon = new ImageIcon("/res/img/grass.jpg");
                break;
            case WATER:
                icon = new ImageIcon("/res/img/grass.jpg");
                break;
            case SOLAR:
                icon = new ImageIcon("/res/img/panel.jpg");
                break;
            case WIND:
                icon = new ImageIcon("/res/img/turbine.jpg");
                break;
            case HOUSE:
                icon = new ImageIcon("/res/img/grass.jpg");
                break;
            case HOME:
                icon = new ImageIcon("/res/img/grass.jpg");
                break;
            default:
                icon = new ImageIcon("/res/img/grass.jpg");
                break;
        }


        this.setIcon(icon);
        this.setBounds(500,200,32,32);

        //Ces 3 méthodes permettent de de donner au bouton la forme de son icone
        /**this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);*/
        System.out.println("buildingView instantiated");

    }
}
