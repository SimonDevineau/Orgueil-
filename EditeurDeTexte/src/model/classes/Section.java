package model.classes;

import java.util.ArrayList;

import model.interfaces.ILine;
import model.interfaces.ISection;
import model.interfaces.IText;

/**
 *9 oct. 2012 - EditeurDeTexte.
 *@author Simon Devineau
 *Ecole des Mines de Nantes
 *Major in Computer and Information System Engineering
 *Section.java 
 */
public class Section implements ISection {

    /**
     * @see model.interfaces.ISection#add(model.interfaces.ISection)
     */
    @Override
    public void add(ISection aSection) {
    }

    /**
     * @see model.interfaces.ISection#getParent()
     */
    @Override
    public ISection getParent() {
        return null;
    }

    /**
     * @see model.interfaces.ISection#getChildren()
     */
    @Override
    public ArrayList<ISection> getChildren() {
        return null;
    }

    /**
     * @see model.interfaces.ISection#getTitle()
     */
    @Override
    public ILine getTitle() {
        return null;
    }

    /**
     * @see model.interfaces.ISection#getIntroduction()
     */
    @Override
    public IText getIntroduction() {
        return null;
    }
}
