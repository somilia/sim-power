package controller;
import model.BuildingType;
import model.Map;
import patterns.InformationObserver;
import view.*;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * Classe principale du package controller qui permet de faire le lien entre les instances du package model et view
 * Cette classe récupère les interactions avec l'utilisateur via la vue, les traite et les transmet au model
 * Cette classe coordonne tout le déroulement du programme
 */
public class GameController {

    private GameFrame gameFrame;//< frame principale de la vue
    private MapViewable mapPanel;//< panel de la map
    private InformationObserver informationPanel;//< panel des informations
    private MenuViewable menu;//< fenêtre du menu

    private Map model;//< classe principale du model par qui passe toutes les interactions entre model et controller

    private PlaceBuildingListener placeBuildingListener;//< Listener qui permet de déplacer la vue d'un bâtiment
    private OpenMenuListener openMenuListener;//< Listener qui permet de rendre visible la fenêtre du menu en cliquant sur un bouton
    private BuildBuildingListener buildSolarPanel;//< Listener qui permet de construire un panneau solaire
    private BuildBuildingListener buildWindTurbine;//< Listener qui permet de construire une éolienne
    private BuildBuildingListener buildHydroPowerPlant;//< Listener qui permet de construire une centrale hydraulique
    private BuildBuildingListener buildCoalPowerPlant;//< Listener qui permet de construire une centrale à charbon
    private BuildBuildingListener buildGasPowerPlant;//< Listener qui permet de construire une centrale à gaz
    private BuildBuildingListener buildNuclearPowerPlant;//< Listener qui permet de construire une centrale nucléaire
    private BuildBuildingListener buildHouse;//< Listener qui permet de construire une maison
    private BuildBuildingListener buildAppartement;//< Listener qui permet de construire un immeuble


    /**Pour rendre un peu plus joli l'interface :)
     */
    GameController() throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
    }

    /**
     * Lance le jeu
     */
    public void start(){
        instantiateModel();
        instantiateListeners();
        javax.swing.SwingUtilities.invokeLater(this::instantiateView);
        model.run();
    }

    /**
     * instancie le model
     */
    private void instantiateModel(){
        model = new Map();
    }

    /**
     * instancie les listeners
     */
    private void instantiateListeners(){
        placeBuildingListener = new PlaceBuildingListener(this);
        buildSolarPanel = new BuildBuildingListener(this, BuildingType.SOLAR);
        buildWindTurbine = new BuildBuildingListener(this, BuildingType.WIND);
        buildHydroPowerPlant = new BuildBuildingListener(this, BuildingType.WATER);
        buildCoalPowerPlant = new BuildBuildingListener(this, BuildingType.COAL);
        buildGasPowerPlant = new BuildBuildingListener(this, BuildingType.GAS);
        buildNuclearPowerPlant = new BuildBuildingListener(this, BuildingType.NUCLEAR);
        buildHouse = new BuildBuildingListener(this, BuildingType.HOUSE);
        buildAppartement = new BuildBuildingListener(this, BuildingType.APPARTEMENT);
    }

    /**
     * instancie la vue
     */
    private void instantiateView(){
        mapPanel = new MapPanel(model.getBoxList());
        mapPanel.addPlaceBuildingListener(placeBuildingListener);
        menu = new MenuFrame(buildSolarPanel, buildWindTurbine, buildHydroPowerPlant, buildCoalPowerPlant, buildGasPowerPlant, buildNuclearPowerPlant, buildHouse, buildAppartement);
        openMenuListener = new OpenMenuListener(menu);
        informationPanel = new InformationPanel(openMenuListener);
        gameFrame = new GameFrame(mapPanel, informationPanel);

        model.registerInformationObserver(informationPanel);
        model.registerHomeObserver(mapPanel);
    }





    public MapViewable getMapPanel() {
        return mapPanel;
    }

    public MenuViewable getMenu() {
        return menu;
    }

    public Map getModel() {
        return model;
    }

    public PlaceBuildingListener getPlaceBuildingListener() {
        return placeBuildingListener;
    }

}
