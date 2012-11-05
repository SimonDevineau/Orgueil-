package model.classes.commands;

import model.classes.Cursor;
import model.interfaces.ICommandVisitor;
import model.interfaces.IDocument;
import model.interfaces.ISection;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering
 *         UpgradeSectionAndSubsections.java
 */
public class UpgradeSectionAndSubsections implements ICommandVisitor {
    @Override
    public void visit(String textInput) {
        ISection current = Cursor.instance().getCurrentSection();
        // if the parent is null it means that we are at the root level
        // and we do not have to do anything.
        if (!(current.getParent()==Cursor.instance().getCurrentDocument())) {
            ISection parent = (ISection) current.getParent();
            if (parent.getParent() instanceof IDocument) { // So the parent is
                                                           // at the root
                // level
                IDocument currentDoc = Cursor.instance().getCurrentDocument();
                currentDoc.addSection(parent.removeSection(parent
                        .indexOfCurrentSection()));
            }
            else {
                parent.getParent().addSection(
                        parent.removeSection(parent.indexOfCurrentSection()));
            }
        }
    }
}
