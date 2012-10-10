package model.interfaces;

import java.util.ArrayList;

/**
 * 9 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         ISection.java
 */
public interface ISection {
    /**
     * Add a section to the current section (as a child)
     * @param aSection
     *            , the section to add
     */
    public void add(ISection aSection);

    /**
     * Deploy the current section
     */
    public void deploySection();

    /**
     * Hide the current section
     */
    public void hideSection();

    /**
     * @return the parent of the current section.
     */
    public ISection getParent();

    /**
     * @return the subsections list of the current section
     */
    public ArrayList<ISection> getSubSections();

    /**
     * @return the section title
     */
    public ILine getTitle();

    /**
     * @return the introduction of the current section
     */
    public IText getIntroduction();

    /**
     * @return the description of the current section.
     */
    @Override
    public String toString();

    /**
     * @return true if anObject is equal to the current section.
     */
    @Override
    public boolean equals(Object anObject);
}
