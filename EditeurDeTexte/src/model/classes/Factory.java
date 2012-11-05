package model.classes;

import model.interfaces.IDocument;
import model.interfaces.ILine;
import model.interfaces.ISection;
import model.interfaces.IText;

/**
 * This class is supposed to be, with the singleton and the interfaces, the only
 * classes which are
 * public because it is required to come through this class to instantiate the
 * other objects. <br/>
 * 10 oct. 2012 - EditeurDeTexte.
 * * @author Simon Devineau & Pierre Reliquet
 * Ecole des Mines de Nantes
 * Major in Computer and Information System Engineering
 * Factory.java
 */
public class Factory {
    /**
     * @return a new Document
     */
    public static IDocument createDocument() {
        return new Document();
    }

    /**
     * @return a new line
     */
    public static ILine createLine() {
        return new Line();
    }

    /**
     * @return a new Section
     */
    public static ISection createSection() {
        return new Section();
    }

    /**
     * @param aCurrentDocument
     * @param aTitle
     * @return
     */
    public static ISection createSection(IDocument parent, String aTitle) {
        return new Section(parent, aTitle);
    }

    /**
     * @param parent
     *            , the parent of the created section
     * @return a new section
     */
    public static ISection createSection(ISection parent) {
        return new Section(parent);
    }

    /**
     * @param aTextInput
     * @return
     */
    public static ISection createSection(String aTitle) {
        return new Section(aTitle);
    }

    /**
     * @return a new text
     */
    public static IText createText() {
        return new Text();
    }

    /**
     * @param aStringWithoutHTML
     * @return
     */
    public static ILine createLine(String aStringWithoutHTML) {
        return new Line(aStringWithoutHTML);
    }

    /**
     * @param aCurrentDoc
     * @return
     */
    public static ISection createSection(IDocument aCurrentDoc) {
        return new Section(aCurrentDoc);
    }
}
