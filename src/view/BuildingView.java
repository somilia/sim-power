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

public class BuildingView extends JButton implements BuildingViewable{

    private BufferedImage imageWithoutMoney;
    private BufferedImage imageWithMoney;

    private Boolean collectMoney;

    private int positionX;
    private int positionY;

    private BuildingListener buildingListener;

    public BuildingView(BuildingType type, GameController gameController){

        this.collectMoney=false;
        this.buildingListener = new BuildingListener(gameController,this);
        this.addActionListener(buildingListener);

        URL resourceWithoutMoney;
        URL resourceWithMoney=null;

        switch (type) { // choisir l'image en fonction du type de bâtiment

            case NUCLEAR:
                resourceWithoutMoney = getClass().getResource("/res/img/nuclear.png");
                break;
            case GAS:
                resourceWithoutMoney = getClass().getResource("/res/img/gaspower.png");
                break;
            case COAL:
                resourceWithoutMoney = getClass().getResource("/res/img/coalpower.png");
                break;
            case WATER:
                resourceWithoutMoney = getClass().getResource("/res/img/hydropower.png");
                break;
            case SOLAR:
                resourceWithoutMoney = getClass().getResource("/res/img/panel.png");
                break;
            case WIND:
                resourceWithoutMoney = getClass().getResource("/res/img/turbine.png");
                break;
            case HOUSE:
                resourceWithoutMoney = getClass().getResource("/res/img/house.png");
                resourceWithMoney = getClass().getResource("/res/img/nuclear.png");
                break;
            case APPARTEMENT:
                resourceWithoutMoney = getClass().getResource("/res/img/appartement.png");
                resourceWithMoney = getClass().getResource("/res/img/nuclear.png");
                break;
            default:
                resourceWithoutMoney = getClass().getResource("/res/img/grass.png");
                break;
        };

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

    public void setImageMoneyCollect(){
        this.collectMoney = true;
        buildingListener.setCanCollectMoney(true);
        this.repaint();
    }

    public void setImageNoMoney(){
        this.collectMoney = false;
        this.repaint();
    }



    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
