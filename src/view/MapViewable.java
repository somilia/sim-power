package view;

import model.Box;
import model.BuildingType;

public interface MapViewable {

    public void initializeMap(Box[][] boxes);
    public void addBuilding(BuildingType buildingType, int positionX, int positionY);
}
