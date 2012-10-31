package model.interfaces;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         ILine.java
 */
public interface ILine extends IStorable {
    /**
     * A constant to indicate that the cursor is not on the line.
     */
    public static final int NO_CURSOR = -1;

    /**
     * Insert the "insertion" param under the cursor if the line has the cursor
     * @param insertion
     *            CharSequence, the sequence to insert.
     */
    public void addUnderCursor(CharSequence insertion);

    /**
     * @param content
     *            , content to append.
     */
    public void append(CharSequence content);

    /**
     * Delete the current letter if the line has the cursor
     */
    public void deleteUnderCursor();

    /**
     * @param obj
     *            , the object which should be tester for equality check
     * @return true if <i><b>this</b></i> and obj are equal
     */
    @Override
    public boolean equals(Object obj);

    /**
     * Return true if the current ILine has the cursor and false otherwise
     * @return a boolean which indicates if the line has the cursor.
     */
    public boolean hasCursor();

    /**
     * Replace the following characters by the replacement
     * @param replacement
     *            , the new string to insert.
     */
    public void replaceUnderCursor(CharSequence replacement);

    /**
     * @return the String representation of the ILine
     */
    @Override
    public String toString();
}
