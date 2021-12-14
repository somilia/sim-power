package controller;
import model.BuildingType;
import model.Map;
import view.GameFrame;
import view.InformationPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameController {

    private GameFrame gameFrame;
    private Map map;

    private BuildingListener buildingListener;
    private BuildBuildingListener buildBuildingListener;
    private placeBuildingListener setBuildingListener;


    //int x=0,y=0;


    GameController(){
        this.gameFrame = new GameFrame();
        this.map = new Map();



        ////////////////////////////////// ne pas faire attention, j'utilise ce qui suit pour debugger mais je l'ai fait sur la branche main comme un con du coup je le met en commentaire pour pas avoir de conflit////////////////

        /*JFrame frame = new JFrame();
        InformationPanel panel = new InformationPanel();
        JButton btn = new JButton("fossil");
        JButton btn2 = new JButton("renewable");
        JButton btn3 = new JButton("house");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.buildBuilding(x++, y++, BuildingType.COAL);
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.buildBuilding(x++, y++, BuildingType.SOLAR);
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.buildBuilding(x++,y++,BuildingType.HOUSE);
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

        map.registerObserver(panel);
        map.run();*/


    }
}
