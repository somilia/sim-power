package view;

import javax.swing.*;


public class BoxButton extends JButton{



    BoxButton(String s) {
        super(s);
        Icon icon = new ImageIcon(getClass().getResource("/res/img/coal.png"));
        this.setSize(20,5);
        this.setIcon(icon);

    }

}
