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
        result.append("<br/>");
        for (int i = 0; i <= nbParents; i++) {
            result.append("*");
        }
        result.append(this.linkedSection.getTitle().toString());
        result.append(this.linkedSection.getText().toString());
        // Work on the subsections
        int nbchildren = this.linkedSection.getSubSections().size();
        if (nbchildren > 0) {
            for (int i = 0; i < nbchildren; i++) {
                result.append(this.linkedSection.getSubSections().get(i)
                        .toString());
            }
        }
        /*
         * for (ISection subSection : linkedSection.getSubSections()) {
         * result.append("&nbsp;<br/>");
         * result.append(subSection.toString());
         * }
         */
        return result.toString();
    }
}
