package model.interfaces;

import java.util.ArrayList;
import java.util.Observer;

/**
 * This class is the main class for the editor because it is the representation
 * of the document. A document is made of
 * <ol>
 * <li>an introduction which is a text</li>
 * <li>A list of sections which are the first children of the tree structure</li>
 * </ol>
 * 
 * 10 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering IDocument.java
 */
public interface IDocument extends IStorable {

	/**
	 * Adds a line to the document at the suitable position.
	 * 
	 * @param line
	 *            , the line to add
	 */
	void addLine(ILine line);

	/**
	 * The method used to add a new observer on the line. This observer is used
	 * in order to be able to notify when the line get the cursor.
	 * 
	 * @param o
	 *            , the new Observer
	 */
	void addObserver(Observer o);

	/**
	 * This method adds the section after the current one.
	 * @param section
	 *            , the section to insert after the current section, the one
	 *            with the cursor
	 * @return true if the section has been added successfully.
	 */
	boolean addSection(ISection section);

	/**
	 * This method inserts the section at the specified position.
	 * @param section
	 *            , the section to insert after the current section, the one
	 *            with the cursor
	 * @param index
	 *            , the index where the new section should be added?
	 */
	void addSection(ISection section, int index);

	/**
	 * Adds the section at the end of the subsections.
	 * @param section
	 *            , the section to append at the end of the sections
	 * @return true if the section has been added successfully.
	 */
	boolean appendSection(ISection section);

	/**
	 * Removes the current section.
	 * @return true if the current section, the one which has the cursor, has
	 *         been deleted successfully.
	 */
	boolean removeSection();

	/**
	 * Removes the section specified and given has an argument.
	 * @param section
	 *            the section to delete
	 * @return true if the section has been deleted successfully
	 */
	boolean removeSection(ISection section);

	/**
	 * Removes the section at the specified index
	 * @param index
	 *            , the index to remove
	 * @return the section removed
	 */
	ISection removeSection(int index);

	/**
	 * @param obj
	 *            , the object to be tested
	 * @return true if obj is equal with this
	 */
	@Override
	boolean equals(Object obj);

	/**
	 * @return the current section, the only one which has the cursor
	 */
	ISection getCurrentSection();

	/**
	 * @return the current section index , the only one which has the cursor
	 */
	int indexOfCurrentSection();

	/**
	 * @param index
	 *            , the index of the section to get
	 * @return the section indexed by the index parameter.
	 */
	ISection getSection(int index);

	/**
	 * @return the current introduction text of the document.
	 */
	IText getText();

	/**
	 * @return true if it is the current document
	 */
	boolean isCurrentDocument();

	/**
	 * @param isCurrentDocument
	 *            , true if it the current document
	 */
	void setIsCurrentDocument(boolean isCurrentDocument);

	/**
	 * Sets the new introduction text
	 * 
	 * @param text
	 *            , the text to set.
	 */
	void setText(IText text);

	/**
	 * @return the string representation of the document.
	 */
	@Override
	String toString();

	/**
	 * @return the subsections list of the current section
	 */
	ArrayList<ISection> getSubSections();

	/**
	 * Set the list of sub-sections
	 * 
	 * @param subSections
	 */
	void setSubSections(ArrayList<ISection> subSections);
}
