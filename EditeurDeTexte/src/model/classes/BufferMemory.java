package model.classes;

import java.util.Stack;

import model.interfaces.IBufferMemory;
import model.interfaces.IStorable;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         BufferMemory.java
 */
class BufferMemory implements IBufferMemory {
    /**
     * The stack of a document
     */
    private Stack<IStorable> documentStack = new Stack<IStorable>();

    /**
     * @see model.interfaces.IBufferMemory#add(model.interfaces.IStorable)
     */
    @Override
    public boolean add(IStorable aStorable) {
        return this.documentStack.add(aStorable);
    }

    /**
     * @return the documentStack
     */
    public Stack<IStorable> getDocumentStack() {
        return this.documentStack;
    }

    /**
     * @see model.interfaces.IBufferMemory#peek()
     */
    @Override
    public IStorable peek() {
        return this.documentStack.peek();
    }

    /**
     * @see model.interfaces.IBufferMemory#pop()
     */
    @Override
    public IStorable pop() {
        return this.documentStack.pop();
    }

    /**
     * @see model.interfaces.IBufferMemory#push(IStorable)
     */
    @Override
    public IStorable push(IStorable storable) {
        return this.documentStack.push(storable);
    }

    /**
     * @see model.interfaces.IBufferMemory#remove(model.interfaces.IStorable)
     */
    @Override
    public boolean remove(IStorable aStorable) {
        return this.documentStack.remove(aStorable);
    }

    /**
     * @param documentStack
     *            the documentStack to set
     */
    public void setDocumentStack(Stack<IStorable> documentStack) {
        this.documentStack = documentStack;
    }
}
