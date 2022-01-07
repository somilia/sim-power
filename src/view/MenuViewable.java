package view;

/**
 * Interface qui contient toutes les méthodes nécessaires à l'affichage de la vue du menu
 */
public interface MenuViewable {

    /**
     * afficher un message d'erreur
     * @param message à afficher
     */
    void printErrorMessage(String message);

    /**
     * @param b si Vrai alors rend visible la vue, si Faux alors rend invisible la vue
     */
    void setVisible(boolean b);
}
