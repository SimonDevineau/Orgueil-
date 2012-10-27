package model.interfaces;

import java.util.ArrayList;
import java.util.Observer;

/**
 * 9 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         ISection.java
 */
public interface ISection extends IStorable{
    /**
     * Add a section to the current section (as a child)
     * @param aSection
     *            , the section to add
     */
    void addSubSection(ISection aSection);

    /**
     * Deploy the current section
     */
    void deploySection();

    /**
     * Hide the current section
     */
    void hideSection();

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
     * @return the introduction of the current section
     */
    IText getIntroduction();

    /**
     * Set the parent of the section.
     * @param aSection
     *            , the new parent
     */
    void setParent(ISection aSection);

    /**
     * Set the title of the section
     * @param aText
     *            , the new title
     */
    void setTitle(ILine aLine);

    /**
     * Set the list of subsections,
     * @param aSubSectionsList
     *            , the new subsections
     */
    void setSubSection(ArrayList<ISection> aSubSectionsList);

    /**
     * @return true if the section is the current section.
     */
    boolean isCurrentSection();
    
    /**
     * 
     * @param isCurrentSection, true if the section is the current section.
     */
    void setIsCurrentSection(boolean isCurrentSection);

    /**
     * @return the description of the current section.
     */
    @Override
    String toString();

    /**
     * @return true if anObject is equal to the current section.
     */
    @Override
    boolean equals(Object anObject);
    
    /**
     *
     * @see java.util.Observable#addObserver(Observer)
     */
    void addObserver(Observer o);
}
