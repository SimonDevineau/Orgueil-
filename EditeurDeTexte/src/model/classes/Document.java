/**
 * 
 */
package model.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.interfaces.ICommandVisitor;
import model.interfaces.IDocument;
import model.interfaces.ILine;
import model.interfaces.ISection;
import model.interfaces.IText;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet Ecole des Mines de Nantes Major in
 *         Computer and Information System Engineering Document.java A Document
 *         is composed of a an introduction text and a list of sections which
 *         can have subsections...
 */
class Document extends Observable implements IDocument {
    /**
     * The introduction text of the document
     */
    protected IText          text  = Factory.createText();
    /**
     * The sections list of the document
     */
    protected List<ISection> sousSections= new ArrayList<ISection>();
    /**
     * True if it is the current document
     */
    private boolean        isCurrentDocument = false;

    public Document() {
        this.text = Factory.createText();
        this.sousSections = new ArrayList<ISection>();

        this.addObserver(Cursor.instance());
    }

    /**
     * @see model.interfaces.IDocument#addLine(model.interfaces.ILine)
     */
    @Override
    public void addLine(ILine aLine) {
        System.out.println("ligne document " + aLine);
        if (Cursor.instance().getCurrentDocument().equals(this)) {
            // Si la section est null, c'est que nous sommes dans le texte
// introductif du document
            if (Cursor.instance().getCurrentSection() == null) {
                this.text.addLine(aLine);
            }
            else {
                System.out.println("dans section, je rajoute une ligne");
                Cursor.instance().getCurrentSection().getText()
                        .addLine(aLine);
            }
        }
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @see model.interfaces.IDocument#addSection(model.interfaces.ISection)
     */
    @Override
    public boolean addSection(ISection aSection) {
        this.sousSections.add(this.indexCurrentSection(), aSection);
        // J'ai rajouté
        for (ISection section : sousSections) {
            section.setIsCurrentSection(false);
        }
        aSection.setIsCurrentSection(true);
        Cursor.instance().setCurrentLine(
                Cursor.instance().getCurrentSection().getTitle());
        this.setChanged();
        this.notifyObservers();
        return this.sousSections.contains(aSection);
    }

    @Override
    public void addSection(ISection section, int index) {
        this.setChanged();
        this.notifyObservers();
        sousSections.add(index, section);
    }

    /**
     * @see model.interfaces.IDocument#appendSection(model.interfaces.ISection)
     */
    @Override
    public boolean appendSection(ISection aSection) {
        return this.sousSections.add(aSection);
    }

    /**
     * @see model.interfaces.IDocument#deleteSection()
     */
    @Override
    public boolean removeSection() {
        // TODO vérifier
        int index = 0;
        while (this.getCurrentSection().equals(this.sousSections.get(index))) {
            index++;
        }
        this.setChanged();
        this.notifyObservers();
        return this.sousSections.remove(this.sousSections.get(index));
    }

    /**
     * @see model.interfaces.IDocument#deleteSection(model.interfaces.ISection)
     */
    @Override
    public boolean removeSection(ISection aSection) {
        this.setChanged();
        this.notifyObservers();
        return this.sousSections.remove(aSection);
    }

    /**
     * @see model.interfaces.IDocument#getCurrentSection()
     */
    @Override
    public ISection getCurrentSection() {
        int sectionsSize = sousSections.size();
        int currentIndex = 0;
        while (currentIndex < sectionsSize
                && !getSectionsList().get(currentIndex).isCurrentSection())
            currentIndex++;
        if (currentIndex == sectionsSize)
            throw new RuntimeException("No current section found!");
        return getSectionsList().get(currentIndex).getCurrentSection();
    }

    /**
     * @see model.interfaces.IDocument#getIindexCurrentSection()
     */
    @Override
    public int indexCurrentSection() {
        int sectionsSize = sousSections.size();
        int index = 0;
        while (index < sectionsSize
                && !this.sousSections.get(index).isCurrentSection())
            index++;
        return index;
    }

    /**
     * @see model.interfaces.IDocument#getSection(int)
     */
    @Override
    public ISection getSection(int index) {
        return sousSections.get(index);
    }

    /**
     * @return the sectionsList
     */
    public List<ISection> getSectionsList() {
        return this.sousSections;
    }

    /**
     * @see model.interfaces.IDocument#getText()
     */
    @Override
    public IText getText() {
        return this.text;
    }

    /**
     * @see model.interfaces.IDocument#isCurrentDocument()
     */
    @Override
    public boolean isCurrentDocument() {
        this.setChanged();
        this.notifyObservers();
        return this.isCurrentDocument;
    }

    /**
     * @see model.interfaces.IDocument#setIsCurrentDocument(boolean)
     */
    @Override
    public void setIsCurrentDocument(boolean aIsCurrentDocument) {
        this.setChanged();
        this.notifyObservers();
        this.isCurrentDocument = aIsCurrentDocument;
    }

    /**
     * @param sectionsList
     *            the sectionsList to set
     */
    public void setSectionsList(List<ISection> sectionsList) {
        this.setChanged();
        this.notifyObservers();
        this.sousSections = sectionsList;
    }

    /**
     * @see model.interfaces.IDocument#setText(model.interfaces.IText)
     */
    @Override
    public void setText(IText aText) {
        this.setChanged();
        this.notifyObservers();
        this.text = aText;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String toReturn = this.getText().toString() + "<br/>";
        int size = sousSections.size();
        for (int i = 0; i < size; i++) {
            toReturn += sousSections.get(i).toString() + "<br/>";
        }
        return toReturn;
    }
}
