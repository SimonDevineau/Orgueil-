/**
 * 
 */
package model.interfaces;

import java.util.ArrayList;

/**
 * This text is useful for writing IDocument & ISection because it is the
 * introductory text. It is made of a list of line. <br/>
 * 10 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering IText.java
 */
public interface IText extends IStorable {
	/**
	 * Add a line to the current text at the cursor position
	 * 
	 * @param aLine
	 *            , the line to add
	 */
	void addLine(ILine aLine);

	/**
	 * add a Line after the current line
	 * 
	 * @param currentLine
	 *            , the current line
	 * @param lineToPaste
	 *            , the line to paste
	 */
	void addLine(ILine currentLine, ILine lineToPaste);

	/**
	 * add a Line before the current line
	 * 
	 * @param currentLine
	 *            , the current line
	 * @param lineToPaste
	 *            , the line to paste
	 */
	void addBeforeLine(ILine currentLine, ILine lineToPaste);

	/**
	 * @return the lines list of the text
	 */
	ArrayList<ILine> getLines();

	/**
	 * @param index
	 *            the index of the line to get
	 * @return the line numbered index (starting with 0 and ending with size-1)
	 */
	ILine getLine(int index);

	/**
	 * @return the size (number of lines) of the text
	 */
	int size();

	/**
	 * @return true if the cursor is inside the current text
	 */
	boolean hasCursor();

	/**
	 * Remove the line situated at the cursor position
	 * 
	 * @param aLine
	 */
	void removeLine(ILine aLine);

	/**
	 * Insert the lineToInsert inside the text after the current line or at the
	 * end.
	 * 
	 * @param lineToInsert
	 */
	void insertLine(ILine lineToInsert);
}
