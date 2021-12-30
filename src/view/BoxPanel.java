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

        if (boxes[x][y].getWater()!=0) {resource = getClass().getResource("/res/img/water.png");}
        else if (boxes[x][y].isCoal()==true && boxes[x][y].getWind()<15 && boxes[x][y].getSun()<15) {resource = getClass().getResource("/res/img/coal.png");}
        else if (boxes[x][y].isGas()==true && boxes[x][y].getWind()<15 && boxes[x][y].getSun()<15) {resource = getClass().getResource("/res/img/gas.png");}
        else if (boxes[x][y].isUranium()==true && boxes[x][y].getWind()<15 && boxes[x][y].getSun()<15) {resource = getClass().getResource("/res/img/uranium.png");}

        else if (boxes[x][y].getWind()>15 && boxes[x][y].isCoal()==false && boxes[x][y].isGas()==false && boxes[x][y].isUranium()==false) {resource = getClass().getResource("/res/img/wind.png");}
        else if (boxes[x][y].getWind()>15 && boxes[x][y].isCoal()==true) {resource = getClass().getResource("/res/img/coal_wind.png");}
        else if (boxes[x][y].getWind()>15 && boxes[x][y].isGas()==true) {resource = getClass().getResource("/res/img/gas_wind.png");}
        else if (boxes[x][y].getWind()>15 && boxes[x][y].isUranium()==true) {resource = getClass().getResource("/res/img/uranium_wind.png");}

        else if (boxes[x][y].getSun()>15 && boxes[x][y].isCoal()==false && boxes[x][y].isGas()==false && boxes[x][y].isUranium()==false) {resource = getClass().getResource("/res/img/sun.png");}
        else if (boxes[x][y].getSun()>15 && boxes[x][y].isCoal()==true) {resource = getClass().getResource("/res/img/coal_sun.png");}
        else if (boxes[x][y].getSun()>15 && boxes[x][y].isGas()==true) {resource = getClass().getResource("/res/img/gas_sun.png");}
        else if (boxes[x][y].getSun()>15 && boxes[x][y].isUranium()==true) {resource = getClass().getResource("/res/img/uranium_sun.png");}
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
