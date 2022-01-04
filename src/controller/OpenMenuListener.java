package controller;

import view.MenuFrame;
import view.MenuViewable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenMenuListener implements ActionListener {

    MenuViewable menuFrame;

    OpenMenuListener(MenuViewable menuFrame){
        this.menuFrame = menuFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menuFrame.setVisible(true);
    }
}
