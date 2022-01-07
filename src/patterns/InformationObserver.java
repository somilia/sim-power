package patterns;

/**
 * Cette interface a le rôle d'Observer dans le design pattern Observer.
 * La classe observable va appeler la méthode de cette interface à chaque fois quelle met à jour les donnes correspondant aux données globales
 */
public interface InformationObserver {

    /**
     * Cette méthode est appelée pour actualiser les données affichées par la vues lorsque celles-ci sont modifiées dans le modèle
     * @param population nombre d'habitants de la map
     * @param populationAvailable nombre d'habitants potentiels mais qui n'ont pas de place (pas de Building de type Home) pour appartenir à la ville
     * @param populationMax nombre total d'hbts que peut acceuillir touts les Building de type Home de la Map
     * @param energyProduced somme des enregies produites par toutes les EnergySources
     * @param energyPrice ratio entre energyProduced et et population
     * @param pollutionRate ratio entre les source d'énergies fossiles et sources d'énergies renouvelables
     * @param userMoney argent du joueur
     */
    void update(int population, int populationAvailable, int populationMax, double energyProduced, double energyPrice, double pollutionRate, int userMoney);
}
