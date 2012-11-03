package model.classes.commands;

import model.classes.Cursor;
import model.interfaces.ICommandVisitor;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         BackSlash.java
 */
class BackSlash implements ICommandVisitor {
	
	@Override
	public void visit() {
		Cursor.getCursorInstance().getCurrentLine().replaceUnderCursor("\\");
	}
	
}
