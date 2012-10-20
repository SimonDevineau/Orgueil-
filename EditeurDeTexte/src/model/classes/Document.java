/**
 * 
 */
package model.classes;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.IDocument;
import model.interfaces.IText;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         Document.java
 */
// TODO: Demander à Pierre si un singleton peut implementer une interface, je ne
// suis pas certain
public final class Document // implements IDocument {
{
    /**
     * The single instance of the document.
     */
    // L'utilisation du mot clé volatile permet, en Java version 5 et supérieur,
    // d'éviter le cas où "Singleton.instance" est non-nul,
    // mais pas encore "réellement" instancié.
    private static volatile Document documentInstance;
    /**
     * The introduction text of the document
     */
    private IText                    introductionText = new Text();
    /**
     * The sections list of the document
     */
    private List<Section>            sectionsList     = new ArrayList<Section>();

    private Document() {
        introductionText = new Text();
        sectionsList = new ArrayList<Section>();
    }

    public static Document getDocumentInstance() {
        initialize();
        return documentInstance;
    }

    /**
     * @return the introductionText
     */
    public IText getIntroductionText() {
        return introductionText;
    }

    /**
     * @param introductionText
     *            the introductionText to set
     */
    public void setIntroductionText(IText introductionText) {
        this.introductionText = introductionText;
    }

    /**
     * @return the sectionsList
     */
    public List<Section> getSectionsList() {
        return sectionsList;
    }

    /**
     * @param sectionsList
     *            the sectionsList to set
     */
    public void setSectionsList(List<Section> sectionsList) {
        this.sectionsList = sectionsList;
    }

    /**
     * This method creates an instance of Document if there is no other instance
     * of Document already created.
     */
    private static void initialize() {
        if (Document.documentInstance == null) {
            // The double checked of the documentInstance existence prevent from
            // calling the method synchronized.
            synchronized (Document.documentInstance) {
                if (Document.documentInstance == null) {
                    Document.documentInstance = new Document();
                }
            }
        }
    }
}
