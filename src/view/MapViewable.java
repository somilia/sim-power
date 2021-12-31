package view;

import model.Box;
import model.BuildingType;

public interface MapViewable {

    public void initializeMap(Box[][] boxes);
    public void addBuilding(BuildingView buildingView, int positionX, int positionY);
    public void removeBuilding(BuildingView buildingView, int positionX, int positionY);
}
