package model.interfaces;

/**
 * 9 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering ISection.java
 */
public interface ISection extends IDocument {
    /**
     * Change the state of the section to be deployed or hidden
     */
    void deployOrHide();

    /**
     * @return parents' section number
     */
    int getNbParents();

    /**
     * @return the parent of the current section.
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
     * Modify the number of parents of the section
     */
    void setNbParents();

    /**
     * Set the parent of the section.
     * @param aSection
     *            , the new parent
     */
    void setParent(IDocument aSection);

    /**
     * Set the title of the section
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
