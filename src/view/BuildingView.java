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

        URL resource = switch (type) { // choisir l'image en fonction du type de bâtiment

            case NUCLEAR -> getClass().getResource("/res/img/nuclear.png");
            case GAS -> getClass().getResource("/res/img/gaspower.png");
            case COAL -> getClass().getResource("/res/img/coalpower.png");
            case WATER -> getClass().getResource("/res/img/hydropower.png");
            case SOLAR -> getClass().getResource("/res/img/panel.png");
            case WIND -> getClass().getResource("/res/img/turbine.png");
            case HOUSE -> getClass().getResource("/res/img/house.png");
            case APPARTEMENT -> getClass().getResource("/res/img/appartement.png");
            default -> getClass().getResource("/res/img/grass.png");
        };

        try {
            assert resource != null;
            image = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setSize(32,32);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
