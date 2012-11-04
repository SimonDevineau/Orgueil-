package model.interfaces;

/**
 * 28 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering ICommandVisitor.java
 */
public interface ICommandVisitor {

	/**
	 * This method uses the cursor to update the content of the text, section
	 * and subsections... This method has to be implemented by all the commands
	 * specified in the Commands.xml file.
	 * 
	 * @param aTextInput
	 *            a String which can be anything for example a parameter for the
	 *            command, e.g. for a new section "\* theTitle". This parameter
	 *            could also be used for a research command.
	 */
	void visit(String textInput);

}
