package model.classes.commands;

import model.classes.Cursor;
import model.classes.Editor;
import model.classes.Factory;
import model.interfaces.ICommandVisitor;
import model.interfaces.IDocument;
import model.interfaces.ISection;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering
 *         DowngradeSectionsAndSubsections.java
 */
public class DowngradeSectionAndSubsections implements ICommandVisitor {

	@Override
	public void visit(String textInput) {
		ISection current = Cursor.getCursorInstance().getCurrentSection();
		// if the parent is null it means that we are at the root level
		if (current.getParent() == null) {
			IDocument currentDoc = Editor.getEditor().getCurrentDocument();
			try {
				// Trying to get the element before the current section at the
				// root level
				ISection previous = currentDoc.getSection(currentDoc
						.getIndexCurrentSection() - 1);
				// Getting the index of the section
				int indexOfDowngraded = currentDoc.getIndexCurrentSection();
				// Removing the section and adding it to the sons of the
				// previous section
				previous.addSubSection(currentDoc
						.removeSection(indexOfDowngraded));
			} catch (Exception e) {
				// if the exception is thrown it means that the section was the
				// first section on the root element and that there is no
				// section before.
				ISection previous = Factory.createSection();
				currentDoc.addSection(previous, 1);
				previous.addSubSection(currentDoc.removeSection(0));
			}
		} else {
			// if the section is the first child so we need to go back to the
			// parent section
			if (current.getParent().indexOfCurrentSection() == 0) {
				ISection previous = Factory.createSection();
				current.getParent().addSubSection(previous, 1);
				previous.addSubSection(current.getParent().removeSection(0));
			} else {
				// if the parent is not null, so we need to get the
				// following section by using the getParent
				ISection previous = current.getParent().getSubSections()
						.get(current.getParent().indexOfCurrentSection() - 1);
				previous.addSubSection(current.getParent().removeSection(
						current.getParent().indexOfCurrentSection()));
			}
		}
	}
}
