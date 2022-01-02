package view;

import controller.BuildBuildingListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

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
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/panel.png")));
            solarBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        solarBuildBtn.repaint();
        windBuildBtn = new JButton("Wind turbine");
        windBuildBtn.addActionListener(windLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/turbine.png")));
            windBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        windBuildBtn.repaint();
        hydroBuildBtn = new JButton("Hydro power Plant");
        hydroBuildBtn.addActionListener(waterLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/turbine.png")));
            hydroBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        hydroBuildBtn.repaint();
        coalBuildBtn = new JButton("Coal power plant");
        coalBuildBtn.addActionListener(coalLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/turbine.png")));
            coalBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        coalBuildBtn.repaint();
        gasBuildBtn = new JButton("Gas power plant");
        gasBuildBtn.addActionListener(gasLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/turbine.png")));
            gasBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        gasBuildBtn.repaint();
        nuclearBuildBtn = new JButton("Nuclear power plant");
        nuclearBuildBtn.addActionListener(nuclearLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/turbine.png")));
            nuclearBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        nuclearBuildBtn.repaint();
        houseBuildBtn = new JButton("Home");
        houseBuildBtn.addActionListener(houseLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/turbine.png")));
            houseBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        houseBuildBtn.repaint();
        appartementBuildBtn = new JButton("Appartement building");
        appartementBuildBtn.addActionListener(appartementLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/turbine.png")));
            appartementBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        appartementBuildBtn.repaint();


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
