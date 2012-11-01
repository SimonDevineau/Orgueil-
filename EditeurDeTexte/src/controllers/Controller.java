package controllers;

import java.util.Observable;
import java.util.Observer;

import model.classes.Cursor;
import model.classes.Editor;
import model.interfaces.IDocument;
import view.MainForm;

/**
 * 1 nov. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         Controller.java
 */
public class Controller implements Observer {
    private MainForm       view = new MainForm();
    private DisplayText    display;
    private InputText      inputText;
    private ValidateButton validateButton;

    /**
     * 
     */
    public Controller() {
        this.view = new MainForm();
        display = FactoryController.createDisplayText(view.getText());
        Cursor.getCursorInstance().getCurrentDocument().addObserver(display);
        inputText = FactoryController.createInputText(view.getCommand());
        view.getCommand().addKeyListener(inputText);
        validateButton = FactoryController.createValidateButton(view
                .getCommand());
        view.getValidate().setAction(validateButton);
    }

    /**
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable aO, Object aArg) {
        for (IDocument documents : Editor.getEditor().getDocuments()) {
            documents.addObserver(display);
        }
    }
}
