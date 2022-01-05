package patterns;

public interface InformationObserver {

    void update(int population, int populationAvailable, int populationMax, double energyProduced, double energyPrice, double pollutionRate, int userMoney);
}
