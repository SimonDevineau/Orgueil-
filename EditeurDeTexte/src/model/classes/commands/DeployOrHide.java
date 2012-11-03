package model.classes.commands;

import model.classes.Cursor;
import model.interfaces.ICommandVisitor;
import model.interfaces.ISection;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         DeployOrHide.java
 */
public class DeployOrHide implements ICommandVisitor {

	@Override
	public void visit(String textInput) {
		//TODO properly
		ISection current = Cursor.getCursorInstance().getCurrentSection();
		if(current!=null)
        	current.setIsCurrentSection(!current.isCurrentSection());
	}
}
