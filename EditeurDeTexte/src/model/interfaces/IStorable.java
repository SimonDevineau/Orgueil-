package model.interfaces;

/**
 * This interface is used to indicate which elements are allowed to be stacked
 * in our BufferMemory. Indeed, we could basically accept all the Object but
 * that would not be convenient in some cases. That is why, we decided to limit
 * the authorized objects. Only the IStorable objects are going to be stackable
 * in our BufferMemory. <br/>
 * 22 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau Ecole des Mines de Nantes Major in Computer and
 *         Information System Engineering IStorable.java
 */
public interface IStorable {
    // No methods because it is just an encapsulation type
}
