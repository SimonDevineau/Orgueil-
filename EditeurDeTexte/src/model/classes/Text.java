package model.classes;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.ICommandVisitor;
import model.interfaces.ILine;
import model.interfaces.IText;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         Text.java
 */
class Text implements IText {
    /**
     * The list of lines
     */
    private List<ILine> linesList = new ArrayList<ILine>();

    public Text() {
        linesList = new ArrayList<ILine>();
    }

    /**
     * @see model.interfaces.IStorable#accept(model.interfaces.ICommandVisitor)
     */
    @Override
    public void accept(ICommandVisitor aVisitor) {
        aVisitor.visit(this);
    }

    /**
     * @see model.interfaces.IText#addLine(model.interfaces.ILine)
     */
    @Override
    public void addLine(ILine aLine) {
    }

    /**
     * @see model.interfaces.IText#getLines()
     */
    @Override
    public ArrayList<ILine> getLines() {
        return null;
    }

    /**
     * @see model.interfaces.IText#hasCursor()
     */
    @Override
    public boolean hasCursor() {
        return false;
    }

    /**
     * @see model.interfaces.IText#removeLine(ILine aLine)
     */
    @Override
    public void removeLine(ILine aLine) {
        if (aLine != null)
            linesList.remove(aLine);
    }

    /**
     * @return the linesList
     */
    public List<ILine> getLinesList() {
        return linesList;
    }

    /**
     * @param linesList
     *            the linesList to set
     */
    public void setLinesList(List<ILine> linesList) {
        this.linesList = linesList;
    }

    /**
     * @see model.interfaces.IText#addLine(model.interfaces.ILine,
     *      model.interfaces.ILine)
     */
    @Override
    public void addLine(ILine aCurrentLine, ILine aLineToPaste) {
        if (aCurrentLine != null && aLineToPaste != null) {
            linesList.add(linesList.lastIndexOf(aCurrentLine), aLineToPaste);
        }
    }
}
