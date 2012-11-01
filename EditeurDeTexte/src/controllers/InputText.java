package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import model.classes.CommandExecutor;
import model.classes.Cursor;

/**
 * 1 nov. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         InputText.java
 */
public class InputText implements KeyListener {
    private JTextField input;

    /**
     * 
     */
    public InputText(JTextField input) {
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
        if (aE.equals(KeyEvent.VK_ENTER)) {
            String textInput = input.getText();
            manageInputText(textInput);
        }
    }

    /**
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped(KeyEvent aE) {
    }

    public static void manageInputText(String textInput) {
        if (!textInput.startsWith("\\")
                || (textInput.length() == 2 && textInput.startsWith("\\") && textInput
                        .endsWith("\\"))) {// meands begin by \
            Cursor.getCursorInstance().getCurrentLine()
                    .addUnderCursor(textInput);
        }
        else {
            // Visit the current document in order to call the suitable commands class 
            Cursor.getCursorInstance()
                    .getCurrentDocument()
                    .accept(CommandExecutor.getCommandExecutorInstance()
                            .getCommandsList().get(textInput));
            // Visit the current line in order to call the suitable commands class
            Cursor.getCursorInstance()
                    .getCurrentLine()
                    .accept(CommandExecutor.getCommandExecutorInstance()
                            .getCommandsList().get(textInput));
            // Visit the current section in order to call the suitable commands class
            Cursor.getCursorInstance()
                    .getCurrentSection()
                    .accept(CommandExecutor.getCommandExecutorInstance()
                            .getCommandsList().get(textInput));
        }
    }
}
