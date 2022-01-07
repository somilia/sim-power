package view;

import controller.BuildBuildingListener;
import model.BuildingType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


/**
 * Cette classe est une vue qui permet d'afficher le menu du jeu qui permet d'acheter des bâtiment.
 * Elle hérite de JFrame car elle correspond à une fenêtre
 */
public class MenuFrame extends JFrame implements MenuViewable{

    private JTabbedPane menuPane;//< vue qui contient plusieurs onglets, un pour chaque catégorie de bâtiment

    private JLabel error1;//< labels qui permettent d'afficher un message d'erreur
    private JLabel error2;
    private JLabel error3;

    private JButton coalBuildBtn;//< boutons pour acheter un bâtiment en particulier
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

        solarBuildBtn = new JButton("<html>Solar Panel<br>"+BuildingType.SOLAR.price+"$");//On définit pour chaque bouton son titre, son listener et son icon
        solarBuildBtn.addActionListener(solarLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/panel.png")));
            solarBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        solarBuildBtn.repaint();

        windBuildBtn = new JButton("<html>Wind turbine<br>"+BuildingType.WIND.price+"$");
        windBuildBtn.addActionListener(windLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/turbine.png")));
            windBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        windBuildBtn.repaint();

        hydroBuildBtn = new JButton("<html>Hydro power plant<br>"+BuildingType.WATER.price+"$");
        hydroBuildBtn.addActionListener(waterLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/hydropower.png")));
            hydroBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        hydroBuildBtn.repaint();

        coalBuildBtn = new JButton("<html>Coal power plant<br>"+BuildingType.COAL.price+"$");
        coalBuildBtn.addActionListener(coalLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/coalpower.png")));
            coalBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        coalBuildBtn.repaint();

        gasBuildBtn = new JButton("<html>Gas power plant<br>"+BuildingType.GAS.price+"$");
        gasBuildBtn.addActionListener(gasLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/gaspower.png")));
            gasBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        gasBuildBtn.repaint();

        nuclearBuildBtn = new JButton("<html>Nuclear power plant<br>"+BuildingType.NUCLEAR.price+"$");
        nuclearBuildBtn.addActionListener(nuclearLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/nuclear.png")));
            nuclearBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        nuclearBuildBtn.repaint();

        houseBuildBtn = new JButton("<html>House<br>"+BuildingType.HOUSE.price+"$");
        houseBuildBtn.addActionListener(houseLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/house.png")));
            houseBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        houseBuildBtn.repaint();

        appartementBuildBtn = new JButton("<html>Appartement Building<br>"+BuildingType.APPARTEMENT.price+"$");
        appartementBuildBtn.addActionListener(appartementLst);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/img/appartement.png")));
            appartementBuildBtn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        appartementBuildBtn.repaint();

        error1 = new JLabel();
        error2 = new JLabel();
        error3 = new JLabel();
        error1.setVisible(false);
        error2.setVisible(false);
        error3.setVisible(false);

        FlowLayout panelLayout = new FlowLayout(FlowLayout.CENTER);


        JPanel renewablePanel = new JPanel();//On créé un panel pour contenir les boutons dans chaque onglet du JTabbedPane.
        renewablePanel.add(solarBuildBtn);
        renewablePanel.add(windBuildBtn);
        renewablePanel.add(hydroBuildBtn);
        renewablePanel.add(error1, BorderLayout.SOUTH);
        error1.setBorder(BorderFactory.createEmptyBorder(40, 10, 30, 10));
        renewablePanel.setLayout(panelLayout);

        JPanel fossilPanel = new JPanel();
        fossilPanel.add(coalBuildBtn);
        fossilPanel.add(gasBuildBtn);
        fossilPanel.add(nuclearBuildBtn);
        fossilPanel.add(error2, BorderLayout.SOUTH);
        error2.setBorder(BorderFactory.createEmptyBorder(40, 10, 30, 10));
        fossilPanel.setLayout(panelLayout);

        JPanel homePanel = new JPanel();
        homePanel.add(houseBuildBtn);
        homePanel.add(appartementBuildBtn);
        homePanel.add(error3, BorderLayout.SOUTH);
        error3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        homePanel.setLayout(panelLayout);

        menuPane = new JTabbedPane();
        menuPane.setPreferredSize( new Dimension(500, 250));
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
        error1.setText(message);
        error1.setVisible(true);
        error2.setText(message);
        error2.setVisible(true);
        error3.setText(message);
        error3.setVisible(true);
        Timer timer = new Timer(3000, arg0 -> {
            error1.setVisible(false);
            error2.setVisible(false);
            error3.setVisible(false);
        });
        timer.setRepeats(false);
        timer.start();
    }

}
