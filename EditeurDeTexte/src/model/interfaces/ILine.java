package model.interfaces;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau & Pierre Reliquet
 * Ecole des Mines de Nantes 
 * Major in Computer and Information System Engineering 
 * ILine.java
 */
public interface ILine {

	/**
	 * @param content
	 *            , content to append.
	 */
	public void append(CharSequence content);

	/**
	 * Return true if the current ILine has the cursor and false otherwise
	 * 
	 * @return a boolean which indicates if the line has the cursor.
	 */
	public boolean hasCursor();

	/**
	 * @return the location of the cursor if the line has the cursor
	 */
	public int getCursorLocation();

	/**
	 * @return the String representation of the ILine
	 */
	@Override
	public String toString();
	
	/**
	 * @param obj, the object which should be tester for equality check
	 * @return true if <i><b>this</b></i> and obj are equal
	 */
	@Override
	public boolean equals(Object obj);

}
