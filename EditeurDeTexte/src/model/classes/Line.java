package model.classes;

import model.interfaces.ICommandVisitor;
import model.interfaces.ILine;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering Line.java
 */
class Line implements ILine {
    /**
     * The representation of the line as a StringBuffer to avoid creating a new
     * string each time that a char is added.
     */
    private StringBuilder _Line;
    private int           _CursorLocation = NO_CURSOR;

    /**
     * Default constructor which creates an empty line
     */
    Line() {
        this._Line = new StringBuilder();
    }

    /**
     * The constructor to create a line using the CharSequence as starting text.
     * @param sequence
     *            , the CharSequence which contains the basis.
     */
    Line(CharSequence sequence) {
        this._Line = new StringBuilder(sequence);
    }

    @Override
    public void addUnderCursor(CharSequence insertion) {
        // TODO vérifier si c'est _CursorLocation ou _CursorLocation+1
        if (this.hasCursor()) {
            this._Line.insert(this._CursorLocation, insertion);
        }
    }

    @Override
    public void append(CharSequence content) {
        this._Line.append(content);
    }

    @Override
    public void deleteUnderCursor() {
        if (this.hasCursor()) {
            this._Line.deleteCharAt(this._CursorLocation);
        }
    }

    @Override
    public int getCursorLocation() {
        return this._CursorLocation;
    }

    @Override
    public boolean hasCursor() {
        return this._CursorLocation != NO_CURSOR;
    }

    @Override
    public void replaceUnderCursor(CharSequence replacement) {
        if (this.hasCursor()) {
            // TODO vérifier les index
            // Copying the start of the line (before the cursor)
            StringBuilder tmp = new StringBuilder(this._Line.substring(0,
                    this._CursorLocation));
            // Adding the replacement
            tmp.append(replacement);
            // TODO vérifier les index
            // Calculating the new cursor location
            int newCursorLocation = this._CursorLocation + replacement.length();
            // If the line is longer than the new cursor location we need
            // to append the rest of the line
            if (this._Line.length() > newCursorLocation) {
                tmp.append(this._Line.substring(newCursorLocation));
            }
            // We store the new variables.
            this._Line = tmp;
            this._CursorLocation = newCursorLocation;
        }
    }

    /**
     * @see model.interfaces.IStorable#accept(model.interfaces.ICommandVisitor)
     */
    @Override
    public void accept(ICommandVisitor aVisitor) {
        aVisitor.visit(this);
    }
}
