package model.interfaces;

import java.util.ArrayList;
import java.util.Observer;

/**
 * 9 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering ISection.java
 */
public interface ISection extends IStorable {
	/**
	 * @see java.util.Observable#addObserver(Observer)
	 */
	void addObserver(Observer o);

	/**
	 * Add a section to the current section (as a child)
	 * 
	 * @param aSection
	 *            , the section to add
	 * @return true if the subsection has been added successfully
	 */
	boolean addSubSection(ISection aSection);

	/**
	 * Add a section to the current section (as a child)
	 * 
	 * @param aSection
	 *            , the section to add
	 * @param int, the index where the new section should be inserted.
	 * @return true if the subsection has been added successfully
	 */
	void addSubSection(ISection aSection, int index);

	/**
	 * 
	 * @param index
	 *            , the index of the section to remove
	 * @return the removed section
	 */
	ISection removeSection(int index);

	/**
	 * @return true if anObject is equal to the current section.
	 */
	@Override
	boolean equals(Object anObject);

	/**
	 * @return the introduction of the current section
	 */
	IText getText();

	/**
	 * @return the parent of the current section.
	 */
	ISection getParent();

	/**
	 * @return the current section used by the editor (browsing the tree to find
	 *         the real current one)
	 */
	ISection getCurrentSection();

	/**
	 * @return the index of the current section or -1 if there is no current
	 *         section in the subsections.
	 */
	int indexOfCurrentSection();

	/**
	 * @return the subsections list of the current section
	 */
	ArrayList<ISection> getSubSections();

	/**
	 * @return the section title
	 */
	ILine getTitle();

	/**
	 * @return true if the section is the current section.
	 */
	boolean isCurrentSection();

	/**
	 * @param isCurrentSection
	 *            , true if the section is the current section.
	 */
	void setIsCurrentSection(boolean isCurrentSection);

	/**
	 * Set the parent of the section.
	 * 
	 * @param aSection
	 *            , the new parent
	 */
	void setParent(ISection aSection);

	/**
	 * Set the list of subsections,
	 * 
	 * @param aSubSectionsList
	 *            , the new subsections
	 */
	void setSubSection(ArrayList<ISection> aSubSectionsList);

	/**
	 * Set the title of the section
	 * 
	 * @param aText
	 *            , the new title
	 */
	void setTitle(ILine aLine);

	/**
	 * @return the description of the current section.
	 */
	@Override
	String toString();

	/**
	 * Change the state of the section to be deployed
	 */
	void deploy();
	
	/**
	 * Change the state of the section to be hidden
	 */
	void hide();
}
