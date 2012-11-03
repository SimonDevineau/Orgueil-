package model.interfaces;

/**
 * 28 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         ICommandVisitor.java
 */
public interface ICommandVisitor {
	
	/**
	 * This method uses the cursor to update the content of the text, section and subsections...
	 */
	void visit();

}
