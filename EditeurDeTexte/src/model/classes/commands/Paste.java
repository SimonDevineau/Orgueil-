package model.classes.commands;

import model.classes.Cursor;
import model.interfaces.ICommandVisitor;
import model.interfaces.IDocument;
import model.interfaces.ILine;
import model.interfaces.ISection;
import model.interfaces.IText;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         Paste.java
 */
class Paste implements ICommandVisitor {
    /**
     * @see model.interfaces.ICommandVisitor#visit(model.interfaces.ILine)
     */
    @Override
    public void visit(ILine aLine) {
    }

    /**
     * @see model.interfaces.ICommandVisitor#visit(model.interfaces.ISection)
     */
    @Override
    public void visit(ISection aSection) {
        if (aSection != null &&  Cursor.getCursorInstance().getCurrentDocument()
                .getBufferMemory().peek() instanceof ILine) {
            aSection.getIntroduction().addLine(
                    Cursor.getCursorInstance().getCurrentLine(),
                    (ILine) Cursor.getCursorInstance().getCurrentDocument()
                            .getBufferMemory().pop());
        }
    }

    /**
     * @see model.interfaces.ICommandVisitor#visit(model.interfaces.IText)
     */
    @Override
    public void visit(IText aText) {
    }

    /**
     * @see model.interfaces.ICommandVisitor#visit(model.interfaces.IDocument)
     */
    @Override
    public void visit(IDocument aDocument) {
    }
}
