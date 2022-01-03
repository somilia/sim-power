package controller;
import model.BuildingType;
import model.Map;
import view.*;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class GameController {

    private GameFrame gameFrame;
    private MapPanel mapPanel;
    private InformationPanel informationPanel;
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
        buildingListener = new BuildingListener(this);
        buildSolarPanel = new BuildBuildingListener(this, buildingListener, BuildingType.SOLAR);
        buildWindTurbine = new BuildBuildingListener(this, buildingListener, BuildingType.WIND);
        buildHydroPowerPlant = new BuildBuildingListener(this, buildingListener, BuildingType.WATER);
        buildCoalPowerPlant = new BuildBuildingListener(this, buildingListener, BuildingType.COAL);
        buildGasPowerPlant = new BuildBuildingListener(this, buildingListener, BuildingType.GAS);
        buildNuclearPowerPlant = new BuildBuildingListener(this, buildingListener, BuildingType.NUCLEAR);
        buildHouse = new BuildBuildingListener(this, buildingListener, BuildingType.HOUSE);
        buildAppartement = new BuildBuildingListener(this, buildingListener, BuildingType.APPARTEMENT);

        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        mapPanel = new MapPanel(model.getBoxList());
        mapPanel.addMouseMotionListener(placeBuildingListener);
        menu = new MenuFrame(buildSolarPanel, buildWindTurbine, buildHydroPowerPlant, buildCoalPowerPlant, buildGasPowerPlant, buildNuclearPowerPlant, buildHouse, buildAppartement);
        openMenuListener = new OpenMenuListener(menu);
        informationPanel = new InformationPanel(openMenuListener);
        gameFrame = new GameFrame(mapPanel, informationPanel);

        model.registerObserver(informationPanel);
        model.run();

    }







    public MapPanel getMapPanel() {
        return mapPanel;
    }

    public InformationPanel getInformationPanel() {
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
