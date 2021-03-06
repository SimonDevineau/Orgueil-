package model.classes.commands;

import model.classes.Cursor;
import model.interfaces.ICommandVisitor;
import model.interfaces.IDocument;
import model.interfaces.ILine;
import model.interfaces.ISection;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering Up.java
 */
public class Up implements ICommandVisitor {
    /**
     * This method is used to go to the previous section
     * @param aSection
     *            the current section
     * @param line
     *            the current line
     */
    private void changeSection(ISection aSection, ILine line) {
        // Get the previous section
        ISection previous = this.getPreviousSection(aSection);
        // If it is null it means that we are at the first root element
        if (previous != null) {
            // Get the text size to check if it null or not
            int textSize = previous.getText().getLines().size();
            // the storage for the previous line
            ILine previousLine;
            /*
             * A if/else statement We could have used a ternary operator but the
             * line would have been to long
             */
            if (textSize > 0) {
                previousLine = previous.getText().getLines().get(textSize - 1);
            }
            else {
                previousLine = previous.getTitle();
            }
            // Affecting the cursor
            Cursor.instance().setCurrentSection(previous);
            Cursor.instance().setCurrentLine(previousLine);
        }
        else {
            int textSize = Cursor.instance().getCurrentDocument().getText()
                    .size();
            Cursor.instance().setCurrentText(
                    Cursor.instance().getCurrentDocument().getText());
            Cursor.instance().setCurrentLine(
                    Cursor.instance().getCurrentDocument().getText()
                            .getLine(textSize - 1));
        }
    }

    /**
     * @param aSection
     *            the section from which we have to get the predecessor
     * @return the predecessor
     */
    private ISection getPreviousSection(ISection aSection) {
        // if the parent is null it means that we are at the root level
        if (aSection.getParent()==Cursor.instance().getCurrentDocument()) {
            try {

                // Trying to get the element before the current section at the
                // root level
                int currentIndex = Cursor.instance().getCurrentDocument()
                        .indexOfCurrentSection();
                return Cursor.instance().getCurrentDocument()
                        .getSection(currentIndex - 1);
            }
            catch (Exception e) {
                // if the exception is thrown it means that the section was the
                // first section on the root element and that there is no
                // section before.
                return null;
            }
        }
        else {
            // if the section is the first child so we need to go back to the
            // parent section
            if (aSection.getParent().indexOfCurrentSection() == 0) {
               return (ISection) aSection.getParent();
            }
            // if the parent is not null, so we need to get the
            // following section by using the getParent
            return aSection.getParent().getSubSections()
                    .get(aSection.getParent().indexOfCurrentSection() - 1);
        }
    }

    @Override
    public void visit(String textInput) {
        ISection current = Cursor.instance().getCurrentSection();
        // If the title has the cursor
        if (current.getTitle().hasCursor()) {
            this.changeSection(current, current.getTitle());
        }
        else { // else it means that the text has the cursor
            int index = 0;
            int linesNumber = current.getText().size();
            while (index < linesNumber
                    && !current.getText().getLine(index).hasCursor()) {
                index++;
            }
            if (index == linesNumber) {
                // bug in the model
                throw new RuntimeException("An error occured in the Up command");
            }
            if (index == 0) {
                // cursor
                Cursor.instance().setCurrentLine(current.getTitle());
            }
            else {
                // or just the previous line
                Cursor.instance().setCurrentLine(
                        current.getText().getLine(index - 1));
            }
        }
    }
}
