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
        String title = textInput.substring(1);
        int index = 0;
        while (title.charAt(index) == '*') {
            index++;
        }
        title = title.substring(index);
        try {
            if (index == 1) {
                Cursor.instance().getCurrentDocument()
                        .addSection(Factory.createSection(title));
            }
            else if (index > Cursor.instance().getCurrentSection()
                    .getNbParents()) {
                Cursor.instance().getCurrentSection()
                        .addSection(Factory.createSection(title));
            }
        }
        catch (Exception aE) {
            Logger.error(
                    "An error of initialisation occured in the class AddSection",
                    aE);
        }
    }
}
