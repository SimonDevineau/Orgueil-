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
        linkedSection = section;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int nbParents = linkedSection.getNbParents();
        for (int i = 0; i <= nbParents; i++) {
            result.append("*");
        }
        result.append(linkedSection.getTitle().toString());
        result.append(linkedSection.getText().toString());
        // Work on the subsections
        int nbchildren = linkedSection.getSubSections().size();
        if (nbchildren > 0) {
            for (int i = 0; i < nbchildren; i++) {
                result.append(linkedSection.getSubSections().get(i).toString());
                result.append("</br>");
            }
        }
        result.append("</br>");
        for (ISection subSection : linkedSection.getSubSections())
            result.append(subSection.toString());
        return result.toString();
    }
}
