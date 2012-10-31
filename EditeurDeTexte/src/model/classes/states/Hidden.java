package model.classes.states;

import model.interfaces.ISection;
import model.interfaces.IState;

class Hidden implements IState {
	
	private ISection linkedSection;
	
	public Hidden(ISection section) {
		linkedSection = section;
	}
	
	@Override
	public String toString() {
		return linkedSection.getTitle().toString() + "...";
	}

}
