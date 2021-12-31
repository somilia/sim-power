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
    BuildingView buildingView;

    BoxPanel(Box[][] boxes, int x, int y) {
        URL resource;

        if (boxes[x][y].getWater()!=0) {resource = getClass().getResource("/res/img/water.png");}
        else if (boxes[x][y].hasCoal() && boxes[x][y].getWind()<15 && boxes[x][y].getSun()<15) {resource = getClass().getResource("/res/img/coal.png");}
        else if (boxes[x][y].hasGas() && boxes[x][y].getWind()<15 && boxes[x][y].getSun()<15) {resource = getClass().getResource("/res/img/gas.png");}
        else if (boxes[x][y].hasUranium() && boxes[x][y].getWind()<15 && boxes[x][y].getSun()<15) {resource = getClass().getResource("/res/img/uranium.png");}

        else if (boxes[x][y].getWind()>15 && !boxes[x][y].hasCoal() && !boxes[x][y].hasGas() && !boxes[x][y].hasUranium()) {resource = getClass().getResource("/res/img/wind.png");}
        else if (boxes[x][y].getWind()>15 && boxes[x][y].hasCoal()) {resource = getClass().getResource("/res/img/coal_wind.png");}
        else if (boxes[x][y].getWind()>15 && boxes[x][y].hasGas()) {resource = getClass().getResource("/res/img/gas_wind.png");}
        else if (boxes[x][y].getWind()>15 && boxes[x][y].hasUranium()) {resource = getClass().getResource("/res/img/uranium_wind.png");}

        else if (boxes[x][y].getSun()>15 && !boxes[x][y].hasCoal() && !boxes[x][y].hasGas() && !boxes[x][y].hasUranium()) {resource = getClass().getResource("/res/img/sun.png");}
        else if (boxes[x][y].getSun()>15 && boxes[x][y].hasCoal()) {resource = getClass().getResource("/res/img/coal_sun.png");}
        else if (boxes[x][y].getSun()>15 && boxes[x][y].hasGas()) {resource = getClass().getResource("/res/img/gas_sun.png");}
        else if (boxes[x][y].getSun()>15 && boxes[x][y].hasUranium()) {resource = getClass().getResource("/res/img/uranium_sun.png");}
        else{resource = getClass().getResource("/res/img/grass.png");}

        try {
            image = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());
        this.positionX = x;
        this.positionY = y;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
