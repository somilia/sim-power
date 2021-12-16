package controller;
import model.BuildingType;
import model.Map;
import view.*;

import javax.swing.*;

public class GameController {

    private GameFrame gameFrame;
    private MapPanel mapPanel;
    private InformationPanel informationPanel;
    private MenuViewable menu;

    private Map model;

    private BuildingListener buildingListener;

    private BuildBuildingListener buildSolarPanel;
    private BuildBuildingListener buildWindTurbine;
    private BuildBuildingListener buildHydrauPowerPlant;
    private BuildBuildingListener buildCoalPowerPlant;
    private BuildBuildingListener buildGasPowerPlant;
    private BuildBuildingListener buildNuclearPowerPlant;

    private PlaceBuildingListener placeBuildingListener;

    int x=0,y=0;



    GameController() {

        model = new Map();

        informationPanel = new InformationPanel();
        mapPanel = new MapPanel(model.getBoxList());
        gameFrame = new GameFrame(mapPanel, informationPanel);
        menu = new MenuFrame();

        buildSolarPanel = new BuildBuildingListener(this, BuildingType.SOLAR);
        buildWindTurbine = new BuildBuildingListener(this, BuildingType.WIND);
        buildHydrauPowerPlant = new BuildBuildingListener(this, BuildingType.WATER);
        buildCoalPowerPlant = new BuildBuildingListener(this, BuildingType.COAL);
        buildGasPowerPlant = new BuildBuildingListener(this, BuildingType.GAS);
        buildNuclearPowerPlant = new BuildBuildingListener(this, BuildingType.NUCLEAR);
        placeBuildingListener = new PlaceBuildingListener(this);
        buildingListener = new BuildingListener();

        mapPanel.addMouseListener(this.placeBuildingListener);
        mapPanel.addMouseMotionListener(this.placeBuildingListener);

        ///////////////////////////////////////////////////Debugging
        JButton btn = new JButton("fossil");
        JButton btn2 = new JButton("renewable");
        JButton btn3 = new JButton("house");

        btn.addActionListener(buildCoalPowerPlant);
        btn2.addActionListener(buildSolarPanel);

        informationPanel.add(btn);
        informationPanel.add(btn2);
        /////////////////////////////////////////////////////

        model.registerObserver(informationPanel);
        model.run();

    }

        ////////////////////////////////// ne pas faire attention, j'utilise ce qui suit pour debugger mais je l'ai fait sur la branche main comme un con du coup je le met en commentaire pour pas avoir de conflit////////////////

        /*JFrame frame = new JFrame();
        InformationPanel panel = new InformationPanel();
        JButton btn = new JButton("fossil");
        JButton btn2 = new JButton("renewable");
        JButton btn3 = new JButton("house");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.buildBuilding(x++, y++, BuildingType.COAL);
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.buildBuilding(x++, y++, BuildingType.SOLAR);
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.buildBuilding(x++,y++,BuildingType.HOUSE);
            }
        });
        frame.setLayout(new FlowLayout());
        frame.add(panel);
        frame.add(btn);
        frame.add(btn2);
        frame.add(btn3);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        model.registerObserver(panel);
        model.run();


    }*/





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
        return buildHydrauPowerPlant;
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
