/**
 * 
 */
package model.classes.states;

import model.interfaces.ISection;
import model.interfaces.IState;

/**
 * @author pierre
 *
 */
class Deployed implements IState {
	
	private ISection linkedSection;
	
	public Deployed(ISection section) {
		linkedSection = section;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(linkedSection.getTitle().toString());
		result.append(linkedSection.getText().toString());
		for(ISection subSection : linkedSection.getSubSections())
			result.append(subSection.toString());
		return result.toString();
	}

}
