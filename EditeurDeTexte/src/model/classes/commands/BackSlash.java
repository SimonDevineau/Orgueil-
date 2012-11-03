package model.classes.commands;

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
class BackSlash implements ICommandVisitor {
    @Override
    public void visit() {
        if (Cursor.getCursorInstance() != null
                && Cursor.getCursorInstance().getCurrentLine() != null) {
            Cursor.getCursorInstance().getCurrentLine()
                    .replaceUnderCursor("\\");
        }
        else if (Cursor.getCursorInstance() != null
                && Cursor.getCursorInstance().getCurrentStorable() != null) {
            if (Cursor.getCursorInstance().getCurrentStorable() instanceof IText) {
                Cursor.getCursorInstance().getCurrentText()
                        .addLine(Factory.createLine());
                Cursor.getCursorInstance().getCurrentLine()
                        .replaceUnderCursor("\\");
            }
            else {
                Cursor.getCursorInstance().getCurrentSection().getText()
                        .addLine(Factory.createLine());
                Cursor.getCursorInstance().getCurrentLine()
                        .replaceUnderCursor("\\");
            }
        }
    }
}
