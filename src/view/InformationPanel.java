package view;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {

    public static final int INFO_PANEL_WHIDTH = 1152;
    public static final int INFO_PANEL_HEIGHT = 50;
    public static final int INFO_PANEL_COORD_X = 0;
    public static final int INFO_PANEL_COORD_Y = 0;

    private int nbOfHabitants;
    private int energyProduced;
    private float energyPrice;
    private float pollutionRate;
    private int userMoney;

    /**
     * On a le choix entre créer un tableaux statique de label pour afficher les 5 attributs ci-dessus
     * Ou alors on instancie 5 JLabel et on les ajoutent à un une ArrayList
     * Ou alors on crée notre propre classe qui extends JLabel et on les instancient et on les ajoutent à une ArrayList
     */

    InformationPanel(){
        this.setLayout(null);
    }

    /**
     * @return la Dimension du JPanel de la barre d'info que l'on souhaite
     */
    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(INFO_PANEL_WHIDTH, INFO_PANEL_HEIGHT));
    }
}
