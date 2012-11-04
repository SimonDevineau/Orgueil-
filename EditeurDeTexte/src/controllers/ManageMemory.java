package controllers;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JList;

import model.classes.BufferMemory;

/**
 * 4 nov. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         ManageMemory.java
 */
public class ManageMemory implements Observer {
    private JList list = new JList();

    /**
     * 
     */
    public ManageMemory(JList list) {
        this.list = list;
    }

    /**
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable aArg0, Object aArg1) {
        this.list.setListData(BufferMemory.instance().getDocumentStack());
    }
}
