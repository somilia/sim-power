package view;

import model.BuildingType;

import javax.swing.*;
import java.awt.*;

public class BuildingView extends JButton {

    private int posX; //position de la vue, on pourra peut-être identifié le building associé dans le model grace a posX en i et posY en j dans la liste de box
    private int posY;


    public BuildingView(BuildingType type){

        //ImageIcon icon = new ImageIcon("/res/img/water.png");
        //this.setIcon(icon);
        this.setBackground(Color.RED);
        this.setVisible(true);
        /*switch (type)
        {
            // choisir l'image en fonction du type de bâtiment
        }*/
        this.setBounds(500,200,32,32);

        //Ces 3 méthodes permettent de de donner au bouton la forme de son icone
        /**this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);*/
        System.out.println("buildingView instantiated");

    }
}
