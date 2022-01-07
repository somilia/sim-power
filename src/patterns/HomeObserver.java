package patterns;

/**
 * Cette interface a le rôle d'Observer dans le design pattern Observer.
 * La classe observable va appeler la méthode de cette interface à chaque fois quelle met à jour les données correspondant à la vue des habitation et notamment à la récolte d'argent
 */
public interface HomeObserver {
    /**
     * Permet de notifier la classe qui implémente cette interface que l'élément à la positon (x,y) a été mis à jour
     * @param x abscisse de l'élément
     * @param y ordonnées de l'élément
     */
    void update(int x, int y);
}
