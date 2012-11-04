package tests;

import utilities.Utilities;
import controllers.FactoryController;

/**
 * 1 nov. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         Main.java
 */
public class LaunchEditor {
    public static void main(String[] args) {
        Utilities.changeMenuBar();
        FactoryController.createController();
    }
}
