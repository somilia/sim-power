package view;

import javax.swing.*;

public class MenuFrame extends JFrame implements MenuViewable{

    private JTabbedPane menuPane;

    private JButton coalBuildBtn;
    private JButton gasBuildBtn;
    private JButton nuclearBuildBtn;

    private JButton solarBuildBtn;
    private JButton windBuildBtn;
    private JButton hydrauBuildBtn;

    public MenuFrame(){
        this.setDefaultCloseOperation(HIDE_ON_CLOSE );
    }

    @Override
    public void printErrorMessage(String message) {

    }

    @Override
    public void placeBuilding() {

    }
}
