package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.print.DocFlavor.INPUT_STREAM;
import javax.swing.JTextField;

import model.classes.CommandExecutor;
import model.classes.Cursor;

/**
 * The class used to manage the text typed by the user and to send it to the
 * model by invoking the proper command or by adding the text. <br/>
 * 1 nov. 2012 - EditeurDeTexte.
 * 
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

	/**
	 * This method processes the text typed by the user and checks if it
	 * contains a code associated with a command. If a command is associated,
	 * the method is going to call the visit method of the command by using the
	 * CommandExecutor and else it is going to add the text.
	 * 
	 * @param textInput, the text typed by the user.
	 */
	public static void manageInputText(String textInput) {
		String command = new String(textInput);
		if (textInput.length() >= 2) {
			command = command.substring(0, 2);
		}
		if (CommandExecutor.getCommandExecutorInstance().getCommandsList()
				.containsKey(command)) {
			CommandExecutor.getCommandExecutorInstance().getCommandsList()
					.get(command).visit(textInput);
		} else {
			Cursor.instance().getCurrentLine().addUnderCursor(textInput);
		}
	}
}
