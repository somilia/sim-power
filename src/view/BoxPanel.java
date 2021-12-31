package view;

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

    BoxPanel(int x, int y) {
       // super(s);

        JLabel picLabel = new JLabel(new ImageIcon(getClass().getResource("/res/img/coal.png")));
        //this.add(picLabel);


        URL resource = getClass().getResource("/res/img/coal.png");
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
