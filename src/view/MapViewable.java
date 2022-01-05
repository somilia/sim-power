package view;

import controller.PlaceBuildingListener;
import model.Box;
import patterns.HomeObserver;

public interface MapViewable extends HomeObserver {

    void initializeMap(Box[][] boxes);
    void addBuilding(BuildingViewable buildingView, int positionX, int positionY);
    void removeBuilding(BuildingViewable buildingView, int positionX, int positionY);
    void addPlaceBuildingListener(PlaceBuildingListener placeBuildingListener);

    void repaint();
}
