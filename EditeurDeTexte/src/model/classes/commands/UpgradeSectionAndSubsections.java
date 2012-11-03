package model.classes.commands;

import model.classes.Cursor;
import model.classes.Editor;
import model.interfaces.ICommandVisitor;
import model.interfaces.ISection;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering
 *         UpgradeSectionAndSubsections.java
 */
public class UpgradeSectionAndSubsections implements ICommandVisitor {

	@Override
	public void visit(String textInput) {
		ISection current = Cursor.getCursorInstance().getCurrentSection();
		// if the parent is null it means that we are at the root level
		// and we do not have to do anything.
		if (current.getParent() != null) {
			ISection parent = current.getParent();
			if (parent.getParent() == null) { // So the parent is at the root
												// level
				Editor.getEditor()
						// we add the section at the root level.
						.getCurrentDocument()
						.addSection(
								current.getParent().removeSection(
										current.getParent()
												.indexOfCurrentSection()));
			} else {
				parent.getParent().addSubSection(
						current.getParent().removeSection(
								current.getParent().indexOfCurrentSection()));
			}
		}
	}

}
