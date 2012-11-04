/**
 * 
 */
package model.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

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
    protected IText          text              = Factory.createText();
    /**
     * The sections list of the document
     */
    protected List<ISection> subSections       = new ArrayList<ISection>();
    /**
     * True if it is the current document
     */
    private boolean          isCurrentDocument = false;

    public Document() {
        this.text = Factory.createText();
        this.subSections = new ArrayList<ISection>();
        this.addObserver(Cursor.instance());
    }

    /**
     * @see model.interfaces.IDocument#addLine(model.interfaces.ILine)
     */
    @Override
    public void addLine(ILine aLine) {
        if (Cursor.instance().getCurrentDocument().equals(this)
                && aLine != null) {
            // If the current section is null, we are in the text of the
// document
            if (Cursor.instance().getCurrentSection() == null) {
                this.text.addLine(aLine);
            }
            else {
                // If the cursor is on the title, we add the line at the
// begining of the text
                if (Cursor
                        .instance()
                        .getCurrentLine()
                        .equals(Cursor.instance().getCurrentSection()
                                .getTitle())) {
                    Cursor.instance()
                            .getCurrentSection()
                            .getText()
                            .addBeforeLine(
                                    Cursor.instance().getCurrentSection()
                                            .getText().getLine(0), aLine);
                }
                else {
                    Cursor.instance()
                            .getCurrentSection()
                            .getText()
                            .addLine(
                                    Cursor.instance().getCurrentSection()
                                            .getText().getLine(0), aLine);
                }
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
        if (this.subSections.size() == 0) {
            this.addSection(aSection, 0);
        }
        else {
            this.addSection(aSection, this.indexOfCurrentSection() + 1);
        }
        return this.subSections.contains(aSection);
    }

    @Override
    public void addSection(ISection aSection, int index) {
        this.subSections.add(index, aSection);
        for (ISection section : this.subSections) {
            section.setIsCurrentSection(false);
        }
        this.subSections.get(index).setIsCurrentSection(true);
        Cursor.instance().setCurrentSection(this.subSections.get(index));
        Cursor.instance().setCurrentLine(
                Cursor.instance().getCurrentSection().getTitle());
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @see model.interfaces.IDocument#appendSection(model.interfaces.ISection)
     */
    @Override
    public boolean appendSection(ISection aSection) {
        this.setChanged();
        this.notifyObservers();
        return this.subSections.add(aSection);
    }

    /**
     * @see model.interfaces.IDocument#getCurrentSection()
     */
    @Override
    public ISection getCurrentSection() {
        int sectionsSize = this.subSections.size();
        int currentIndex = 0;
        while (currentIndex < sectionsSize
                && !this.getSectionsList().get(currentIndex).isCurrentSection()) {
            currentIndex++;
        }
        if (currentIndex == sectionsSize) {
            throw new RuntimeException("No current section found!");
        }
        return this.getSectionsList().get(currentIndex).getCurrentSection();
    }

    /**
     * @see model.interfaces.IDocument#getSection(int)
     */
    @Override
    public ISection getSection(int index) {
        return this.subSections.get(index);
    }

    /**
     * @return the sectionsList
     */
    public List<ISection> getSectionsList() {
        return this.subSections;
    }

    /**
     * @see model.interfaces.IDocument#getSubSections()
     */
    @Override
    public ArrayList<ISection> getSubSections() {
        return (ArrayList<ISection>) this.subSections;
    }

    /**
     * @see model.interfaces.IDocument#getText()
     */
    @Override
    public IText getText() {
        return this.text;
    }

    /**
     * @see model.interfaces.IDocument#getIindexCurrentSection()
     */
    @Override
    public int indexOfCurrentSection() {
        int subSectionsSize = this.getSubSections().size();
        if (this.getSubSections() == null || subSectionsSize == 0) {
            return -1;
        }
        int currentIndex = 0;
        while (currentIndex < subSectionsSize
                && !this.getSubSections().get(currentIndex).isCurrentSection()) {
            currentIndex++;
        }
        if (currentIndex == subSectionsSize) {
            return -1;
        }
        else {
            return currentIndex;
        }
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
     * @see model.interfaces.IDocument#deleteSection()
     */
    @Override
    public boolean removeSection() {
        // TODO vérifier
        int index = 0;
        while (this.getCurrentSection().equals(this.subSections.get(index))) {
            index++;
        }
        this.setChanged();
        this.notifyObservers();
        return this.subSections.remove(this.subSections.get(index));
    }

    @Override
    public ISection removeSection(int index) {
        return this.subSections.remove(index);
    }

    /**
     * @see model.interfaces.IDocument#deleteSection(model.interfaces.ISection)
     */
    @Override
    public boolean removeSection(ISection aSection) {
        this.setChanged();
        this.notifyObservers();
        return this.subSections.remove(aSection);
    }

    /**
     * @see model.interfaces.IDocument#setIsCurrentDocument(boolean)
     */
    @Override
    public void setIsCurrentDocument(boolean aIsCurrentDocument) {
        this.isCurrentDocument = aIsCurrentDocument;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @param sectionsList
     *            the sectionsList to set
     */
    public void setSectionsList(List<ISection> sectionsList) {
        this.subSections = sectionsList;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @see model.interfaces.IDocument#setSubSections(java.util.ArrayList)
     */
    @Override
    public void setSubSections(ArrayList<ISection> aSubSections) {
        this.subSections = aSubSections;
    }

    /**
     * @see model.interfaces.IDocument#setText(model.interfaces.IText)
     */
    @Override
    public void setText(IText aText) {
        this.text = aText;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String toReturn = "<html>" + this.getText().toString() + "<br/>";
        int size = this.subSections.size();
        for (int i = 0; i < size; i++) {
            toReturn += this.subSections.get(i).toString() + "<br/>";
        }
        toReturn += "</html>";
        return toReturn;
    }
}
