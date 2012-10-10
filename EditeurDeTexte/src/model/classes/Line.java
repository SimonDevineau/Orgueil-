package model.classes;

import model.interfaces.ILine;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 * Ecole des Mines de Nantes
 * Major in Computer and Information System Engineering
 * Line.java 
 */
class Line implements ILine {

	/**
	 * The representation of the line as a StringBuffer to avoid creating a new string
	 * each time that a char is added.
	 */
	private StringBuilder _Line;
	
	/**
	 * Default constructor which creates an empty line
	 */
	Line() {
		_Line = new StringBuilder();
	}
	
	/**
	 * The constructor to create a line using the CharSequence as starting text.
	 * @param sequence, the CharSequence which contains the basis.
	 */
	Line(CharSequence sequence) {
		_Line = new StringBuilder(sequence);
	}
	
}
