package view;

import javax.swing.*;


public class BoxButton extends JButton{



    BoxButton(String s) {
        super(s);
        Icon icon = new ImageIcon(getClass().getResource("/res/img/ballon.jpg"));
        this.setSize(200,50);
        this.setIcon(icon);

    }

}
