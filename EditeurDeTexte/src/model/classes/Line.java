package model.classes;

import java.util.Observable;
import java.util.Observer;

import model.interfaces.ICommandVisitor;
import model.interfaces.ILine;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering Line.java
 */
class Line extends Observable implements ILine {
    /**
     * The representation of the line as a StringBuffer to avoid creating a new
     * string each time that a char is added.
     */
    private StringBuilder _Line;
    /**
     * The variable to store the variable location inside the line.
     */
    private int           _CursorLocation = NO_CURSOR;

    /**
     * Default constructor which creates an empty line
     */
    Line() {
        _Line = new StringBuilder();
        _CursorLocation = 0;
        Cursor.getCursorInstance().setCurrentLine(this);
        this.addObserver(Cursor.getCursorInstance());
    }

    /**
     * The constructor to create a line using the CharSequence as starting text.
     * @param sequence
     *            , the CharSequence which contains the basis.
     */
    Line(CharSequence sequence) {
        _Line = new StringBuilder(sequence);
        Cursor.getCursorInstance().setCurrentLine(this);
    }

    @Override
    public void addUnderCursor(CharSequence insertion) {
        System.out.println(this.countObservers());
        // TODO vérifier si c'est _CursorLocation ou _CursorLocation+1
        if (hasCursor()) {
            System.out.println("text " + insertion);
            _Line.insert(_CursorLocation, insertion);
        }
        setCursorLocation(_CursorLocation + insertion.length());
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void append(CharSequence content) {
        _Line.append(content);
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void deleteUnderCursor() {
        if (hasCursor()) {
            _Line.deleteCharAt(_CursorLocation);
        }
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public int getCursorLocation() {
        return _CursorLocation;
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
            StringBuilder tmp = new StringBuilder(_Line.substring(0,
                    _CursorLocation));
            // Adding the replacement
            tmp.append(replacement);
            // TODO vérifier les index
            // Calculating the new cursor location
            int newCursorLocation = _CursorLocation + replacement.length();
            // If the line is longer than the new cursor location we need
            // to append the rest of the line
            if (_Line.length() > newCursorLocation) {
                tmp.append(_Line.substring(newCursorLocation));
            }
            // We store the new variables.
            _Line = tmp;
            _CursorLocation = newCursorLocation;
            setCursorLocation(newCursorLocation);
        }
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @see model.interfaces.IStorable#accept(model.interfaces.ICommandVisitor)
     */
    @Override
    public void accept(ICommandVisitor aVisitor) {
        aVisitor.visit(this);
    }

    @Override
    public void setCursorLocation(int position) {
        if (position != _CursorLocation) {
            _CursorLocation = position;
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public void removeCursor() {
        setCursorLocation(NO_CURSOR);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public int length() {
        return _Line.length();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        int index = 0;
        System.out.println("index cursor " + _CursorLocation);
        System.out.println("text " + _Line.toString());
        if (this.hasCursor()) {
            while (index <= _CursorLocation && index < _Line.length()) {
                toReturn.append(_Line.charAt(index));
                index++;
            }
            if (_Line.length() == 0) {
                toReturn.append("<span style=\"background-color:red;text-decoration:blink;\">"
                        + "_" + "</span>");
            }
            else {
                toReturn.append("<span style=\"text-decoration:underline;background-color:red;text-decoration:blink;\">"
                        + _Line.charAt(index) + "</span>");
                while (index < _Line.length()) {
                    index++;
                    toReturn.append(_Line.charAt(index));
                }
            }
        }
        else {
            toReturn = _Line;
        }
        return toReturn.toString();
    }
/*
    */ /**
     * @see model.interfaces.ILine#hasCursor()
     */
    /*
     * @Override
     * public boolean hasCursor() {
     * //TODO equals or ==, plutot == car on veut que ca soit la meme case
     * m�moire
     * return Cursor.getCursorInstance().getCurrentLine().equals(this);
     * }
     */
}
