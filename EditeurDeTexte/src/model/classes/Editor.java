package model.classes;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import model.interfaces.IDocument;

/**
 * 21 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau Ecole des Mines de Nantes Major in Computer and
 *         Information System Engineering Editor.java This interface represents
 *         the object Editor which has a single instance. An Editor is composed
 *         of several documents.
 */
// TODO Trouver un systeme pour le cursor
public final class Editor extends Observable {
	// L'utilisation du mot cle volatile permet, en Java version 5 et superieur,
	// d'eviter le cas ou "Singleton.instance" est non-nul,
	// mais pas encore "reellement" instancie.
	/**
	 * The single instance of Editor
	 */
	private static volatile Editor editorInstance;

	/**
	 * The cursor of the Editor
	 */
	private Cursor cursor = Cursor.getCursorInstance();

	/**
	 * @return the single instance of Editor.
	 */
	public static Editor getEditor() {
		initialize();
		return editorInstance;
	}

	private static void initialize() {
		if (editorInstance == null) {
			// On ne synchronise qu'à l'intérieur du if pour améliorer les performances.
			synchronized (Editor.class) {
				if (editorInstance == null) {
					editorInstance = new Editor();
				}
			}
		}
	}

	/**
	 * The list of documents which composed the Editor
	 */
	private Set<IDocument> documents = new HashSet<IDocument>();

	private Editor() {
		this.documents = new HashSet<IDocument>();
		cursor = Cursor.getCursorInstance();
	}

	/**
	 * @param aDocument
	 *            , the document to add
	 * @return true if the document has been added
	 */
	public boolean addDocument(IDocument aDocument) {
		boolean result = this.documents.add(aDocument);
		this.setChanged();
		this.notifyObservers();
		return result;
	}

	/**
	 * @return the current Document
	 */
	public IDocument getCurrentDocument() {
		Iterator<IDocument> iterator = this.documents.iterator();
		IDocument docTemp = null;
		IDocument toReturn = null;
		while (iterator.hasNext()
				&& !(docTemp = iterator.next()).isCurrentDocument()) {
			toReturn = docTemp;
		}
		return toReturn;
	}

	/**
	 * @return the list of documents
	 */
	public HashSet<IDocument> getDocuments() {
		return (HashSet<IDocument>) this.documents;
	}

	/**
	 * @return true if the current document has been removed
	 */
	public boolean removeCurrentDocument() {
		return this.documents.remove(this.getCurrentDocument());
	}

	/**
	 * @param aDocument
	 *            , the document to remove.
	 * @return true if the document has been removed.
	 */
	public boolean removeDocument(IDocument aDocument) {
		return this.documents.remove(aDocument);
	}

	/**
	 * @param documentsList
	 *            , the new list of documents.
	 */
	public void setDocuments(Set<IDocument> documentsList) {
		this.documents = documentsList;
		this.setChanged();
		this.notifyObservers();
	}
}
