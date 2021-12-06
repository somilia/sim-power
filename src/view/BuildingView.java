package view;

import model.BuildingType;

import javax.swing.*;

public class BuildingView extends JButton {

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
