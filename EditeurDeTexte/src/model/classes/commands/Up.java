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
 *         Computer and Information System Engineering Up.java
 */
class Up implements ICommandVisitor {

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
		if (aSection.getTitle().hasCursor())
			changeSection(aSection, aSection.getTitle());
		else { // else it means that the text has the cursor
			int index = 0;
			int linesNumber = aSection.getIntroduction().size();
			while (index < linesNumber
					&& !aSection.getIntroduction().getLine(index).hasCursor())
				index++;
			if (index == linesNumber) // in that case it means that we have a bug in the model
				throw new RuntimeException(
						"An error occured in the Down command line 52, because no line has the cursor");
			if (index == 0) { // in that case the title has to receive the cursor
				aSection.getTitle().setCursorLocation(
						aSection.getIntroduction().getLine(index)
								.getCursorLocation());
				aSection.getIntroduction().getLine(index).removeCursor();
			} else { // or just the previous line
				aSection.getIntroduction()
						.getLine(index - 1)
						.setCursorLocation(
								aSection.getIntroduction().getLine(index)
										.getCursorLocation());
				aSection.getIntroduction().getLine(index).removeCursor();
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
	 * This method is used to go to the previous section
	 * 
	 * @param aSection
	 *            the current section
	 * @param line
	 *            the current line
	 */
	private void changeSection(ISection aSection, ILine line) {
		// Get the previous section
		ISection previous = getPreviousSection(aSection);

		// If it is null it means that we are at the first root element
		if (previous != null) {
			// Get the text size to check if it null or not
			int textSize = previous.getIntroduction().getLines().size();
			// the storage for the previous line
			ILine previousLine;
			/*
			 * A if/else statement We could have used a ternary operator but the
			 * line would have been to long
			 */
			if (textSize > 0)
				previousLine = previous.getIntroduction().getLines()
						.get(textSize - 1);
			else
				previousLine = previous.getTitle();

			// Affecting the cursor
			previousLine.setCursorLocation(line.getCursorLocation());
			// Removing the previous cursor
			line.removeCursor();
		}
	}

	/**
	 * 
	 * @param aSection the section from which we have to get the predecessor 
	 * @return the predecessor
	 */
	private ISection getPreviousSection(ISection aSection) {
		// if the parent is null it means that we are at the root level
		if (aSection.getParent() == null) {
			try {
				// Trying to get the elment before the current section at the
				// root level
				return Editor
						.getEditor()
						.getCurrentDocument()
						.getSection(
								Editor.getEditor().getCurrentDocument()
										.getIndexCurrentSection() - 1);
			} catch (Exception e) {
				// if the exception is thrown it means that the section was the
				// first section on the root element and that there is no
				// section before.
				return null;
			}
		} else {
			// if the section is the first child so we need to go back to the
			// parent section
			if (aSection.getParent().indexOfCurrentSection() == 0)
				aSection.getParent();
			// if the parent is not null, so we need to get the
			// following section by using the getParent
			return aSection.getParent().getSubSections()
					.get(aSection.getParent().indexOfCurrentSection() - 1);
		}
	}

}
