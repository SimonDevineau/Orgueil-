package model.classes.commands;

import utilities.Logger;
import model.classes.Cursor;
import model.classes.Factory;
import model.interfaces.ICommandVisitor;
import model.interfaces.IText;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         BackSlash.java
 */
public class BackSlash implements ICommandVisitor {
    @Override
    public void visit(String textInput) {
        try {
            Cursor.instance().getCurrentLine()
                    .replaceUnderCursor("\\");
        }
        catch (Exception aE) {
            Logger.error("An error of line initialization occured.", aE);
        }
    }
}
