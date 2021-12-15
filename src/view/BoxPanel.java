package view;

import javax.swing.*;


public class BoxPanel extends JPanel{

    int positionX;
    int positionY;

    BoxPanel(int x, int y) {
       // super(s);

        JLabel picLabel = new JLabel(new ImageIcon(getClass().getResource("/res/img/coal.png")));
        this.add(picLabel);


        this.positionX = x;
        this.positionY = y;

    }


}
