package model.classes;

import java.util.AbstractCollection;
import java.util.HashSet;
import java.util.Iterator;

import model.interfaces.IDocument;

/**
 * 21 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         Editor.java
 *         This interface represents the object Editor which has a single
 *         instance.
 *         An Editor is composed of several documents.
 */
// TODO Demander a Pierre si un HashSet est util, faire IBufferMemory, regarder
// si toutes les méthodes getCurrent... sont bonnes. Trouver un systeme pour le
// cursor
public final class Editor {
    // L'utilisation du mot clé volatile permet, en Java version 5 et supérieur,
    // d'éviter le cas où "Singleton.instance" est non-nul,
    // mais pas encore "réellement" instancié.
    /**
     * The single instance of Editor
     */
    private static volatile Editor       editorInstance;
    /**
     * The list of documents which composed the Editor
     */
    public AbstractCollection<IDocument> documents = new HashSet<IDocument>();

    private Editor() {
        documents = new HashSet<IDocument>();
    }

    /**
     * @return the single instance of Editor.
     */
    public static Editor getEditor() {
        initialize();
        return Editor.editorInstance;
    }

    /**
     * @param aDocument
     *            , the document to add
     * @return true if the document has been added
     */
    public boolean addDocument(IDocument aDocument) {
        return documents.add(aDocument);
    }

    /**
     * @param aDocument
     *            , the document to remove.
     * @return true if the document has been removed.
     */
    public boolean removeDocument(IDocument aDocument) {
        return documents.remove(aDocument);
    }

    /**
     * @return true if the current document has been removed
     */
    public boolean removeCurrentDocument() {
        return documents.remove(getCurrentDocument());
    }

    /**
     * @return the list of documents
     */
    public HashSet<IDocument> getDocuments() {
        return (HashSet<IDocument>) documents;
    }

    /**
     * @param documentsList
     *            , the new list of documents.
     */
    public void setDocuments(AbstractCollection<IDocument> documentsList) {
        this.documents = documentsList;
    }

    /**
     * @return the current Document
     */
    public IDocument getCurrentDocument() {
        Iterator<IDocument> iterator = documents.iterator();
        IDocument docTemp = null;
        IDocument toReturn = null;
        while (iterator.hasNext()
                && !(docTemp = (IDocument) iterator.next()).isCurrentDocument()) {
            toReturn = docTemp;
        }
        return toReturn;
    }

    private static void initialize() {
        if (editorInstance == null) {
            synchronized (Editor.class) {
                if (editorInstance == null) {
                    editorInstance = new Editor();
                }
            }
        }
    }
}
