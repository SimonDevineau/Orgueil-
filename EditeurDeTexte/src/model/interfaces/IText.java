/**
 * 
 */
package model.interfaces;

import java.util.ArrayList;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         IText.java
 */
public interface IText extends IStorable {
    /**
     * Add a line to the current text at the cursor position
     * @param aLine
     *            , the line to add
     */
    public void addLine(ILine aLine);
    /**
     * add a Line after the current line
     * @param currentLine, the current line
     * @param lineToPaste, the line to paste
     */
    public void addLine(ILine currentLine, ILine lineToPaste);

    /**
     * @return the lines list of the text
     */
    public ArrayList<ILine> getLines();

    /**
     * @return true if the cursor is inside the current text
     */
    public boolean hasCursor();

    /**
     * Remove the line situated at the cursor position
     * @param aLine
     */
    void removeLine(ILine aLine);
}
