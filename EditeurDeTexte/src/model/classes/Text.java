package model.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.interfaces.ILine;
import model.interfaces.IText;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering Text.java
 */
class Text extends Observable implements IText {
    /**
     * The list of lines
     */
    private List<ILine> linesList = new ArrayList<ILine>();

    public Text() {
        this.linesList = new ArrayList<ILine>();
        this.addObserver(Cursor.instance());
        Cursor.instance().setCurrentText(this);
    }

    /**
     * @see model.interfaces.IText#addBeforeLine(model.interfaces.ILine,
     *      model.interfaces.ILine)
     */
    @Override
    // TODO CHECK IF IT IS TRUE
    public void addBeforeLine(ILine aCurrentLine, ILine aLineToPaste) {
        if (aCurrentLine != null && aLineToPaste != null
                && this.linesList.lastIndexOf(aCurrentLine) >= 1) {
            this.linesList.add(this.linesList.lastIndexOf(aCurrentLine),
                    aLineToPaste);
        }
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @see model.interfaces.IText#addLine(model.interfaces.ILine)
     */
    @Override
    public void addLine(ILine aLine) {
        if (aLine != null) {
            this.linesList.add(aLine);
        }
        else {
            this.linesList.add(Factory.createLine());
        }
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @see model.interfaces.IText#addLine(model.interfaces.ILine,
     *      model.interfaces.ILine)
     */
    @Override
    public void addLine(ILine aCurrentLine, ILine aLineToPaste) {
        if (aCurrentLine != null && aLineToPaste != null) {
            this.linesList.add(this.linesList.lastIndexOf(aCurrentLine),
                    aLineToPaste);
        }
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @see model.interfaces.IText#getLine(int)
     */
    @Override
    public ILine getLine(int index) {
        return this.linesList.get(index);
    }

    /**
     * @see model.interfaces.IText#getLines()
     */
    @Override
    public ArrayList<ILine> getLines() {
        return (ArrayList<ILine>) this.linesList;
    }

    /**
     * @return the linesList
     */
    public List<ILine> getLinesList() {
        return this.linesList;
    }

    /**
     * @see model.interfaces.IText#hasCursor()
     */
    @Override
    public boolean hasCursor() {
        int size = this.linesList.size();
        for (int i = 0; i < size; i++) {
            if (this.linesList.get(i).hasCursor()) {
                return true;
            }
        }
        return false;
    }

    private int indexOfCurrentLine() {
        int size = this.linesList.size();
        int index = 0;
        while (index < size && !this.getLine(index).hasCursor()) {
            index++;
        }
        if (index == size) {
            return -1;
        }
        return index;
    }

    @Override
    public void insertLine(ILine lineToInsert) {
        this.setChanged();
        this.notifyObservers();
        if (this.hasCursor()) {
            int index = this.indexOfCurrentLine();
            this.linesList.add(index + 1, lineToInsert);
        }
        else {
            this.addLine(lineToInsert);
        }
    }

    /**
     * @see model.interfaces.IText#removeLine(ILine aLine)
     */
    @Override
    public void removeLine(ILine aLine) {
        this.setChanged();
        this.notifyObservers();
        if (aLine != null) {
            this.linesList.remove(aLine);
        }
    }

    /**
     * @param linesList
     *            the linesList to set
     */
    public void setLinesList(List<ILine> linesList) {
        this.setChanged();
        this.notifyObservers();
        this.linesList = linesList;
    }

    /**
     * @see model.interfaces.IText#size()
     */
    @Override
    public int size() {
        return this.linesList.size();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String toReturn = "";
        for (ILine line : this.linesList) {
            toReturn += "<br/>" + line.toString();
        }
        return toReturn;
    }
}
