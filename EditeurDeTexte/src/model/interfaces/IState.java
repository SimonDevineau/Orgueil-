package model.interfaces;

/**
 * This interface is used to implement the State pattern. This pattern is very
 * useful to avoid the if statement. In our case it is useful to avoid the
 * if(section.isDeployed)... else ... <br/>
 * With the state pattern, there is a current state which is, for instance,
 * deployed or hidden and our code is going to delegate some methods to the
 * current state to simplify the code. <br/>
 * @author Pierre Reliquet & Simon Devineau
 */
public interface IState {
    /**
     * @return the string representation of the linked section.
     */
    @Override
    public String toString();
}
