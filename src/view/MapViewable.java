package view;

import controller.PlaceBuildingListener;
import model.Box;
import model.BuildingType;

public interface MapViewable extends HomeObserver{

    public void initializeMap(Box[][] boxes);
    public void addBuilding(BuildingView buildingView, int positionX, int positionY);
    public void removeBuilding(BuildingView buildingView, int positionX, int positionY);
    public void addPlaceBuildingListener(PlaceBuildingListener placeBuildingListener);

    void repaint();
}
