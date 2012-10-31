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
class Section extends Observable implements ISection {
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
    private IText          introduction     = Factory.createText();
    /**
     * The parent section.
     */
    private ISection       parent           = Factory.createSection();
    /**
     * The current state which is used to print the section. This is used to
     * implement the state pattern.
     * It enables to delegate instead of doing some switch case with ifs.
     */
    private IState         currentState;
    private IState         deployedState;
    private IState         hiddenState;
    /**
     * True if this section is the current section
     */
    private boolean        isCurrentSection = false;

    public Section() {
        hiddenState = StateFactory.createHiddenState(this);
        deployedState = StateFactory.createDeployedState(this);
        currentState = deployedState;
        Cursor.getCursorInstance().setCurrentSection(this);
    }

    /**
     * @see model.interfaces.ISection#addSubSection(model.interfaces.ISection)
     */
    @Override
    public void addSubSection(ISection aSection) {
        aSection.setParent(this);
        this.subSections.add(aSection);
    }

    // TODO: Les deux m�thodes suivantes ne font pas plut�t parti de la vue
    // plutot que du modele ?
    /**
     * @see model.interfaces.ISection#deploySection()
     */
    @Override
    public void deploySection() {
        currentState = deployedState;
    }

    /**
     * @see model.interfaces.ISection#hideSection()
     */
    @Override
    public void hideSection() {
        currentState = hiddenState;
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
     * @see model.interfaces.ISection#getIntroduction()
     */
    @Override
    public IText getIntroduction() {
        return introduction;
    }

    /**
     * @see model.interfaces.ISection#setParent(model.interfaces.ISection)
     */
    @Override
    public void setParent(ISection aSection) {
        // Check when we ahve to add the children to the parent list
        // TODO aSection.addSubSection(this);
        this.parent = aSection;
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
     * @see model.interfaces.ISection#isCurrentSection()
     */
    @Override
    public boolean isCurrentSection() {
        return isCurrentSection;
    }

    /**
     * @see model.interfaces.ISection#setIsCurrentSection(boolean)
     */
    @Override
    public void setIsCurrentSection(boolean aIsCurrentSection) {
        this.isCurrentSection = aIsCurrentSection;
        if (this.parent != null) {
            this.parent.setIsCurrentSection(aIsCurrentSection);
        }
    }

    /**
     * @see model.interfaces.ISection#getCurrentSection()
     */
    @Override
    public ISection getCurrentSection() {
        int subSectionsSize = getSubSections().size();
        if (getSubSections() == null || subSectionsSize == 0)
            return this;
        int currentIndex = 0;
        while (currentIndex < subSectionsSize
                && !getSubSections().get(currentIndex).isCurrentSection())
            currentIndex++;
        if (currentIndex == subSectionsSize)
            return this;
        else
            return getSubSections().get(currentIndex).getCurrentSection();
    }

    /**
     * @see java.util.Observable#addObserver(Observer)
     */
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void accept(ICommandVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return currentState.toString();
    }
}
