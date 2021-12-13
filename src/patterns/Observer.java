package patterns;

public interface Observer {

    public void update(int population, int populationAvailable, int populationMax, double energyProduced, double energyPrice, double pollutionRate, int userMoney);
}
