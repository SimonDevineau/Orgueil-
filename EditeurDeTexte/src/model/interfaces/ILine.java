package model.interfaces;

import java.util.Observer;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering ILine.java
 */
public interface ILine extends IStorable {
	/**
	 * A constant to indicate that the cursor is not on the line.
	 */
	static final int NO_CURSOR = -1;

	/**
	 * Insert the "insertion" param under the cursor if the line has the cursor
	 * 
	 * @param insertion
	 *            CharSequence, the sequence to insert.
	 */
	void addUnderCursor(CharSequence insertion);

	/**
	 * @param content
	 *            , content to append.
	 */
	void append(CharSequence content);

	/**
	 * Delete the current letter if the line has the cursor
	 */
	void deleteUnderCursor();

	/**
	 * @param obj
	 *            , the object which should be tester for equality check
	 * @return true if <i><b>this</b></i> and obj are equal
	 */
	@Override
	boolean equals(Object obj);

	/**
	 * Return true if the current ILine has the cursor and false otherwise
	 * 
	 * @return a boolean which indicates if the line has the cursor.
	 */
	boolean hasCursor();
	
	/**
	 * Set the line to indicate that it is the current line.
	 * @param isCurrent true if the line is the current one
	 */
	void setCurrent(boolean isCurrent);

	/**
	 * Replace the following characters by the replacement
	 * 
	 * @param replacement
	 *            , the new string to insert.
	 */
	void replaceUnderCursor(CharSequence replacement);

	/**
	 * @return the String representation of the ILine
	 */
	@Override
	String toString();

	/**
	 * The method used to add a new observer on the line. This observer is used
	 * in order to be able to notify when the line get the cursor.
	 * 
	 * @param o
	 *            , the new Observer
	 */
	void addObserver(Observer o);

	/**
	 * 
	 * @return the length of the line
	 */
	int length();
}
