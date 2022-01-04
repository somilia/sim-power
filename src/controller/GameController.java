package controller;
import model.BuildingType;
import model.Map;
import patterns.InformationObserver;
import view.*;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class GameController {

    private GameFrame gameFrame;
    private MapViewable mapPanel;
    private InformationObserver informationPanel;
    private MenuViewable menu;

    private Map model;

    private PlaceBuildingListener placeBuildingListener;
    private BuildingListener buildingListener;
    private OpenMenuListener openMenuListener;
    private BuildBuildingListener buildSolarPanel;
    private BuildBuildingListener buildWindTurbine;
    private BuildBuildingListener buildHydroPowerPlant;
    private BuildBuildingListener buildCoalPowerPlant;
    private BuildBuildingListener buildGasPowerPlant;
    private BuildBuildingListener buildNuclearPowerPlant;
    private BuildBuildingListener buildHouse;
    private BuildBuildingListener buildAppartement;



    GameController() throws UnsupportedLookAndFeelException {

        model = new Map();

        placeBuildingListener = new PlaceBuildingListener(this);
        buildSolarPanel = new BuildBuildingListener(this, BuildingType.SOLAR);
        buildWindTurbine = new BuildBuildingListener(this, BuildingType.WIND);
        buildHydroPowerPlant = new BuildBuildingListener(this, BuildingType.WATER);
        buildCoalPowerPlant = new BuildBuildingListener(this, BuildingType.COAL);
        buildGasPowerPlant = new BuildBuildingListener(this, BuildingType.GAS);
        buildNuclearPowerPlant = new BuildBuildingListener(this, BuildingType.NUCLEAR);
        buildHouse = new BuildBuildingListener(this, BuildingType.HOUSE);
        buildAppartement = new BuildBuildingListener(this, BuildingType.APPARTEMENT);

        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        mapPanel = new MapPanel(model.getBoxList());
        mapPanel.addPlaceBuildingListener(placeBuildingListener);
        menu = new MenuFrame(buildSolarPanel, buildWindTurbine, buildHydroPowerPlant, buildCoalPowerPlant, buildGasPowerPlant, buildNuclearPowerPlant, buildHouse, buildAppartement);
        openMenuListener = new OpenMenuListener(menu);
        informationPanel = new InformationPanel(openMenuListener);
        gameFrame = new GameFrame(mapPanel, informationPanel);

        model.registerInformationObserver(informationPanel);
        model.registerHomeObserver(mapPanel);
        model.run();

    }







    public MapViewable getMapPanel() {
        return mapPanel;
    }

    public InformationObserver getInformationPanel() {
        return informationPanel;
    }

    public MenuViewable getMenu() {
        return menu;
    }

    public Map getModel() {
        return model;
    }

    public BuildingListener getBuildingListener() {
        return buildingListener;
    }

    public BuildBuildingListener getBuildSolarPanel() {
        return buildSolarPanel;
    }

    public BuildBuildingListener getBuildWindTurbine() {
        return buildWindTurbine;
    }

    public BuildBuildingListener getBuildHydrauPowerPlant() {
        return buildHydroPowerPlant;
    }

    public BuildBuildingListener getBuildCoalPowerPlant() {
        return buildCoalPowerPlant;
    }

    public BuildBuildingListener getBuildGasPowerPlant() {
        return buildGasPowerPlant;
    }

    public BuildBuildingListener getBuildNuclearPowerPlant() {
        return buildNuclearPowerPlant;
    }

    public PlaceBuildingListener getPlaceBuildingListener() {
        return placeBuildingListener;
    }

}
