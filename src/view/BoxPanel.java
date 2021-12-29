package view;

import model.Box;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class BoxPanel extends JPanel{

    int positionX;
    int positionY;

    BufferedImage image;

    BoxPanel(Box[][] boxes, int x, int y) {
        URL resource;

        if (boxes[x][y].getWater()!=0) {resource = getClass().getResource("/res/img/grass.png");} //img actuelle de la map
        else if (boxes[x][y].getWind()!=0) {resource = getClass().getResource("/res/img/wind.png");}
        else{resource = getClass().getResource("/res/img/grass.png");}

        try {
            image = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }


        this.positionX = x;
        this.positionY = y;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
