package model.interfaces;

/**
 * 21 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         IBufferMemory.java
 *         This interface defines the behavior of the document stack.
 */
public interface IBufferMemory {
 
    /**
     * @param storable
     *            , the data to add into the stack
     * @return, true if the data was added
     */
    boolean add(IStorable storable);

    /**
     * @param storable
     *            , the data to remove
     * @return, true if the data was removed.
     */
    boolean remove(IStorable storable);

    /**
     * 
     * @param storable, push the storable onto the top of the stack
     * @return the element that has been pushed onto the top of the stack
     */
    IStorable push(IStorable storable);
    
    /**
     * Remove the last object of the stack
     * @return the last IStorable object of the stack
     */
    IStorable pop();
    /**
     * 
     * @return the last object of the stack
     */
    IStorable peek();
}
