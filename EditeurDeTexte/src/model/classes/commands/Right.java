package model.classes.commands;

import model.classes.Cursor;
import model.interfaces.ICommandVisitor;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering Right.java
 */
public class Right implements ICommandVisitor {
    @Override
    public void visit(String textInput) {
        Cursor.instance().setCurrentPosition(
                Cursor.instance().getCurrentPosition() + 1);
    }
}
