package model;

import java.util.Random;

/**
 * Cette classe modélise les cases qui composent la Map du jeu
 * Cette classe peut être composé d'une instance d'un bâtiment ou vide.
 * Cette classe contient aussi des attributs permettant de modéliser les ressources du jeu qui sont soi dynamiques soit statiques
 */
public class Box
{
    private static final double MEAN = 0.0;// la moyenne de la variation est à 0 c'est à dire que la valeur moyenne des caractéristiques est celle initialisée au départ et est constante
    private static final double STANDARD_DERIVATION = 0.2;

    private double wind;
    private double sun;
    private double water;

    private boolean gas;
    private boolean coal;
    private boolean uranium;

    private boolean containEnergySource;//< vrai si la Box l'attribut energySource n'est pas null et référence une instance d'un objet héritant de la classes EnergySources
    private boolean containHome;//< vrai si la Box l'attribut home n'est pas null et référence une instance d'un bâtiment héritant de la classes Home

    private EnergySources energySource;//< est null si la Box ne contient pas de source d'énergie, sinon l'attribut référence une instance d'un objet héritant de la classe EnergySources
    private Home home;//< est null si la Box ne contient pas d'habitation, sinon l'attribut référence une instance d'un objet héritant de la classe Home

    public Box(double _wind, double _sun, double _water, boolean _gas, boolean _coal, boolean _uranium){
        wind = _wind;
        sun = _sun;
        water = _water;
        gas = _gas;
        coal = _coal;
        uranium = _uranium;
    }

    /**
     * Ajoute un bâtiment à la case.
     * C'est à dire que soit l'attribut energySource soit l'attribut home reçoit une référence sur un objet du type respectif
     * Actualise aussi la valeur de containEnergySource ou de containHome
     *
     * @param type type du bâtiment paramètre
     */
    public void addBuilding(BuildingType type){
        switch (type) {
            case SOLAR -> {
                this.energySource = new SolarPanel(this);
                this.containEnergySource = true;
            }
            case WIND -> {
                this.energySource = new WindTurbine(this);
                this.containEnergySource = true;
            }
            case WATER -> {
                this.energySource = new HydroPowerPlant(this);
                this.containEnergySource = true;
            }
            case NUCLEAR -> {
                this.energySource = new NuclearPowerPlant(this);
                this.containEnergySource = true;
            }
            case GAS -> {
                this.energySource = new GasPowerPlant(this);
                this.containEnergySource = true;
            }
            case COAL -> {
                this.energySource = new CoalPowerPlant(this);
                this.containEnergySource = true;
            }
            case HOUSE -> {
                this.home = new House();
                this.containHome = true;
            }
            case APPARTEMENT -> {
                this.home = new AppartementBuilding();
                this.containHome = true;
            }
            default -> throw new IllegalArgumentException("Building type for energySource not valid");
        }
    }

    /**
     * Simule la variation du vent sur la case
     * On utilise une loi normale centrée réduite, on a juste à multiplier par la variance v et ajouter la moyenne m pour obtenir une loi normale N(m,v)
     */
    public void randomWindGen() {
        Random r = new Random();
        wind += r.nextGaussian()* STANDARD_DERIVATION +MEAN;
    }

    /**
     * Simule la variation du soleil sur la case
     * On utilise une loi normale centrée réduite, on a juste à multiplier par la variance v et ajouter la moyenne m pour obtenir une loi normale N(m,v)
     */
    public void randomSunGen() {
        Random r = new Random();
        sun += r.nextGaussian()* STANDARD_DERIVATION +MEAN;
    }

    /**
     * Simule la variation de l'eau sur la case, s'il y en a de base
     * On utilise une loi normale centrée réduite, on a juste à multiplier par la variance v et ajouter la moyenne m pour obtenir une loi normale N(m,v)
     */
    public void randomWaterGen() {
        if (water != 0) {                   //si water = 0 alors il n'y a pas d"eau sur la case donc pas besoin de la faire varier
            Random r = new Random();
            water += r.nextGaussian() * STANDARD_DERIVATION + MEAN;
        }
    }

    public boolean getContainEnergySource(){
        return this.containEnergySource;
    }

    public boolean getContainHome(){
        return this.containHome;
    }

    public EnergySources getEnergySource() {
        return energySource;
    }

    public Home getHome() {
        return home;
    }

    public double getWind() {
        return wind;
    }

    public double getSun() {
        return sun;
    }

    public double getWater() {
        return water;
    }

    public boolean hasGas() {
        return gas;
    }

    public boolean hasCoal() {
        return coal;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public boolean hasUranium() {
        return uranium;
    }
}