package controller;

import view.MenuViewable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener qui permet d'ouvir la fenêtre du menu
 * implémente ActionListener car il sera ajouté à un bouton
 */
public class OpenMenuListener implements ActionListener {

    MenuViewable menuFrame;

    OpenMenuListener(MenuViewable menuFrame){
        this.menuFrame = menuFrame;
    }

    /**
     * appelé lorqu'on clique sur le bouton qui possède ce Listener
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        menuFrame.setVisible(true);
    }
}
