package model.classes.commands;

import model.classes.Cursor;
import model.interfaces.ICommandVisitor;
import model.interfaces.IDocument;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         Pop.java
 */
public class Pop implements ICommandVisitor{

	@Override
	public void visit(String textInput) {
		IDocument current = Cursor.instance().getCurrentDocument();
		current.getBufferMemory().pop();
	}
}
