/**
 * 
 */
package model.classes;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.IBufferMemory;
import model.interfaces.IDocument;
import model.interfaces.ISection;
import model.interfaces.IText;

/**
 * 10 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau & Pierre Reliquet
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         Document.java
 *         A Document is composed of a an introduction text and a list of
 *         sections which can have
 *         subsections...
 */
class Document implements IDocument {
    /**
     * The introduction text of the document
     */
    private IText          introductionText  = new Text();
    /**
     * The sections list of the document
     */
    private List<ISection> sectionsList      = new ArrayList<ISection>();
    /**
     * True if it is the current document
     */
    private boolean        isCurrentDocument = false;
    /**
     * The buffer memory of this document
     */
    private IBufferMemory  bufferMemory = Factory.createBufferMemory();
    /**
     * The path of the document
     */
    private String path = "";

    public Document() {
        introductionText = new Text();
        sectionsList = new ArrayList<ISection>();
        path =" ";
    }

    public Document(String path) {
        this();
        this.path = path;
    }

    /**
     * @return the sectionsList
     */
    public List<ISection> getSectionsList() {
        return sectionsList;
    }

    /**
     * @param sectionsList
     *            the sectionsList to set
     */
    public void setSectionsList(List<ISection> sectionsList) {
        this.sectionsList = sectionsList;
    }

    /**
     * @see model.interfaces.IDocument#deleteSection()
     */
    @Override
    public boolean deleteSection() {
        int index = 0;
        while (this.getCurrentSection().equals(sectionsList.get(index))) {
            index++;
        }
        return this.sectionsList.remove(sectionsList.get(index));
    }

    /**
     * @see model.interfaces.IDocument#deleteSection(model.interfaces.ISection)
     */
    @Override
    public boolean deleteSection(ISection aSection) {
        return this.sectionsList.remove(aSection);
    }

    /**
     * @see model.interfaces.IDocument#addSection(model.interfaces.ISection)
     */
    @Override
    public boolean addSection(ISection aSection) {
        this.sectionsList.add(this.getIndexCurrentSection(), aSection);
        return this.sectionsList.contains(aSection);
    }

    /**
     * @see model.interfaces.IDocument#appendSection(model.interfaces.ISection)
     */
    @Override
    public boolean appendSection(ISection aSection) {
        return this.sectionsList.add(aSection);
    }

    /**
     * @see model.interfaces.IDocument#getText()
     */
    @Override
    public IText getText() {
        return introductionText;
    }

    /**
     * @see model.interfaces.IDocument#setText(model.interfaces.IText)
     */
    @Override
    public void setText(IText aText) {
        this.introductionText = aText;
    }

    /**
     * @see model.interfaces.IDocument#getPath()
     */
    @Override
    public String getPath() {
        return path;
    }

    /**
     * @see model.interfaces.IDocument#setPath(java.net.URL)
     */
    @Override
    public void setPath(String aString) {
        this.path = aString;
    }

    /**
     * @see model.interfaces.IDocument#getCurrentSection()
     */
    @Override
    public ISection getCurrentSection() {
        return this.sectionsList.get(this.getIndexCurrentSection());
    }

    /**
     * @see model.interfaces.IDocument#getIindexCurrentSection()
     */
    @Override
    public int getIndexCurrentSection() {
        int index = 0;
        while (this.sectionsList.get(index).isCurrentSection()) {
            index++;
        }
        return index;
    }

    /**
     * @see model.interfaces.IDocument#isCurrentDocument()
     */
    @Override
    public boolean isCurrentDocument() {
        return isCurrentDocument;
    }

    /**
     * @see model.interfaces.IDocument#setIsCurrentDocument(boolean)
     */
    @Override
    public void setIsCurrentDocument(boolean aIsCurrentDocument) {
        this.isCurrentDocument = aIsCurrentDocument;
    }

    /**
     * @return the bufferMemory
     */
    public IBufferMemory getBufferMemory() {
        return bufferMemory;
    }

    /**
     * @param bufferMemory the bufferMemory to set
     */
    public void setBufferMemory(IBufferMemory bufferMemory) {
        this.bufferMemory = bufferMemory;
    }
}
