package view;

import controller.OpenMenuListener;
import patterns.InformationObserver;

import javax.swing.*;
import java.awt.*;

/**
 * Cette classe est une vue qui hérite de JPanel car elle va contenir des vues qui vont afficher des données
 * Cette classe implémente InformationObserver car elle contient les vues qui affiche ces données
 */
public class InformationPanel extends JPanel implements InformationObserver {

    public static final int INFO_PANEL_WHIDTH = 1024;
    public static final int INFO_PANEL_HEIGHT = 50;

    private JLabel populationLabel;//< label qui affiche la population
    private JLabel populationAvailableLabel;//< label qui affiche la population disponible
    private JLabel energyProducedLabel;//< label qui affiche l'énergie produite
    private JLabel energyPriceLabel;//< label qui affiche le prix de l'énergie
    private JLabel pollutionRateLabel;//< label qui affiche le taux de pollution
    private JLabel userMoneyLabel;//< label qui affiche l'argent du joueur

    private JButton menuBtn;//< bouton qui permet d'ouvrir le menu

    public InformationPanel(OpenMenuListener openMenuListener){

        this.setLayout(new FlowLayout());

        populationLabel = new JLabel();
        populationAvailableLabel = new JLabel();
        energyProducedLabel = new JLabel();
        energyPriceLabel = new JLabel();
        pollutionRateLabel = new JLabel();
        userMoneyLabel = new JLabel();
        menuBtn = new JButton("Menu");
        menuBtn.addActionListener(openMenuListener);

        this.add(populationLabel);
        this.add(populationAvailableLabel);
        this.add(energyProducedLabel);
        this.add(energyPriceLabel);
        this.add(pollutionRateLabel);
        this.add(userMoneyLabel);
        this.add(menuBtn);
    }


    /**
     * Lorsque l'on appel la method JFrame.pack(), celle-ci apelle la méthode getPreferredSize() de chacun de ses composants pour adapter au mieux sa taille
     * @return la Dimension du JPanel de la barre d'info que l'on souhaite
     */
    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(INFO_PANEL_WHIDTH, INFO_PANEL_HEIGHT));
    }




    /**
     * Methode de l'interface Observable, qui est appeleé par le model (La Map) qui hérité de la classe abstraite observable
     * Cette méthode est appelée pour actualiser les données affichées par la vues lorsque celles-ci sont modifiées dans le modèle
     * @param population nombre d'habitants de la map
     * @param populationAvailable nombre d'habitants potentiels mais qui n'ont pas de place (pas de Building de type Home) pour appartenir à la ville
     * @param populationMax nombre total d'hbts que peut acceuillir touts les Building de type Home de la Map
     * @param energyProduced somme des enregies produites par toutes les EnergySources
     * @param energyPrice ratio entre energyProduced et et population
     * @param pollutionRate ratio entre les source d'énergies fossiles et sources d'énergies renouvelables
     * @param userMoney argent du joueur
     */
    @Override
    public void update(int population, int populationAvailable, int populationMax, double energyProduced, double energyPrice, double pollutionRate, int userMoney) {
        populationLabel.setText("Population : " + population + "/" + populationMax);
        populationAvailableLabel.setText("Population available : " + populationAvailable);
        energyProducedLabel.setText(String.format("Energy produced : %.2f" ,energyProduced));
        energyPriceLabel.setText(String.format("Energy price : %.3f",energyPrice));
        pollutionRateLabel.setText(String.format("Pollution rate : %.2f",pollutionRate));
        userMoneyLabel.setText("Money : " + userMoney);
    }
}
