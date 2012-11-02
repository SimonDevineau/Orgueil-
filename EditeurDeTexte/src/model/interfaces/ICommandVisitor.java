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
     * @param line
     *            , the line where the commands will be executed.
     */
    void visit(ILine line);

    /**
     * @param section
     *            , the section where the commands will be executed.
     */
    void visit(ISection section);

    /**
     * @param text
     *            , the text where the commands will be executed.
     */
    void visit(IText text);
    /**
     * @param document
     *            , the text where the commands will be executed.
     */
    void visit(IDocument document);
}
