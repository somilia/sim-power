package view;

import model.BuildingType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class BuildingView extends JButton {

    private int posX; //position de la vue, on pourra peut-être identifié le building associé dans le model grace a posX en i et posY en j dans la liste de box
    private int posY;

    BufferedImage image;

    public BuildingView(BuildingType type){

        URL resource;

        // JSP PK LE SWITCH MARCHE PAS PTN

        /*switch (type) { // choisir l'image en fonction du type de bâtiment

            case NUCLEAR:
                resource = getClass().getResource("/res/img/nuclear.jpg");
                break;
            case GAS:
                resource = getClass().getResource("/res/img/grass.jpg");
                break;
            case COAL:
                resource = getClass().getResource("/res/img/grass.jpg");
                break;
            case WATER:
                resource = getClass().getResource("/res/img/grass.jpg");
                break;
            case SOLAR:
                resource = getClass().getResource("/res/img/panel.jpg");
                System.out.println("solarrrrr");
                break;
            case WIND:
                resource = getClass().getResource("/res/img/turbine.jpg");
                break;
            case HOUSE:
                resource = getClass().getResource("/res/img/grass.jpg");
                break;
            case HOME:
                resource = getClass().getResource("/res/img/grass.jpg");
                break;
            default:
                resource = getClass().getResource("/res/img/grass.jpg");
                break;
        }*/




        resource = getClass().getResource("/res/img/panel.png");
        try {
            image = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setSize(32,32);
        this.setVisible(true);

        //Ces 3 méthodes permettent de de donner au bouton la forme de son icone
        /*this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);*/
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
