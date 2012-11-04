package model.classes.commands;

import model.classes.Cursor;
import model.classes.Factory;
import model.interfaces.ICommandVisitor;
import model.interfaces.IDocument;
import model.interfaces.ILine;
import model.interfaces.ISection;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering Down.java
 */
public class Down implements ICommandVisitor {
    /**
     * This method is used to go to the next section
     * @param aSection
     *            the current section
     * @param line
     *            the current line
     */
    private void changeSection(ISection aSection, ILine line) {
        // Get the next section
        ISection next = this.getNextSection(aSection);
        if (next != null) {
            Cursor.instance().setCurrentLine(next.getTitle());
            Cursor.instance().setCurrentSection(next);
        }
    }

    /**
     * @param aSection
     *            the section from which we have to get the predecessor
     * @return the predecessor
     */
    private ISection getNextSection(ISection aSection) {
        // if the parent is null it means that we are at the root level
        if (aSection.getParent() instanceof IDocument
                || aSection.getParent() == null) {
            try {
                // Trying to get the element after the current section at the
                // root level
                int currentIndex = Cursor.instance().getCurrentDocument()
                        .indexOfCurrentSection();
                return Cursor.instance().getCurrentDocument()
                        .getSection(currentIndex + 1);
            }
            catch (Exception e) {
                // if the exception is thrown it means that the section was the
                // last section on the root element and that there is no
                // section after. So we need to create a new one
                ISection lastSection = Factory.createSection();
                Cursor.instance().getCurrentDocument().addSection(lastSection);
                return lastSection;
            }
        }
        else {
            // if the section is the first child so we need to go back to the
            // parent section
            if (aSection.getParent().indexOfCurrentSection() == aSection
                    .getParent().getSubSections().size() - 1) {
                return this.getNextSection((ISection) aSection.getParent());
            }
            // if the parent is not null, so we need to get the
            // following section by using the getParent
            return aSection.getParent().getSubSections()
                    .get(aSection.getParent().indexOfCurrentSection() + 1);
        }
    }

    @Override
    public void visit(String textInput) {
        ISection current = Cursor.instance().getCurrentSection();
        // If the title has the cursor
        if (current.getTitle().hasCursor()) {
            // If the text is not empty
            if (current.getText().size() != 0) {
                Cursor.instance().setCurrentLine(current.getText().getLine(0));
            }
            else {
                // else it means that the text is empty
                this.changeSection(current, current.getTitle());
            }
        }
        else { // else it means that the text has the cursor
            int index = 0;
            int linesNumber = current.getText().size();
            while (index < linesNumber
                    && !current.getText().getLine(index).hasCursor()) {
                index++;
            }
            if (index == linesNumber) {
                throw new RuntimeException(
                        "An error occured in the Down command line 52, an error exists in the model because no line has the cursor");
            }
            if (index == linesNumber - 1) {
                this.changeSection(current, current.getText().getLine(index));
            }
            else {
                Cursor.instance().setCurrentLine(
                        current.getText().getLine(index + 1));
            }
        }
    }
}
