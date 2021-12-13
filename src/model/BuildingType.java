package model;

public enum BuildingType {
    NUCLEAR(50000), GAS(15000), COAL(25000), WATER(35000), SOLAR(5000), WIND(10000),  HOUSE(5000), APPARTEMENT(15000), FOSSIL, RENEWABLE, HOME;

    int price;

    BuildingType(){}

    BuildingType(int price){
        this.price = price;
    }
}
