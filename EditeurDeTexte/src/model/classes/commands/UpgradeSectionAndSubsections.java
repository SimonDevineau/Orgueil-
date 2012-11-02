package model.classes.commands;

import model.classes.Editor;
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
 *         UpgradeSectionAndSubsections.java
 */
class UpgradeSectionAndSubsections implements ICommandVisitor {

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
		// and we do not have to do anything.
		if (aSection.getParent() != null) {
			ISection parent = aSection.getParent();
			if (parent.getParent() == null) { // So the parent is at the root
												// level
				Editor.getEditor()
						// we add the section at the root level.
						.getCurrentDocument()
						.addSection(
								aSection.getParent().removeSection(
										aSection.getParent()
												.indexOfCurrentSection()));
			} else {
				parent.getParent().addSubSection(
						aSection.getParent().removeSection(
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
