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
        hiddenState = StateFactory.createHiddenState(this);
        deployedState = StateFactory.createDeployedState(this);
        currentState = deployedState;
        title = Factory.createLine();
        this.isCurrentSection = true;
    }

    public Section(IDocument parent) {
        this();
        setParent(parent);
    }

    /**
     * @param aTitle
     */
    public Section(String aTitle) {
        this();
        title.append(aTitle);
    }

    public Section(IDocument parent, String aTitle) {
        this(parent);
        title.append(aTitle);
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
        return title;
    }

    /**
     * @see model.interfaces.ISection#setParent(model.interfaces.ISection)
     */
    @Override
    public void setParent(IDocument aSection) {
        aSection.addSection(this);
    }

    /**
     * @see model.interfaces.ISection#getNbParents()
     */
    @Override
    public int getNbParents() {
        this.setNbParents();
        return nbParents;
    }

    /**
     * @see model.interfaces.ISection#setTitle(model.interfaces.IText)
     */
    @Override
    public void setTitle(ILine aLine) {
        this.title = aLine;
    }

    /**
     * @see model.interfaces.ISection#setIsCurrentSection(boolean)
     */
    @Override
    public void setIsCurrentSection(boolean aIsCurrentSection) {
        this.isCurrentSection = aIsCurrentSection;
        if (this.parent instanceof ISection)
            ((ISection) this.parent).setIsCurrentSection(aIsCurrentSection);
    }

    /**
     * @see model.interfaces.ISection#getCurrentSection()
     */
    @Override
    public ISection getCurrentSection() {
        int index = indexOfCurrentSection();
        if (index == -1)
            return this;
        else
            return getSubSections().get(index).getCurrentSection();
    }

    /**
     * @see java.util.Observable#addObserver(Observer)
     */
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public String toString() {
        return currentState.toString();
    }

    @Override
    public int indexOfCurrentSection() {
        int subSectionsSize = getSubSections().size();
        if (getSubSections() == null || subSectionsSize == 0)
            return -1;
        int currentIndex = 0;
        while (currentIndex < subSectionsSize
                && !getSubSections().get(currentIndex).isCurrentSection())
            currentIndex++;
        if (currentIndex == subSectionsSize)
            return -1;
        else
            return currentIndex;
    }

    /**
     * @see model.interfaces.ISection#isCurrentSection()
     */
    @Override
    public boolean isCurrentSection() {
        return isCurrentSection;
    }

    @Override
    public void deployOrHide() {
        if (currentState == deployedState)
            currentState = hiddenState;
        else
            currentState = deployedState;
    }

    /**
     * @see model.interfaces.ISection#setNbParents()
     */
    @Override
    public void setNbParents() {
        int nbParents = 0;
        ISection section = this;
        while (!(section.getParent() instanceof IDocument)
                && section.getParent() != null) {
            section = (ISection) section.getParent();
            nbParents++;
        }
        this.nbParents = nbParents;
    }
}
