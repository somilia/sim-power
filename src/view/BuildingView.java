package view;

import model.BuildingType;

import javax.swing.*;

public class BuildingView extends JButton {

    private int posX; //position de la vue, on pourra peut-être identifié le building associé dans le model grace a posX en i et posY en j dans la liste de box
    private int posY;


    BuildingView(BuildingType type){

        switch (type)
        {
            // choisir l'image en fonction du type de bâtiment
        }

        //Ces 3 méthodes permettent de de donner au bouton la forme de son icone
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);

    }
}
