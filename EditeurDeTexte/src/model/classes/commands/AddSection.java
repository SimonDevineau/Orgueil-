package model.classes.commands;

import model.classes.Cursor;
import model.classes.Factory;
import model.interfaces.ICommandVisitor;

/**
 * 1 nov. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         AddSection.java
 */
public class AddSection implements ICommandVisitor {
	
	@Override
	public void visit() {
		Cursor.getCursorInstance().getCurrentDocument()
        .addSection(Factory.createSection());
	}
	
}
