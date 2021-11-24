public enum BuildingType {
    HOME, NUCLEAR, SOLAR, HYDRAULIC, COAL 
}
public class Box
{
    private int wind;
    private int sun;
    private int water;

    private boolean gas;
    private boolean coal;
    private boolean uranium;

    private boolean building;
    private enum BuildingType buildingType;


    public Box(){

        building=false;
    }

}