package view;

import controller.BuildBuildingListener;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame implements MenuViewable{

    private JTabbedPane menuPane;

    private JButton coalBuildBtn;
    private JButton gasBuildBtn;
    private JButton nuclearBuildBtn;
    private JButton solarBuildBtn;
    private JButton windBuildBtn;
    private JButton hydroBuildBtn;
    private JButton houseBuildBtn;
    private JButton appartementBuildBtn;



    public MenuFrame(BuildBuildingListener solarLst, BuildBuildingListener windLst,BuildBuildingListener waterLst,BuildBuildingListener coalLst,BuildBuildingListener gasLst,BuildBuildingListener nuclearLst, BuildBuildingListener houseLst, BuildBuildingListener appartementLst){
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo( null );
        this.setSize(new Dimension(600, 300));

        solarBuildBtn = new JButton("Solar Panel");
        solarBuildBtn.addActionListener(solarLst);
        solarBuildBtn.setIcon(new ImageIcon("/res/img/solar.png"));
        windBuildBtn = new JButton("Wind turbine");
        windBuildBtn.addActionListener(windLst);
        hydroBuildBtn = new JButton("Hydro power Plant");
        hydroBuildBtn.addActionListener(waterLst);
        coalBuildBtn = new JButton("Coal power plant");
        coalBuildBtn.addActionListener(coalLst);
        gasBuildBtn = new JButton("Gas power plant");
        gasBuildBtn.addActionListener(gasLst);
        nuclearBuildBtn = new JButton("Nuclear power plant");
        nuclearBuildBtn.addActionListener(nuclearLst);
        houseBuildBtn = new JButton("Home");
        houseBuildBtn.addActionListener(houseLst);
        appartementBuildBtn = new JButton("Appartement building");
        appartementBuildBtn.addActionListener(appartementLst);


        FlowLayout panelLayout = new FlowLayout(FlowLayout.CENTER);

        JPanel renewablePanel = new JPanel();
        renewablePanel.add(solarBuildBtn);
        renewablePanel.add(windBuildBtn);
        renewablePanel.add(hydroBuildBtn);
        renewablePanel.setLayout(panelLayout);
        JPanel fossilPanel = new JPanel();
        fossilPanel.add(coalBuildBtn);
        fossilPanel.add(gasBuildBtn);
        fossilPanel.add(nuclearBuildBtn);
        fossilPanel.setLayout(panelLayout);
        JPanel homePanel = new JPanel();
        homePanel.add(houseBuildBtn);
        homePanel.add(appartementBuildBtn);
        homePanel.setLayout(panelLayout);

        menuPane = new JTabbedPane();
        menuPane.setPreferredSize( new Dimension( 500, 250 ) );
        menuPane.setTabPlacement( JTabbedPane.TOP );
        menuPane.setVisible(true);
        menuPane.addTab("Renewable energy", renewablePanel);
        menuPane.addTab("Fossil energy", fossilPanel);
        menuPane.addTab("Home", homePanel);

        this.add(menuPane);
        this.pack();
        this.setResizable(false);
    }

    @Override
    public void printErrorMessage(String message) {
        //TODO
    }

    @Override
    public void placeBuilding() {

    }
}
