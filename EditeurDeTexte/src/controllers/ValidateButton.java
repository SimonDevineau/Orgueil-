package controllers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

/**
 * A standard JButton which is just used to validate the command typed. <br/>
 * 1 nov. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         ValidateButton.java
 */
@SuppressWarnings("serial")
public class ValidateButton extends AbstractAction {
    private JTextField input;

    /**
     * 
     */
    public ValidateButton(JTextField input) {
        this.putValue(NAME, "Validate");
        this.putValue(SHORT_DESCRIPTION,
                "Press this button to validate your input");
        this.input = input;
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent aArg0) {
        ManageInputText.manageInputText(this.input.getText());
        this.input.setText("");
    }
}
