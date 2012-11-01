package controllers;

import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * 1 nov. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         FactoryController.java
 */
public class FactoryController {
    public static Controller createController() {
        return new Controller();
    }

    public static DisplayText createDisplayText(JLabel inputText) {
        return new DisplayText(inputText);
    }

    public static InputText createInputText(JTextField input) {
        return new InputText(input);
    }

    public static ValidateButton createValidateButton(JTextField input) {
        return new ValidateButton(input);
    }
}
