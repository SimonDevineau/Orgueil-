package model.classes;

import java.util.Observable;
import java.util.Stack;

import model.interfaces.IStorable;

/**
 * This class is the stack used to enable the copy-paste in our program. It can
 * only store the IStorable objects. It is kind of encapsulation class of the
 * standard java Stack<br/>
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering BufferMemory.java
 */
public final class BufferMemory extends Observable {
    /**
     * The volatile keyword has been added in java 5 and avoids many trouble
     * with the case when the singleton.instance was not null but not really
     * instantiated.
     */
    private static volatile BufferMemory bufferMemoryInstance;

    /**
     * Initializes the BufferMemory when another class tries to access it.
     */
    private static void initialize() {
        if (bufferMemoryInstance == null) {
            synchronized (Cursor.class) {
                if (bufferMemoryInstance == null) {
                    bufferMemoryInstance = new BufferMemory();
                }
            }
        }
    }

    /**
     * @return the bufferMemoryInstance
     */
    public static BufferMemory instance() {
        initialize();
        return bufferMemoryInstance;
    }

    /**
     * @param bufferMemoryInstance
     *            the bufferMemoryInstance to set
     */
    public static void setBufferMemoryInstance(BufferMemory bufferMemoryInstance) {
        initialize();
        BufferMemory.bufferMemoryInstance = bufferMemoryInstance;
    }

    /**
     * The stack of a document
     */
    private Stack<IStorable> documentStack = new Stack<IStorable>();

    /**
     * Default constructor which initializes the stack
     */
    public BufferMemory() {
        this.documentStack = new Stack<IStorable>();
    }

    /**
     * Add the element in the stack
     * @param aStorable
     *            the element to add at the end of the stack
     */
    public boolean add(IStorable aStorable) {
        initialize();
        this.update();
        return this.documentStack.add(aStorable);
    }

    /**
     * @return the documentStack
     */
    public Stack<IStorable> getDocumentStack() {
        initialize();
        return this.documentStack;
    }

    /**
     * returns the last element of the BufferMemory without removing it
     */
    public IStorable peek() {
        initialize();
        this.update();
        return this.documentStack.peek();
    }

    /**
     * return the last element of the BufferMemory and removes it
     */
    public IStorable pop() {
        initialize();
        this.update();
        return this.documentStack.pop();
    }

    /**
     * Add the parameter on the top of the stack
     * @param storable
     *            , the IStorable to add on top of the stack
     */
    public IStorable push(IStorable storable) {
        initialize();
        this.update();
        return this.documentStack.push(storable);
    }

    /**
     * Removes the parameter from the BufferMemory
     * @param aStorable
     *            , the IStorable to remove
     */
    public boolean remove(IStorable aStorable) {
        initialize();
        this.update();
        return this.documentStack.remove(aStorable);
    }

    /**
     * @param documentStack
     *            the documentStack to set
     */
    public void setDocumentStack(Stack<IStorable> documentStack) {
        initialize();
        this.update();
        this.documentStack = documentStack;
    }

    private void update() {
        this.setChanged();
        this.notifyObservers();
    }
}
