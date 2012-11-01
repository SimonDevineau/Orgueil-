package tests;

import javax.swing.text.Document;

import model.classes.Factory;
import model.interfaces.IDocument;
import controllers.Controller;
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
        Controller controller = FactoryController.createController();
        
    }
}
