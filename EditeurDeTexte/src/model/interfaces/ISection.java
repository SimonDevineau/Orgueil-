package model.interfaces;

import java.util.ArrayList;
import java.util.Observer;

/**
 * 9 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering ISection.java
 */
public interface ISection extends IDocument {
    /**
     * Change the state of the section to be deployed
     */
    void deploy();

    /**
     * @return parents' section number
     */
    int getNbParents();

    /**
     * @return the parent of the current section.
     */
    ISection getParent();

    /**
     * @return the subsections list of the current section
     */
    ArrayList<ISection> getSubSections();

    /**
     * @return the section title
     */
    ILine getTitle();

    /**
     * Change the state of the section to be hidden
     */
    void hide();

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
    void setParent(ISection aSection);

    /**
     * Set the list of subsections,
     * @param aSubSectionsList
     *            , the new subsections
     */
    void setSubSection(ArrayList<ISection> aSubSectionsList);

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
