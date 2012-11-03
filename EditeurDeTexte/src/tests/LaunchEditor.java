package tests;

import javax.swing.text.Document;

import utilities.Utilities;

import model.classes.Cursor;
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
        Utilities.changeMenuBar();
        Controller controller = FactoryController.createController();
        System.out.println("current Line " + Cursor.instance().getCurrentLine());
        System.out.println("current text " + Cursor.instance().getCurrentText());
        System.out.println("current section " + Cursor.instance().getCurrentSection());
        System.out.println("current doc " + Cursor.instance().getCurrentDocument());
    }
}
