package model.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.classes.states.StateFactory;
import model.interfaces.ICommandVisitor;
import model.interfaces.ILine;
import model.interfaces.ISection;
import model.interfaces.IState;
import model.interfaces.IText;

/**
 * 9 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau Ecole des Mines de Nantes Major in Computer and
 *         Information System Engineering Section.java
 */
class Section extends Document implements ISection {
    /**
     * The subsections' list of the current section.
     */
    private List<ISection> subSections      = new ArrayList<ISection>();
    /**
     * The current section title.
     */
    private ILine          title            = Factory.createLine();
    /**
     * The current section text;
     */
    private IText          text             = Factory.createText();
    /**
     * The parent section.
     */
    // The value is null because a section hasn't got necessarily a parent
    private ISection       parent           = null;
    /**
     * The current state which is used to print the section. This is used to
     * implement the state pattern. It enables to delegate instead of doing some
     * switch case with ifs.
     */
    private IState         currentState;
    private IState         deployedState;
    private IState         hiddenState;
    /**
     * True if this section is the current section
     */
    private boolean        isCurrentSection = false;
    /**
     * The number of parents of the section
     */
    private int            nbParents        = 0;

    public Section() {
        hiddenState = StateFactory.createHiddenState(this);
        deployedState = StateFactory.createDeployedState(this);
        currentState = deployedState;
        Cursor.instance().setCurrentSection(this);
    }

    public Section(ISection parent) {
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

    /**
     * @see model.interfaces.ISection#getParent()
     */
    @Override
    public ISection getParent() {
        return this.parent;
    }

    /**
     * @see model.interfaces.ISection#getSubSections()
     */
    @Override
    public ArrayList<ISection> getSubSections() {
        return (ArrayList<ISection>) subSections;
    }

    /**
     * @see model.interfaces.ISection#getTitle()
     */
    @Override
    public ILine getTitle() {
        return title;
    }

    /**
     * @see model.interfaces.ISection#getText()
     */
    @Override
    public IText getText() {
        return text;
    }

    /**
     * @see model.interfaces.ISection#setParent(model.interfaces.ISection)
     */
    @Override
    public void setParent(ISection aSection) {
        aSection.addSection(this);
    }

    /**
     * @see model.interfaces.ISection#getNbParents()
     */
    @Override
    public int getNbParents() {
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
     * @see model.interfaces.ISection#setSubSection(java.util.ArrayList)
     */
    @Override
    public void setSubSection(ArrayList<ISection> aSubSectionsList) {
        this.subSections = aSubSectionsList;
    }

    /**
     * @see model.interfaces.ISection#setIsCurrentSection(boolean)
     */
    @Override
    public void setIsCurrentSection(boolean aIsCurrentSection) {
        this.isCurrentSection = aIsCurrentSection;
        if (this.parent != null)
            this.parent.setIsCurrentSection(aIsCurrentSection);
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
    public void addSection(ISection aSection, int index) {
        subSections.add(index, aSection);
    }

 

    @Override
    public void deploy() {
        currentState = deployedState;
    }

    @Override
    public void hide() {
        currentState = hiddenState;
    }

    /**
     * @see model.interfaces.ISection#setNbParents()
     */
    @Override
    public void setNbParents() {
        int nbParents = 0;
        ISection section = this;
        while (section.getParent() != null) {
            section = section.getParent();
            nbParents++;
        }
        this.nbParents = nbParents;
    }
}
