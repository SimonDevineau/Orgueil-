package model.classes;

import java.util.Observer;

import model.classes.states.StateFactory;
import model.interfaces.IDocument;
import model.interfaces.ILine;
import model.interfaces.ISection;
import model.interfaces.IState;

/**
 * 9 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau Ecole des Mines de Nantes Major in Computer and
 *         Information System Engineering Section.java
 */
class Section extends Document implements ISection {
    protected ILine     title;
    /**
     * The parent section.
     */
    // The value is null because a section hasn't got necessarily a parent
    protected IDocument parent           = null;
    /**
     * The current state which is used to print the section. This is used to
     * implement the state pattern. It enables to delegate instead of doing some
     * switch case with ifs.
     */
    protected IState    currentState;
    protected IState    deployedState;
    protected IState    hiddenState;
    /**
     * True if this section is the current section
     */
    protected boolean   isCurrentSection = false;
    /**
     * The number of parents of the section
     */
    protected int       nbParents        = 0;

    public Section() {
        super();
        this.hiddenState = StateFactory.createHiddenState(this);
        this.deployedState = StateFactory.createDeployedState(this);
        this.currentState = this.deployedState;
        this.title = Factory.createLine();
        this.isCurrentSection = true;
    }

    public Section(IDocument parent) {
        this();
        this.setParent(parent);
    }

    public Section(IDocument parent, String aTitle) {
        this(parent);
        this.title.append(aTitle);
    }

    /**
     * @param aTitle
     */
    public Section(String aTitle) {
        this();
        this.title.append(aTitle);
    }

    /**
     * @see java.util.Observable#addObserver(Observer)
     */
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void deployOrHide() {
        if (this.currentState == this.deployedState) {
            this.currentState = this.hiddenState;
        }
        else {
            this.currentState = this.deployedState;
        }
    }

    /**
     * @see model.interfaces.ISection#getCurrentSection()
     */
    @Override
    public ISection getCurrentSection() {
        int index = this.indexOfCurrentSection();
        if (index == -1) {
            return this;
        }
        else {
            return this.getSubSections().get(index).getCurrentSection();
        }
    }

    /**
     * @see model.interfaces.ISection#getNbParents()
     */
    @Override
    public int getNbParents() {
        int nbParents = 0;
        ISection section = this;
        while (section.getParent() != null) {
            section = (ISection) section.getParent();
            nbParents++;
        }
        this.nbParents = nbParents;
        return nbParents;
    }

    /**
     * @see model.interfaces.ISection#getParent()
     */
    @Override
    public IDocument getParent() {
        return this.parent;
    }

    /**
     * @see model.interfaces.ISection#getTitle()
     */
    @Override
    public ILine getTitle() {
        return this.title;
    }

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
     * @see model.interfaces.ISection#isCurrentSection()
     */
    @Override
    public boolean isCurrentSection() {
        return this.isCurrentSection;
    }

    /**
     * @see model.interfaces.ISection#setIsCurrentSection(boolean)
     */
    @Override
    public void setIsCurrentSection(boolean aIsCurrentSection) {
        this.isCurrentSection = aIsCurrentSection;
        if (this.parent instanceof ISection) {
            ((ISection) this.parent).setIsCurrentSection(aIsCurrentSection);
        }
    }

    /**
     * @see model.interfaces.ISection#setNbParents()
     */
    @Override
    public void setNbParents() {
        int nbParents = 0;
        ISection section = this;
        while (section.getParent() != null) {
            section = (ISection) section.getParent();
            nbParents++;
        }
        this.nbParents = nbParents;
    }

    /**
     * @see model.interfaces.ISection#setParent(model.interfaces.ISection)
     */
    @Override
    public void setParent(IDocument aSection) {
        aSection.addSection(this);
    }

    /**
     * @see model.interfaces.ISection#setTitle(model.interfaces.IText)
     */
    @Override
    public void setTitle(ILine aLine) {
        this.title = aLine;
    }

    @Override
    public String toString() {
        return this.currentState.toString();
    }
}
