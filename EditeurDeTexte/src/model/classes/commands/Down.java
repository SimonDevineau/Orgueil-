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
 *         Computer and Information System Engineering Down.java
 */
public class Down implements ICommandVisitor {

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
		// If the title has the cursor
		if (aSection.getTitle().hasCursor()) {
			// If the text is not empty
			if (aSection.getIntroduction().getLines().size() != 0) {
				aSection.getIntroduction()
						.getLines()
						.get(0)
						.setCursorLocation(
								aSection.getTitle().getCursorLocation());
				aSection.getTitle().removeCursor();
			} else
				// else it means that the section is empty
				changeSection(aSection, aSection.getTitle());
		} else { // else it means that the text has the cursor
			int index = 0;
			int linesNumber = aSection.getIntroduction().getLines().size();
			while (index < linesNumber
					&& !aSection.getIntroduction().getLines().get(index)
							.hasCursor())
				index++;
			if (index == linesNumber)
				throw new RuntimeException(
						"An error occured in the Down command line 52");
			if (index == linesNumber - 1)
				changeSection(aSection, aSection.getIntroduction().getLines()
						.get(index));
			else {
				aSection.getIntroduction()
						.getLines()
						.get(index + 1)
						.setCursorLocation(
								aSection.getIntroduction().getLines()
										.get(index).getCursorLocation());
				aSection.getIntroduction().getLines().get(index).removeCursor();
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

	/**
	 * This method is used to go to the next section
	 * @param aSection the current section
	 * @param line the current line
	 */
	private void changeSection(ISection aSection, ILine line) {
		//TODO établir le comportement lorsque l'on est sur la dernière section!
		ISection follower;
		// if the parent is null, we need to use the editor to access
		// the document because we are at the root level.
		if (aSection.getParent() == null) {
			// TODO changer cela parce que le try catch pour faire un test n'est
			// pas terrible
			try {
				follower = Editor
						.getEditor()
						.getCurrentDocument()
						.getSection(
								Editor.getEditor().getCurrentDocument()
										.getIndexCurrentSection() + 1);
			} catch (Exception e) {
				// if the exception is thrown it means that the section was the
				// last section.
				return;
			}
		} else {
			if (aSection.getParent().indexOfCurrentSection() == aSection
					.getParent().getSubSections().size() - 1)
				return;
			// if the parent is not null, so we need to get the
			// following section by using the getParent
			follower = aSection.getParent().getSubSections()
					.get(aSection.getParent().indexOfCurrentSection() + 1);
		}
		// affecting the cursor to the next section
		follower.getTitle().setCursorLocation(line.getCursorLocation());
		// and removing the cursor from the previous line.
		line.removeCursor();
	}
}
