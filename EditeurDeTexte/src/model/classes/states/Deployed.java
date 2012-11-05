/**
 * 
 */
package model.classes.states;

import model.interfaces.ISection;
import model.interfaces.IState;

/**
 * @author pierre
 */
class Deployed implements IState {
    private ISection linkedSection;

    public Deployed(ISection section) {
        this.linkedSection = section;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int nbParents = this.linkedSection.getNbParents();
        for (int i = 0; i <= nbParents; i++) {
            result.append("*");
        }
        result.append(this.linkedSection.getTitle().toString());
        result.append(this.linkedSection.getText().toString());
        // Work on the subsections
        for (ISection subSection : linkedSection.getSubSections()) {
            result.append("&nbsp;<br/>");
            result.append(subSection.toString());
        }
        return result.toString();
    }
}
