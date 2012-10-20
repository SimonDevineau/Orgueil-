/**
 * 
 */
package model.classes;

import model.interfaces.IDocument;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         Document.java
 */
// TODO: Demander ˆ Pierre si un singleton peut implementer une interface, je ne
// suis pas certain
public final class Document // implements IDocument {
{
    /**
     * The single instance of the document.
     */
    public static volatile Document documentInstance;

    private Document() {
    }

    public static Document getDocumentInstance() {
        initialize();
        return documentInstance;
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
