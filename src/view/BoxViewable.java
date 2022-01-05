package view;

public interface BoxViewable {
    void repaint();
    void addBuilding(BuildingViewable buildingView);
    void removeBuilding(BuildingViewable buildingView);
    BuildingViewable getBuildingView();
}
