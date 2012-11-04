package model.interfaces;

/**
 * The section is as important as the document because a document is composed of
 * sections. However, the sections and the document have in common the
 * introductory text and the subsections. Knowing that fact it seems obvious
 * that a section is a particular document because it is a document plus a
 * title. That is why an inheritance relationship has been used for the section
 * and the document --> Section extends Document. <br/>
 * 9 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering ISection.java
 */
public interface ISection extends IDocument {
	/**
	 * Change the state of the section to be deployed or hidden
	 */
	void deployOrHide();

	/**
	 * @return the depth of the section in the tree, but before calling the get,
	 *         it is required to call the setNbParents to compute the depth.
	 */
	int getNbParents();

	/**
	 * @return the parent of the current section with the higher common type -->
	 *         IDocument
	 */
	IDocument getParent();

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
	 * Computes the number of parents of the section, to access it a get is
	 * required.
	 */
	void setNbParents();

	/**
	 * Set the parent of the section.
	 * 
	 * @param aSection
	 *            , the new parent
	 */
	void setParent(IDocument aSection);

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
}
