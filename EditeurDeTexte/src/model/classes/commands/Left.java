package model.classes.commands;

import model.classes.Cursor;
import model.interfaces.ICommandVisitor;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering Left.java
 */
public class Left implements ICommandVisitor {

	@Override
	public void visit(String textInput) {
		Cursor.instance().setCurrentPosition(Cursor.instance().getCurrentPosition() -1 );
	}

}
