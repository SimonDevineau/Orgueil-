package model.interfaces;

import java.util.Observer;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         IDocument.java
 */
public interface IDocument extends IStorable {
    /**
     * Add a line to the document at the suitable position.
     * @param line
     *            , the line to add
     */
    void addLine(ILine line);

    /**
     * The method used to add a new observer on the line. This observer is used
     * in order to be able to notify when the line get the cursor.
     * @param o
     *            , the new Observer
     */
    void addObserver(Observer o);

    /**
     * @param section
     *            , the section to insert after the current section, the one
     *            with the cursor
     * @return true if the section has been added successfully.
     */
    boolean addSection(ISection section);

    /**
     * @param section
     *            , the section to insert after the current section, the one
     *            with the cursor
     * @param index
     *            , the index where the new section should be added?
     * @return true if the section has been added successfully.
     */
    void addSection(ISection section, int index);

    /**
     * @param section
     *            , the section to append at the end of the sections
     * @return true if the section has been added successfully.
     */
    boolean appendSection(ISection section);

    /**
     * @return true if the current section, the one which has the cursor, has
     *         been
     *         deleted successfully.
     */
    boolean removeSection();

    /**
     * @param section
     *            the section to delete
     * @return true if the section has been deleted successfully
     */
    boolean removeSection(ISection section);

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
    int indexCurrentSection();

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
     * @param text
     *            , the text to set.
     */
    void setText(IText text);

    /**
     * @return the string representation of the document.
     */
    @Override
    String toString();
}
