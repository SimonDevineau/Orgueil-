package model.interfaces;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         IStorable.java
 */
public interface IStorable {
    
    /**
     * 
     * @param visitor
     */
    void accept(ICommandVisitor visitor);
}
