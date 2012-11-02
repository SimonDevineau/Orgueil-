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
			if (aSection.getText().size() != 0) {
				aSection.getText()
						.getLine(0)
						.setCursorLocation(
								aSection.getTitle().getCursorLocation());
				aSection.getTitle().removeCursor();
			} else
				// else it means that the text is empty
				changeSection(aSection, aSection.getTitle());
		} else { // else it means that the text has the cursor
			int index = 0;
			int linesNumber = aSection.getText().size();
			while (index < linesNumber
					&& !aSection.getText().getLine(index).hasCursor())
				index++;
			if (index == linesNumber)
				throw new RuntimeException(
						"An error occured in the Down command line 52, an error exists in the model because no line has the cursor");
			if (index == linesNumber - 1)
				changeSection(aSection,
						aSection.getText().getLine(index));
			else {
				aSection.getText()
						.getLine(index + 1)
						.setCursorLocation(
								aSection.getText().getLine(index)
										.getCursorLocation());
				aSection.getText().getLine(index).removeCursor();
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
	 * 
	 * @param aSection
	 *            the current section
	 * @param line
	 *            the current line
	 */
	private void changeSection(ISection aSection, ILine line) {
		// Get the next section
		ISection next = getNextSection(aSection);

		if (next != null) {
			// Putting the cursor on the title
			next.getTitle().setCursorLocation(line.getCursorLocation());
			// Removing the cursor from the previous line
			line.removeCursor();
		}
	}

	/**
	 * 
	 * @param aSection
	 *            the section from which we have to get the predecessor
	 * @return the predecessor
	 */
	private ISection getNextSection(ISection aSection) {
		// if the parent is null it means that we are at the root level
		if (aSection.getParent() == null) {
			try {
				// Trying to get the element after the current section at the
				// root level
				return Editor
						.getEditor()
						.getCurrentDocument()
						.getSection(
								Editor.getEditor().getCurrentDocument()
										.getIndexCurrentSection() + 1);
			} catch (Exception e) {
				// if the exception is thrown it means that the section was the
				// last section on the root element and that there is no
				// section after. So we need to create a new one
				ISection lastSection = Factory.createSection();
				Editor.getEditor().getCurrentDocument().addSection(lastSection);
				return lastSection;
			}
		} else {
			// if the section is the first child so we need to go back to the
			// parent section
			if (aSection.getParent().indexOfCurrentSection() == aSection
					.getParent().getSubSections().size() - 1)
				return getNextSection(aSection.getParent());
			// if the parent is not null, so we need to get the
			// following section by using the getParent
			return aSection.getParent().getSubSections()
					.get(aSection.getParent().indexOfCurrentSection() + 1);
		}
	}
}
