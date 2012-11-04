package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.print.DocFlavor.INPUT_STREAM;
import javax.swing.JTextField;

import model.classes.CommandExecutor;
import model.classes.Cursor;

/**
 * 1 nov. 2012 - EditeurDeTexte.
 * @author Simon Devineau Ecole des Mines de Nantes Major in Computer and
 *         Information System Engineering InputText.java
 */
public class ManageInputText implements KeyListener {
    private JTextField input;

    /**
     * 
     */
    public ManageInputText(JTextField input) {
        this.input = input;
        input.addKeyListener(this);
    }

    /**
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent aE) {
    }

    /**
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent aE) {
        if (aE.getKeyCode() == KeyEvent.VK_ENTER) {
            String textInput = input.getText();
            manageInputText(textInput);
            input.setText("");
        }
    }

    /**
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped(KeyEvent aE) {
    }

    public static void manageInputText(String textInput) {
        String command = new String(textInput);
        if (textInput.length() >= 2) {
            command = command.substring(0, 2);
        }
        if (CommandExecutor.getCommandExecutorInstance().getCommandsList()
                .containsKey(command)) {
            CommandExecutor.getCommandExecutorInstance().getCommandsList()
                    .get(command).visit(textInput);
        }
        else {
            Cursor.instance().getCurrentLine().addUnderCursor(textInput);
        }
    }
}
