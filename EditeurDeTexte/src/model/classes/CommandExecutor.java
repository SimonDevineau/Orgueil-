package model.classes;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import model.interfaces.ICommandVisitor;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau Ecole des Mines de Nantes Major in Computer and
 *         Information System Engineering CommandExecutor.java
 */
public final class CommandExecutor {
    /**
     * The single instance of CommandExecutor
     */
    private static volatile CommandExecutor commandExecutorInstance = new CommandExecutor();
    /**
     * The map which contains all the commands
     */
    private Map<String, ICommandVisitor>    commandsList            = new HashMap<String, ICommandVisitor>();

    private CommandExecutor() {
        commandsList = new HashMap<String, ICommandVisitor>();
        loadCommands();
    }

    /**
     * @return the commandExecutorInstance
     */
    public static CommandExecutor getCommandExecutorInstance() {
        return commandExecutorInstance;
    }

    /**
     * @param commandExecutorInstance
     *            the commandExecutorInstance to set
     */
    public static void setCommandExecutorInstance(
            CommandExecutor commandExecutorInstance) {
        CommandExecutor.commandExecutorInstance = commandExecutorInstance;
    }

    /**
     * @return the commandsList
     */
    public Map<String, ICommandVisitor> getCommandsList() {
        return this.commandsList;
    }

    /**
     * @param commandsList
     *            the commandsList to set
     */
    public void setCommandsList(Map<String, ICommandVisitor> commandsList) {
        this.commandsList = commandsList;
    }

    /**
     * This method is used to load all the commands inside the Map. In order to
     * do that we are using reflection
     * so the program is extensible and respect the Open-Close principle.
     */
    private void loadCommands() {
        Map<String, String> tmp = new HashMap<String, String>();
        utilities.ConfigurationLoader.LoadConfigFile(tmp, "Commands.xml",
                utilities.ConfigurationLoader.DEFAULT_CONFIG_TAG,
                utilities.ConfigurationLoader.DEFAULT_CONFIG_KEY,
                utilities.ConfigurationLoader.DEFAULT_CONFIG_VALUE);
        System.out.println("le set dans command EX" + tmp);
        Set<String> keys = tmp.keySet();
        Iterator<String> key = keys.iterator();
        while (key.hasNext()) {
            String current = key.next();
            String fullName = tmp.get(current);
            try {
                // The variable to store the class
                Class tmpClass;
                tmpClass = Class.forName(fullName);
                // Now we check if the interface is implemented
                Class[] interfacesImplemented = tmpClass.getInterfaces();
                boolean iCommandImplemented = false;
                for (Class anInterfaceImplemented : interfacesImplemented) {
                    if (anInterfaceImplemented.equals(ICommandVisitor.class))
                        iCommandImplemented = true;
                }
                // if the interface ICommand is implemented we can add it.
                if (iCommandImplemented) {
                    commandsList.put(current,
                            (ICommandVisitor) tmpClass.newInstance());
                    // ((ICommandVisitor) tmpClass.newInstance()).execute();
                }
                else { // we write in the logger that it the interface is not
                       // implemented
                    utilities.Logger.error(fullName
                            + " does not implement the ICommand");
                }
            }
            catch (ClassNotFoundException classNotFound) {
                utilities.Logger
                        .error("Class " + fullName + " cannot be found",
                                classNotFound);
            }
            catch (InstantiationException instantiationFailed) {
                utilities.Logger.error("Class " + fullName
                        + " cannot be instantiated!", instantiationFailed);
            }
            catch (IllegalAccessException accessException) {
                utilities.Logger.error("Constructor of class " + fullName
                        + " cannot be accessed!", accessException);
            }
        }
    }
}
