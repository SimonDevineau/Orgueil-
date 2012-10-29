package model.classes;

import model.interfaces.IBufferMemory;
import model.interfaces.ILine;
import model.interfaces.ISection;
import model.interfaces.IText;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         Factory.java
 *         This class is supposed to be, with Editor, the only classes which are
 *         public
 *         because it is required to come through this class to instanciate the
 *         other objects.
 */
public class Factory {
    /**
     * @return a new buffer memory
     */
    public static IBufferMemory createBufferMemory() {
        return new BufferMemory();
    }

    /**
     * @return a new line
     */
    public static ILine createLine() {
        return new Line();
    }

    /**
     * @return a new Section
     */
    public static ISection createSection() {
        return new Section();
    }

    /**
     * @return a new text
     */
    public static IText createText() {
        return new Text();
    }
}
