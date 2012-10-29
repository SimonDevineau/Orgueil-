package model.classes;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import model.interfaces.ICommandVisitor;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * 
 * @author Simon Devineau Ecole des Mines de Nantes Major in Computer and
 *         Information System Engineering CommandExecutor.java
 */
public final class CommandExecutor {
	/**
	 * The single instance of CommandExecutor
	 */
	private static volatile CommandExecutor commandExecutorInstance;
	/**
	 * The map which contains all the commands
	 */
	private Map<String, ICommand> commandsList = new HashMap<String, ICommand>();

	private CommandExecutor() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		commandsList = new HashMap<String, ICommand>();
		loadCommands();
	}
    /**
     * The single instance of CommandExecutor
     */
    private static volatile CommandExecutor commandExecutorInstance;

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
	public Map<String, ICommand> getCommandsList() {
		return commandsList;
	}
    /**
     * The map which contains all the commands
     */
    private Map<String, ICommandVisitor> commandsList = new HashMap<String, ICommandVisitor>();

    private CommandExecutor() {
        this.commandsList = new HashMap<String, ICommandVisitor>();
    }

    /**
     * @return the commandsList
     */
    public Map<String, ICommandVisitor> getCommandsList() {
        return this.commandsList;
    }

    // TODO definir comment est le fichier
    public void init(File aFile) {
    }

	/**
	 * @param commandsList
	 *            the commandsList to set
	 */
	public void setCommandsList(Map<String, ICommand> commandsList) {
		this.commandsList = commandsList;
	}

	// TODO definir comment est le fichier
	public void init(File aFile) {

	}

	/**
	 * This method is used to load all the commands inside the Map. In order to do that we are using reflection 
	 * so the program is extensible and respect the Open-Close principle.
	 */
	private void loadCommands() {
		Map<String, String> tmp = new HashMap<String, String>();
		utilities.ConfigurationLoader.LoadConfigFile(tmp, "Commands.xml",
				utilities.ConfigurationLoader.DEFAULT_CONFIG_TAG,
				utilities.ConfigurationLoader.DEFAULT_CONFIG_KEY,
				utilities.ConfigurationLoader.DEFAULT_CONFIG_VALUE);
		Set<String> keys = tmp.keySet();
		Iterator<String> key = keys.iterator();
		while (key.hasNext()) {
			String current = key.next();
			String fullName = tmp.get(current);
			// TODO Remove this print
			System.out.println(fullName);
			try {
				// The variable to store the class
				Class tmpClass;
				tmpClass = Class.forName(fullName);

				// Now we check if the interface is implemented
				Class[] interfacesImplemented = tmpClass.getInterfaces();
				boolean iCommandImplemented = false;
				for (Class anInterfaceImplemented : interfacesImplemented) {
					if (anInterfaceImplemented.equals(ICommand.class))
						iCommandImplemented = true;
				}

				// if the interface ICommand is implemented we can add it.
				if (iCommandImplemented) {
					commandsList
							.put(current, (ICommand) tmpClass.newInstance());
					((ICommand) tmpClass.newInstance()).execute();
				} else { // we write in the logger that it the interface is not
							// implemented
					utilities.Logger.error(fullName
							+ " does not implement the ICommand");
				}
			} catch (ClassNotFoundException classNotFound) {
				utilities.Logger
						.error("Class " + fullName + " cannot be found",
								classNotFound);
			} catch (InstantiationException instantiationFailed) {
				utilities.Logger.error("Class " + fullName
						+ " cannot be instantiated!", instantiationFailed);
			} catch (IllegalAccessException accessException) {
				utilities.Logger.error("Constructor of class " + fullName
						+ " cannot be accessed!", accessException);
			}

		}
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		new CommandExecutor();
	}
    /**
     * @param commandsList
     *            the commandsList to set
     */
    public void setCommandsList(Map<String, ICommandVisitor> commandsList) {
        this.commandsList = commandsList;
    }
}
