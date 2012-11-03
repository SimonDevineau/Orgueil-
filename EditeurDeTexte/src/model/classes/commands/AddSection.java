package model.classes.commands;

import utilities.Logger;
import model.classes.Cursor;
import model.classes.Factory;
import model.interfaces.ICommandVisitor;

/**
 * 1 nov. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         AddSection.java
 */
public class AddSection implements ICommandVisitor {
    @Override
    public void visit(String textInput) {
        try {
            Cursor.instance().getCurrentDocument()
                    .addSection(Factory.createSection(textInput));
            System.out.println("title cursor location "
                    + Cursor.instance().getCurrentSection().getTitle()
                            .hasCursor());
        }
        catch (Exception aE) {
            Logger.error(
                    "An error of initialisation occured in the class AddSection",
                    aE);
        }
    }
}
