package controller;
import model.BuildingType;
import model.Map;
import patterns.Observer;
import view.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameController {

    private GameFrame gameFrame;
    private MapViewable mapPanel;
    private Observer informationPanel;
    private MenuViewable menu;

    private Map model;

    private BuildingListener buildingListener;
    private BuildBuildingListener buildBuildingListener;
    private placeBuildingListener setBuildingListener;




    int x=0,y=0;




    GameController() {

        model = new Map();

        gameFrame = new GameFrame();
        mapPanel = new MapPanel();
        informationPanel = new InformationPanel();
        menu = new MenuFrame();


        buildBuildingListener = new BuildBuildingListener(this, BuildingType.SOLAR);
        buildBuildingListener = new BuildBuildingListener(this, BuildingType.WIND);
        buildBuildingListener = new BuildBuildingListener(this, BuildingType.WATER);
        buildBuildingListener = new BuildBuildingListener(this, BuildingType.COAL);
        buildBuildingListener = new BuildBuildingListener(this, BuildingType.GAS);
        buildBuildingListener = new BuildBuildingListener(this, BuildingType.NUCLEAR);

        buildingListener = new BuildingListener();


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


    }





    public MapViewable getMapPanel() {
        return mapPanel;
    }

    public Observer getInformationPanel() {
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

    public BuildBuildingListener getBuildBuildingListener() {
        return buildBuildingListener;
    }

    public placeBuildingListener getSetBuildingListener() {
        return setBuildingListener;
    }*/

}
