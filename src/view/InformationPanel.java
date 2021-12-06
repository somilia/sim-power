package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InformationPanel extends JPanel {

    public static final int INFO_PANEL_WHIDTH = 1152;
    public static final int INFO_PANEL_HEIGHT = 50;
    public static final int INFO_PANEL_COORD_X = 0;
    public static final int INFO_PANEL_COORD_Y = 0;

    /*private int nbOfHabitants;
    private int energyProduced;
    private float energyPrice;
    private float pollutionRate;
    private int userMoney;*/

    private JLabel nbOfHabitantsLabel;
    private JLabel energyProducedLabel;
    private JLabel energyPriceLabel;
    private JLabel pollutionRateLabel;
    private JLabel userMoneyLabel;

    private List<JLabel> labelList;


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
