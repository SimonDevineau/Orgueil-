package model.classes;

import java.util.Observable;
import java.util.Observer;

import model.interfaces.IDocument;
import model.interfaces.ILine;
import model.interfaces.ISection;
import model.interfaces.IStorable;
import model.interfaces.IText;

/**
 * 31 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau Ecole des Mines de Nantes Major in Computer and
 *         Information System Engineering Cursor.java
 */
public final class Cursor extends Observable implements Observer {
    /**
     * the single instance of the cursor
     */
    private static volatile Cursor cursorInstance;
    /**
     * The current line in the document
     */
    private ILine                  currentLine;
    /**
     * The current section in the current document
     */
    private ISection               currentSection;
    /**
     * The introductive text of the current document
     */
    private IText                  currentTextIntro;
    /**
     * The current document of the editor
     */
    private IDocument              currentDocument;
    /**
     * The current position of the cursor in the current line
     */
    private int                    currentPosition = 0;

    private Cursor() {
    }

    /**
     * @return the cursorInstance
     */
    public static Cursor instance() {
        initialize();
        return cursorInstance;
    }

    /**
     * @param cursorInstance
     *            the cursorInstance to set
     */
    public static void setCursorInstance(Cursor cursorInstance) {
        initialize();
        Cursor.cursorInstance = cursorInstance;
    }

    /**
     * @return the currentLine
     */
    public ILine getCurrentLine() {
        initialize();
        if (currentLine == null) {
            Cursor.instance().getCurrentDocument()
                    .addLine(Factory.createLine());
        }
        return currentLine;
    }

    /**
     * @param _currentLine
     *            the currentLine to set
     */
    public void setCurrentLine(ILine _currentLine) {
        initialize();
        if (this.currentLine != null)
            this.currentLine.setCurrent(false);
        if (_currentLine != null)
            this.currentLine = _currentLine;
        else
            this.currentLine = Factory.createLine();
        this.currentLine.setCurrent(true);
        update(null, null);
    }

    public IStorable getCurrentStorable() {
        if (Cursor.instance().getCurrentSection() == null) {
            return currentSection;
        }
        else {
            return currentTextIntro;
        }
    }

    /**
     * @return the currentSection
     */
    public ISection getCurrentSection() {
        initialize();
        return currentSection;
    }

    /**
     * @param currentSection
     *            the currentSection to set
     */
    public void setCurrentSection(ISection currentSection) {
        initialize();
        if (this.currentSection != null
                && this.currentSection.isCurrentSection())
            this.currentSection.setIsCurrentSection(false);
        this.currentSection = currentSection;
        this.currentSection.setIsCurrentSection(true);
        if (getCurrentSection().getTitle().hasCursor()) {
            currentTextIntro = null;
            setCurrentLine(getCurrentSection().getTitle());
        }
        else if (getCurrentSection().getText() != null)
            setCurrentText(getCurrentSection().getText());
        else {
            getCurrentSection().getText().addLine(Factory.createLine());
            setCurrentLine(getCurrentSection().getText().getLine(0));
        }
    }

    /**
     * @return the currentTextIintro
     */
    public IText getCurrentText() {
        return currentTextIntro;
    }

    /**
     * @param currentTextIintro
     *            the currentTextIintro to set
     */
    public void setCurrentText(IText currentTextIintro) {
        this.currentTextIntro = currentTextIintro;
        int textSize = currentTextIintro.size();
        int index = 0;
        while (index < textSize && !getCurrentText().getLine(index).hasCursor())
            index++;
        if (index == textSize && textSize <= 0) {
            /*if (currentSection instanceof IDocument) {
                currentTextIntro.addLine(Factory.createLine());
                setCurrentLine(currentTextIntro.getLine(0));
            }
            else if (currentSection.getTitle() != null)
                setCurrentLine(currentSection.getTitle());*/
        }
        else if (index == textSize && textSize > 0) {
            setCurrentLine(currentTextIntro.getLine(0));
        }
        else
            setCurrentLine(getCurrentText().getLine(index));
    }

    /**
     * @return the currentDocument
     */
    public IDocument getCurrentDocument() {
        initialize();
        return currentDocument;
    }

    /**
     * @param currentDocument
     *            the currentDocument to set
     */
    public void setCurrentDocument(IDocument currentDocument) {
        initialize();
        this.currentDocument = currentDocument;
    }

    /**
     * @return the currentPosition
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

    /**
     * @param currentPosition
     *            the currentPosition to set
     */
    public void setCurrentPosition(int currentPosition) {
        if (currentPosition == -1)
            currentPosition = 0;
        if (currentPosition > Cursor.instance().getCurrentLine().length()) {
            this.currentPosition = Cursor.instance().getCurrentLine().length();
        }
        else {
            this.currentPosition = currentPosition;
        }
        update(null, null);
    }

    private static void initialize() {
        if (cursorInstance == null) {
            synchronized (Cursor.class) {
                if (cursorInstance == null) {
                    cursorInstance = new Cursor();
                }
            }
        }
    }

    /**
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable aO, Object aArg) {
        this.setChanged();
        this.notifyObservers();
    }
}
