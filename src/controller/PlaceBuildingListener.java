package controller;

import model.BuildingType;
import model.Map;
import view.BuildingView;
import view.MapPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PlaceBuildingListener implements MouseMotionListener, MouseListener {

    private GameController gameController;

    private BuildingType buildingType;
    private BuildingView buildingView;

    private boolean isPlacingBuilding;
    private boolean boxIsAvailable;

    public PlaceBuildingListener(GameController gameController){
        this.gameController = gameController;
        this.isPlacingBuilding = false;
        this.boxIsAvailable=false;
    }

    public void activatePlacingBuilding(BuildingView buildingView, BuildingType buildingType){
        this.buildingView = buildingView;
        this.buildingType = buildingType;
        this.isPlacingBuilding = true;
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {

        System.out.println("mouse moved out of if");
        if(isPlacingBuilding){

            System.out.println("mouse moved : "+e.getX()+" "+e.getY());
            buildingView.setLocation(e.getX()+5, e.getY()+5);

            if(gameController.getModel().boxIsEmpty(e.getX()/(MapPanel.MAP_PANEL_WIDTH/Map.NB_BOX_X), e.getY()/(MapPanel.MAP_PANEL_HEIGHT/Map.NB_BOX_Y))){
                boxIsAvailable=true;
                System.out.println("box "+(e.getX()/(MapPanel.MAP_PANEL_WIDTH/Map.NB_BOX_X))+" "+(e.getY()/(MapPanel.MAP_PANEL_HEIGHT/Map.NB_BOX_Y))+" is available");
                //TODO on met en verre l'objet
            }
            else{
                System.out.println("box "+e.getX()+" "+e.getY()+" is not free");
                boxIsAvailable = false;
                // TODO mettre en rouge l'objet
            }
        }
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse clicked out of if");
        //TODO if Buildingtype == SOLAR || ... || (buildingType == coal && box[x][y].getCOntainCoal) || .....
        if(boxIsAvailable){
            System.out.println("mouse clicked"+(e.getX()/(MapPanel.MAP_PANEL_WIDTH/Map.NB_BOX_X))+" "+(e.getY()/(MapPanel.MAP_PANEL_HEIGHT/Map.NB_BOX_Y)));
            isPlacingBuilding=false;
            gameController.getModel().buildBuilding(e.getX()/(MapPanel.MAP_PANEL_WIDTH/Map.NB_BOX_X),e.getY()/(MapPanel.MAP_PANEL_HEIGHT/Map.NB_BOX_Y), buildingType);
            gameController.getMapPanel().addBuilding(buildingView, e.getX()/(MapPanel.MAP_PANEL_WIDTH/Map.NB_BOX_X),e.getY()/(MapPanel.MAP_PANEL_HEIGHT/Map.NB_BOX_Y));
        }
    }




    ////////////////////// useless
    /**
     *
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
