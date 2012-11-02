package model.classes.commands;

import model.classes.Editor;
import model.classes.Factory;
import model.interfaces.ICommandVisitor;
import model.interfaces.IDocument;
import model.interfaces.ILine;
import model.interfaces.ISection;
import model.interfaces.IText;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering
 *         DowngradeSectionsAndSubsections.java
 */
class DowngradeSectionAndSubsections implements ICommandVisitor {

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
		// if the parent is null it means that we are at the root level
		if (aSection.getParent() == null) {
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
			if (aSection.getParent().indexOfCurrentSection() == 0) {
				ISection previous = Factory.createSection();
				aSection.getParent().addSubSection(previous, 1);
				previous.addSubSection(aSection.getParent().removeSection(0));
			} else {
				// if the parent is not null, so we need to get the
				// following section by using the getParent
				ISection previous = aSection.getParent().getSubSections()
						.get(aSection.getParent().indexOfCurrentSection() - 1);
				previous.addSubSection(aSection.getParent().removeSection(
						aSection.getParent().indexOfCurrentSection()));
			}
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
