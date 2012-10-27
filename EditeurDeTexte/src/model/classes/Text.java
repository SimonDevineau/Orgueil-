package model.classes;

import java.util.ArrayList;

import model.interfaces.ILine;
import model.interfaces.IText;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 * Ecole des Mines de Nantes
 * Major in Computer and Information System Engineering
 * Text.java 
 */
public class Text implements IText {

    /**
     * @see model.interfaces.IText#addLine(model.interfaces.ILine)
     */
    @Override
    public void addLine(ILine aLine) {
    }

    /**
     * @see model.interfaces.IText#removeLine()
     */
    @Override
    public void removeLine() {
    }

    /**
     * @see model.interfaces.IText#hasCursor()
     */
    @Override
    public boolean hasCursor() {
        return false;
    }

    /**
     * @see model.interfaces.IText#getLines()
     */
    @Override
    public ArrayList<ILine> getLines() {
        return null;
    }

}
