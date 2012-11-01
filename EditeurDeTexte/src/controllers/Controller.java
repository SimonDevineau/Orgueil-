package controllers;

import model.classes.Cursor;
import model.classes.Factory;
import view.MainForm;

/**
 * 1 nov. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         Controller.java
 */
public class Controller {
    private MainForm view = new MainForm();

    /**
     * 
     */
    public Controller() {
        this.view = new MainForm();
        DisplayText display = FactoryController.createDisplayText(view
                .getText());
        Cursor.getCursorInstance().getCurrentDocument().addObserver(display);
        InputText inputText = FactoryController.createInputText(view
                .getCommand());
        view.getCommand().addKeyListener(inputText);
        ValidateButton validateButton = FactoryController
                .createValidateButton(view.getCommand());
        view.getValidate().setAction(validateButton);
    }
}
