package model.classes;

import java.util.Stack;

import model.interfaces.IStorable;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         BufferMemory.java
 */
public final class BufferMemory {
    private static volatile BufferMemory bufferMemoryInstance;
    /**
     * The stack of a document
     */
    private Stack<IStorable>             documentStack = new Stack<IStorable>();

    /**
     * 
     */
    public BufferMemory() {
        documentStack = new Stack<IStorable>();
    }

    /**
     * @see model.interfaces.IBufferMemory#add(model.interfaces.IStorable)
     */
    public boolean add(IStorable aStorable) {
        initialize();
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
     * @see model.interfaces.IBufferMemory#peek()
     */
    public IStorable peek() {
        initialize();
        return this.documentStack.peek();
    }

    /**
     * @see model.interfaces.IBufferMemory#pop()
     */
    public IStorable pop() {
        initialize();
        return this.documentStack.pop();
    }

    /**
     * @see model.interfaces.IBufferMemory#push(IStorable)
     */
    public IStorable push(IStorable storable) {
        initialize();
        return this.documentStack.push(storable);
    }

    /**
     * @see model.interfaces.IBufferMemory#remove(model.interfaces.IStorable)
     */
    public boolean remove(IStorable aStorable) {
        initialize();
        return this.documentStack.remove(aStorable);
    }

    /**
     * @param documentStack
     *            the documentStack to set
     */
    public void setDocumentStack(Stack<IStorable> documentStack) {
        initialize();
        this.documentStack = documentStack;
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

    private static void initialize() {
        if (bufferMemoryInstance == null) {
            synchronized (Cursor.class) {
                if (bufferMemoryInstance == null) {
                    bufferMemoryInstance = new BufferMemory();
                }
            }
        }
    }
}
