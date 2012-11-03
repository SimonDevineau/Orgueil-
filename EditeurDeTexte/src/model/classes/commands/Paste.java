package model.classes.commands;

import model.classes.Cursor;
import model.interfaces.ICommandVisitor;
import model.interfaces.ILine;
import model.interfaces.ISection;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau Ecole des Mines de Nantes Major in Computer and
 *         Information System Engineering Paste.java
 */
public class Paste implements ICommandVisitor {

	@Override
	public void visit(String textInput) {
		ISection current = Cursor.getCursorInstance().getCurrentSection();
		if (current != null
				&& Cursor.getCursorInstance().getCurrentDocument()
						.getBufferMemory().peek() instanceof ILine) {
			current.getText().insertLine(
					(ILine) Cursor.getCursorInstance().getCurrentDocument()
							.getBufferMemory().pop());

		}
	}
}
