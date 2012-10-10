package model.interfaces;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 * École des Mines de Nantes
 * Major in Computer and Information System Engineering
 * IDocument.java 
 */
public interface IDocument {

	/**
	 * @return true if the current section, the one which has the cursor, has been 
	 * deleted successfully.
	 */
	public boolean deleteSection();
	
	/**
	 * @param section the section to delete
	 * @return true if the section has been deleted successfully
	 */
	public boolean deleteSection(ISection section);
	
	/**
	 * @param section, the section to insert after the current section, the one with the cursor
	 * @return true if the section has been added successfully.
	 */
	public boolean addSection(ISection section);
	
	/**
	 * @param section, the section to append at the end of the sections
	 * @return true if the section has been added successfully.
	 */
	public boolean appendSection(ISection section);
	
	/**
	 * @return the current introduction text of the document.
	 */
	public IText getText();
	
	/**
	 * Sets the new introduction text
	 * @param text, the text to set.
	 */
	public void setText(IText text);
	
	/**
	 * @return the string representation of the document.
	 */
	@Override
	public String toString();
	
	/**
	 * 
	 * @param obj, the object to be tested
	 * @return true if obj is equal with this
	 */
	@Override
	public boolean equals(Object obj);
}
