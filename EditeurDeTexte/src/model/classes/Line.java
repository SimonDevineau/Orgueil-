package model.classes;

import java.util.Observable;
import java.util.Observer;

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
    private boolean       _IsCurrent;

    /**
     * Default constructor which creates an empty line
     */
    Line() {
        this._Line = new StringBuilder();
        Cursor.instance().setCurrentLine(this);
        this.addObserver(Cursor.instance());
    }

    /**
     * The constructor to create a line using the CharSequence as starting text.
     * @param sequence
     *            , the CharSequence which contains the basis.
     */
    Line(CharSequence sequence) {
        this();
        this.addUnderCursor(sequence);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void addUnderCursor(CharSequence insertion) {
        // TODO vérifier si c'est _CursorLocation ou _CursorLocation+1
        if (this.hasCursor()
                && Cursor.instance().getCurrentPosition() < this._Line.length()) {
            this._Line
                    .insert(Cursor.instance().getCurrentPosition(), insertion);
            Cursor.instance()
                    .setCurrentPosition(
                            Cursor.instance().getCurrentPosition()
                                    + insertion.length());
        }
        else if (this.hasCursor()
                && Cursor.instance().getCurrentPosition() >= this._Line
                        .length()) {
            this._Line.insert(this._Line.length(), insertion);
            Cursor.instance().setCurrentPosition(
                    this._Line.length() + insertion.length());
        }
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void append(CharSequence content) {
        if (this.hasCursor()) {
            this._Line.append(content);
            Cursor.instance().setCurrentPosition(
                    Cursor.instance().getCurrentPosition() + content.length());
            this.setChanged();
            this.notifyObservers();
        }
    }

    @Override
    public void deleteUnderCursor() {
        if (this.hasCursor()) {
            this._Line.deleteCharAt(Cursor.instance().getCurrentPosition());
            this.setChanged();
            this.notifyObservers();
        }
    }

    @Override
    public boolean hasCursor() {
        return this._IsCurrent;
    }

    @Override
    public int length() {
        return this._Line.length();
    }

    @Override
    public void replaceUnderCursor(CharSequence replacement) {
        if (this.hasCursor()) {
            // TODO vérifier les index
            // Copying the start of the line (before the cursor)
            StringBuilder tmp = new StringBuilder(this._Line.substring(0,
                    Cursor.instance().getCurrentPosition()));
            // Adding the replacement
            tmp.append(replacement);
            // TODO vérifier les index
            // Calculating the new cursor location
            int newCursorLocation = Cursor.instance().getCurrentPosition()
                    + replacement.length();
            // If the line is longer than the new cursor location we need
            // to append the rest of the line
            if (this._Line.length() > newCursorLocation) {
                tmp.append(this._Line.substring(newCursorLocation));
            }
            // We store the new variables.
            this._Line = tmp;
            Cursor.instance().setCurrentPosition(newCursorLocation);
            this.setChanged();
            this.notifyObservers();
        }
    }

    /*
    *//**
     * @see model.interfaces.ILine#hasCursor()
     */
    /*
     * @Override public boolean hasCursor() { //TODO equals or ==, plutot == car
     * on veut que ca soit la meme case m�moire return
     * Cursor.getCursorInstance().getCurrentLine().equals(this); }
     */
    @Override
    public void setCurrent(boolean isCurrent) {
        this._IsCurrent = isCurrent;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        int index = 0;
        // If the line has the cursor, this one has to be added to the line
        if (this.hasCursor()) {
            // FBefore adding the cursor, we get all the char placed before this
            // one
            while (index < Cursor.instance().getCurrentPosition() - 1
                    && index < this._Line.length()) {
                toReturn.append(this._Line.charAt(index));
                index++;
            }
            // If there is no char before the cursor, the line is empty, we add
            // an "_" to display a fake cursor
            if (this._Line.length() == 0) {
                toReturn.append("<span style=\"background-color:red;text-decoration:blink;\">"
                        + "_" + "</span>");
            }
            // If there are chars before we place the cursor on the
            // corresponding char and complete the line
            else {
                if (index < this._Line.length() || index == 0) {
                    toReturn.append("<span style=\"text-decoration:underline;background-color:red;text-decoration:blink;\">"
                            + this._Line.charAt(index) + "</span>");
                }
                else {
                    toReturn.append("<span style=\"text-decoration:underline;background-color:red;text-decoration:blink;\">"
                            + this._Line.charAt(this._Line.length() - 1)
                            + "</span>");
                }
                index++;
                while (index < this._Line.length()) {
                    toReturn.append(this._Line.charAt(index));
                    index++;
                }
            }
        }
        else {
            toReturn = this._Line;
        }
        return toReturn.toString();
    }

    @Override
    public String toStringWithoutHTML() {
        String toReturn = "";
        String debut = "";
        String fin = "";
        int index = 0;
        while (index < this._Line.length()) {
            toReturn += (this._Line.charAt(index));
            index++;
        }
        if (toReturn.contains("<span") && toReturn.contains("</span>")) {
            debut = toReturn.substring(index, toReturn.indexOf("<span"));
            fin = toReturn.substring(toReturn.lastIndexOf("</span>"));
        }
        System.out.println(debut + fin);
        return debut + fin;
    }
}
