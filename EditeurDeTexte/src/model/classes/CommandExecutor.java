package model.classes;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import model.interfaces.ICommandVisitor;

/**
 * 22 oct. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         CommandExecutor.java
 */
public final class CommandExecutor {
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
    public void setCommandsList(Map<String, ICommandVisitor> commandsList) {
        this.commandsList = commandsList;
    }
}
