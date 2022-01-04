package patterns;

public interface InformationObserver {

    public void update(int population, int populationAvailable, int populationMax, double energyProduced, double energyPrice, double pollutionRate, int userMoney);
}
