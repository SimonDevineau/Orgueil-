package controllers;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.classes.Cursor;
import model.interfaces.IDocument;

import view.MainForm;

/**
 * 1 nov. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         DisplayText.java
 */
public class DisplayText implements Observer {
    /**
     * The view of the Editor
     */
    private JLabel inputText;

    /**
     * @param view
     *            , the view of the editor
     * @param model
     *            ,the document which needs to be displayed
     */
    public DisplayText(JLabel inputText) {
        this.inputText = inputText;
    }

    /**
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable aArg0, Object aArg1) {
        if (aArg0 instanceof IDocument)
            inputText.setText(Cursor.getCursorInstance().getCurrentDocument()
                    .toHTML());
    }
}
