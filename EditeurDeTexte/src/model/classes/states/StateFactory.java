package model.classes.states;

import model.interfaces.ISection;
import model.interfaces.IState;

public class StateFactory {
    public static IState createDeployedState(ISection section) {
        return new Deployed(section);
    }

    public static IState createHiddenState(ISection section) {
        return new Hidden(section);
    }
}
