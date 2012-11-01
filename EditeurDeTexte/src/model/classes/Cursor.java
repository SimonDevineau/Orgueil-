package model.classes;

import model.interfaces.IDocument;
import model.interfaces.ILine;
import model.interfaces.ISection;

/**
 * 31 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         Cursor.java
 */
public final class Cursor {
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
     * The current document of the editor
     */
    private IDocument              currentDocument;
    /**
     * The current position of the cursor in the current line
     */
    private int                    currentPosition = 0;

    private Cursor() {
        this.currentLine = null;
        this.currentSection = null;
        this.currentDocument = Factory.createDocument();
        setCurrentPosition(0);
    }

    /**
     * @return the cursorInstance
     */
    public static Cursor getCursorInstance() {
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
        return currentLine;
    }

    /**
     * @param currentLine
     *            the currentLine to set
     */
    public void setCurrentLine(ILine currentLine) {
        initialize();
        this.currentLine = currentLine;
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
        this.currentSection = currentSection;
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
        this.currentPosition = currentPosition;
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
}
