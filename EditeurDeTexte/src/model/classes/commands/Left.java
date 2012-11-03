package model.classes.commands;

import model.classes.Cursor;
import model.interfaces.ICommandVisitor;
import model.interfaces.ILine;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering Left.java
 */
class Left implements ICommandVisitor {

	@Override
	public void visit() {
		ILine current = Cursor.getCursorInstance().getCurrentLine();
		current.setCursorLocation((current.getCursorLocation() == 0) ? 0
				: current.getCursorLocation() - 1);
	}

}
