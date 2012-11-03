package model.classes.commands;

import model.classes.Cursor;
import model.interfaces.ICommandVisitor;
import model.interfaces.ILine;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         DeleteAndSave.java
 */
public class DeleteAndSave implements ICommandVisitor {

	@Override
	public void visit(String textInput) {
		ILine current = Cursor.getCursorInstance().getCurrentLine();
		if (current != null) {
            //TODO FAUT IL PASSER PAR UN CONSTRUCTEUR PAR DEFAULT
            Cursor.getCursorInstance().getCurrentDocument().getBufferMemory()
                    .push(current);
            Cursor.getCursorInstance().getCurrentSection().getText()
                    .removeLine(current);
        }
	}
}
