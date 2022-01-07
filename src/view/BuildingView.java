package view;

import controller.BuildingListener;
import controller.GameController;
import model.BuildingType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Classe qui correspond à la vue d'un bâtiment
 * Cette classe hérite de JButton ce qui simplifie le fait de pouvoir cliquer sur les bâtiments
 * Cette classe implémente l'interface BuildingViewable pour implémenter toutes les méthodes nécessaires au bon affichage de la vue
 */
public class BuildingView extends JButton implements BuildingViewable{

    private BufferedImage imageWithoutMoney;
    private BufferedImage imageWithMoney;

    private Boolean collectMoney;//< Vrai si on peut cliquer sur la vue pour collecter l'argent

    private int positionX;//< abscisse de la vue
    private int positionY;//< ordonnées de la vue

    private BuildingListener buildingListener;//< Listener qui gère les clics sur la vue

    public BuildingView(BuildingType type, GameController gameController){

        this.collectMoney=false;
        this.buildingListener = new BuildingListener(gameController,this);
        this.addActionListener(buildingListener);

        URL resourceWithoutMoney;
        URL resourceWithMoney=null;

        switch (type) { // choisit l'image en fonction du type de bâtiment

            case NUCLEAR -> resourceWithoutMoney = getClass().getResource("/res/img/nuclear.png");
            case GAS -> resourceWithoutMoney = getClass().getResource("/res/img/gaspower.png");
            case COAL -> resourceWithoutMoney = getClass().getResource("/res/img/coalpower.png");
            case WATER -> resourceWithoutMoney = getClass().getResource("/res/img/hydropower.png");
            case SOLAR -> resourceWithoutMoney = getClass().getResource("/res/img/panel.png");
            case WIND -> resourceWithoutMoney = getClass().getResource("/res/img/turbine.png");
            case HOUSE -> {
                resourceWithoutMoney = getClass().getResource("/res/img/house.png");
                resourceWithMoney = getClass().getResource("/res/img/house_money.png");
            }
            case APPARTEMENT -> {
                resourceWithoutMoney = getClass().getResource("/res/img/appartement.png");
                resourceWithMoney = getClass().getResource("/res/img/appartement_money.png");
            }
            default -> resourceWithoutMoney = getClass().getResource("/res/img/grass.png");
        }

        try {
            assert resourceWithoutMoney != null;
            imageWithoutMoney = ImageIO.read(resourceWithoutMoney);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(resourceWithMoney!=null){
            try{
                imageWithMoney = ImageIO.read(resourceWithMoney);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        this.setSize(32,32);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(collectMoney){
            g.drawImage(imageWithMoney, 0, 0, null);
        }
        else{
            g.drawImage(imageWithoutMoney, 0, 0, null);
        }
    }

    /**
     * modifie l'icon de la vue pour afficher celui sans argent pour représenter le fait que la collecte d'argent n'est plus possible
     */
    public void setImageMoneyCollect(){
        this.collectMoney = true;
        buildingListener.setCanCollectMoney(true);
        this.repaint();
    }

    /**
     * @return l'abscisse de la vue
     */
    public void setImageNoMoney(){
        this.collectMoney = false;
        this.repaint();
    }



    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    @Override
    public void setPosition(int x, int y) {
        this.positionX=x;
        this.positionY=y;
    }

}
