package view;

import javax.swing.*;


public class BoxButton extends JButton{

    int positionX;
    int positionY;


    BoxButton(String s, int x, int y) {
        super(s);
        Icon icon = new ImageIcon(getClass().getResource("/res/img/coal.png"));
        this.setSize(1,1);
        this.setIcon(icon);
        this.positionX = x;
        this.positionY = y;

    }

}
