package controllers;

import java.util.Observable;
import java.util.Observer;

import model.classes.BufferMemory;
import model.classes.Cursor;
import model.classes.Factory;
import view.MainForm;

/**
 * 1 nov. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         Controller.java
 *         This class implements the controller which makes the link between the
 *         Editor model and the view.
 */
public class Controller implements Observer {
    private MainForm        view = new MainForm();
    private ManageInputText inputText;
    private ValidateButton  validateButton;
    private ManageMemory    manageMemory;

    /**
     * Create the view and initialize the controller and observer
     */
    public Controller() {
        this.view = new MainForm();
        this.inputText = FactoryController.createInputText(this.view
                .getCommand());
        this.view.getCommand().addKeyListener(this.inputText);
        this.validateButton = FactoryController.createValidateButton(this.view
                .getCommand());
        this.view.getValidate().setAction(this.validateButton);
        Cursor.instance().addObserver(this);
        Cursor.instance().setCurrentDocument(Factory.createDocument());
        this.manageMemory = new ManageMemory(this.view.getBufferMemory());
        BufferMemory.instance().addObserver(this.manageMemory);
    }

    /**
     * @return the controller in charge of managing the text typed by the user.
     */
    public ManageInputText getInputTextManager() {
        return this.inputText;
    }

    /**
     * @return an access to the validate button.
     */
    public ValidateButton getValidateButton() {
        return this.validateButton;
    }

    /**
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     *      Add to all the documents, an observer to be able to display the
     *      document when a change is done
     */
    @Override
    public void update(Observable aO, Object aArg) {
        this.view.getText().setText(
                Cursor.instance().getCurrentDocument().toString());
        this.view.repaint();
    }
}
