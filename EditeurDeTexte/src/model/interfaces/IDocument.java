package model.interfaces;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         IDocument.java
 */
public interface IDocument extends IStorable{
    /**
     * @param section
     *            , the section to insert after the current section, the one
     *            with the cursor
     * @return true if the section has been added successfully.
     */
    public boolean addSection(ISection section);

    /**
     * @param section
     *            , the section to append at the end of the sections
     * @return true if the section has been added successfully.
     */
    public boolean appendSection(ISection section);

    /**
     * @return true if the current section, the one which has the cursor, has
     *         been
     *         deleted successfully.
     */
    public boolean deleteSection();

    /**
     * @param section
     *            the section to delete
     * @return true if the section has been deleted successfully
     */
    public boolean deleteSection(ISection section);

    /**
     * @param obj
     *            , the object to be tested
     * @return true if obj is equal with this
     */
    @Override
    public boolean equals(Object obj);

    /**
     * @return the current section, the only one which has the cursor
     */
    public ISection getCurrentSection();

    /**
     * @return the current section index , the only one which has the cursor
     */
    public int getIndexCurrentSection();

    /**
     * @return the document path
     */
    public String getPath();

    /**
     * @return the current introduction text of the document.
     */
    public IText getText();

    /**
     * @return true if it is the current document
     */
    public boolean isCurrentDocument();

    /**
     * @param isCurrentDocument
     *            , true if it the current document
     */
    public void setIsCurrentDocument(boolean isCurrentDocument);

    /**
     * @param url
     *            , the new path
     */
    public void setPath(String uString);

    /**
     * Sets the new introduction text
     * @param text
     *            , the text to set.
     */
    public void setText(IText text);

    /**
     * @return the string representation of the document.
     */
    @Override
    public String toString();
}
