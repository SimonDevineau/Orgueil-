package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The logger class provides a logger which generates an html log where the
 * error, debug, warn and info message are displayed in different colours.
 * @author Pierre Reliquet
 */
public class Logger {
    /**
     * The line separator which is calculated in function of the OS
     */
    public static final String  LINE_SEPARATOR = System.getProperty("line.separator");
    /**
     * The footer to close the html format.
     */
    private static final String FOOTER         = "</ol>" + LINE_SEPARATOR
                                                       + "</body>"
                                                       + LINE_SEPARATOR
                                                       + "</html>";
    /**
     * The html starting item for the ordered list, needs to be formatted with
     * the specific colour to use inside the item.
     */
    private static final String STARTING_ITEM  = "<li style=\"color:%s\">";
    /**
     * The html ending item
     */
    private static final String ENDING_ITEM    = "</li>";
    /**
     * The new line tag in html
     */
    private static final String NEW_LINE       = "<br />";
    /**
     * the error colour is red by default and can be changed by the user by
     * affecting another String.
     */
    public static String        ERROR_COLOUR   = "red";
    /**
     * the warning colour is red by default and can be changed by the user by
     * affecting another String.
     */
    public static String        WARNING_COLOUR = "orange";
    /**
     * the debug colour is red by default and can be changed by the user by
     * affecting another String.
     */
    public static String        DEBUG_COLOUR   = "green";
    /**
     * the info colour is red by default and can be changed by the user by
     * affecting another String.
     */
    public static String        INFO_COLOUR    = "black";
    /**
     * The instance to respect the singleton pattern.
     */
    private static Logger       _Instance      = new Logger();

    /**
     * Write the debug message inside the log file
     * @param debugMessage
     *            , the debug message to write.
     */
    public static void debug(String debugMessage) {
        write(DEBUG_COLOUR, debugMessage);
    }

    /**
     * Write the debug message inside the log file and the stack trace
     * @param debugMessage
     *            , the debug message to write
     * @param anException
     *            , the associated exception
     */
    public static void debug(String debugMessage, Exception anException) {
        write(DEBUG_COLOUR, debugMessage, anException);
    }

    /**
     * Write the error message inside the log file
     * @param errorMessage
     *            , the error message to write
     */
    public static void error(String errorMessage) {
        write(ERROR_COLOUR, errorMessage);
    }

    /**
     * Write the error message inside the file
     * @param errorMessage
     *            , the String-based error message
     * @param anException
     *            , the associated exception
     */
    public static void error(String errorMessage, Exception anException) {
        write(ERROR_COLOUR, errorMessage, anException);
    }

    /**
     * @param colour
     *            , the colour for the message
     * @return the description (ERROR: | INFO: ...)
     */
    private static String getDescription(String colour) {
        if (colour.equals(ERROR_COLOUR)) {
            return "ERROR: ";
        }
        else if (colour.equals(INFO_COLOUR)) {
            return "INFO: ";
        }
        else if (colour.equals(WARNING_COLOUR)) {
            return "WARNING: ";
        }
        else {
            return "DEBUG: ";
        }
    }

    /**
     * @return the access to the singleton
     */
    public static Logger getInstance() {
        return _Instance;
    }

    /**
     * Write the info message inside the log file.
     * @param infoMessage
     *            , the info message to write
     */
    public static void info(String infoMessage) {
        write(INFO_COLOUR, infoMessage);
    }

    /**
     * Write the info message inside the log file and the stack trace
     * @param infoMessage
     *            , the info message to write
     * @param anException
     *            , the associated exception
     */
    public static void info(String infoMessage, Exception anException) {
        write(INFO_COLOUR, infoMessage, anException);
    }

    /**
     * Write the warning message inside the log file
     * @param warnMessage
     *            , the warning message to write
     */
    public static void warn(String warnMessage) {
        write(WARNING_COLOUR, warnMessage);
    }

    /**
     * Write the warning message and the stack trace inside the log file
     * @param warnMessage
     *            , the warning message to write
     * @param anException
     *            , the associated exception.
     */
    public static void warn(String warnMessage, Exception anException) {
        write(WARNING_COLOUR, warnMessage, anException);
    }

    /**
     * Write the message to the logger
     * @param colour
     *            , the colour
     * @param message
     *            , the message
     */
    private static void write(String colour, String message) {
        if (getInstance()._Logger != null) {
            try {
                getInstance()._Logger.write(String
                        .format(STARTING_ITEM, colour));
                getInstance()._Logger.write(getDescription(colour) + message);
                getInstance()._Logger.write(ENDING_ITEM);
                getInstance()._Logger.flush();
            }
            catch (IOException e) {
                System.err.println("An exception occured with the Logger: "
                        + LINE_SEPARATOR);
                e.printStackTrace();
            }
        }
        else {
            writeConsole(colour, message);
        }
    }

    /**
     * Write the message inside the logger.
     * @param colour
     *            , the colour of the message
     * @param message
     *            , the message
     * @param anException
     *            , the linked exception
     */
    private static void write(String colour, String message,
            Exception anException) {
        if (getInstance()._Logger != null) {
            try {
                getInstance()._Logger.write(String
                        .format(STARTING_ITEM, colour));
                getInstance()._Logger.write(getDescription(colour)
                        + message
                        + NEW_LINE
                        + getInstance().getStringFromStackTrace(
                                anException.getStackTrace()));
                getInstance()._Logger.write(ENDING_ITEM);
                getInstance()._Logger.flush();
            }
            catch (IOException e) {
                System.err.println("An exception occured with the Logger: "
                        + LINE_SEPARATOR);
                e.printStackTrace();
            }
        }
        else {
            writeConsole(colour, message, anException);
        }
    }

    /**
     * write the error message on the console
     * @param colour
     *            , the colour
     * @param message
     *            , the message
     */
    private static void writeConsole(String colour, String message) {
        System.err.println(getDescription(colour) + message);
    }

    /**
     * write the error message on the console
     * @param colour
     *            , the colour
     * @param message
     *            , the message
     * @param anException
     *            , the linked exception
     */
    private static void writeConsole(String colour, String message,
            Exception anException) {
        write(colour, message);
        System.err.println(getInstance().getStringFromStackTrace(
                anException.getStackTrace()));
    }

    /**
     * The filewriter used to create the html file.
     */
    private FileWriter _Logger   = null;
    /**
     * The filename for the html file.
     */
    private String     _FileName = "";

    /**
     * Default private constructor to override the standard public one.
     */
    private Logger() {
        File logFolder = new File("Logs");
        if (!logFolder.exists()) {
            logFolder.mkdir();
        }
        DateFormat formatter = new SimpleDateFormat("ddMMyyyy-HHmmss");
        this._FileName = "Logs" + File.separator + "Log_"
                + formatter.format(new Date()) + ".html";
        try {
            this._Logger = new FileWriter(this._FileName, true);
            this._Logger.write(this.getHeader());
            this._Logger.flush();
        }
        catch (IOException e) {
            System.err
                    .println("An error occured with the logger initialisation, the log is going to be displayed on the Console : "
                            + LINE_SEPARATOR);
            e.printStackTrace();
        }
        this.addHook();
    }

    /**
     * The method to add the shutdown hook to ensure that the html file is going
     * to be well formatted.
     */
    private void addHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    Logger.this._Logger.write(FOOTER);
                    Logger.this._Logger.flush();
                    Logger.this._Logger.close();
                }
                catch (IOException e) {
                    System.err
                            .println("An error occured while trying to close the log file. "
                                    + "The footer:"
                                    + LINE_SEPARATOR
                                    + FOOTER
                                    + LINE_SEPARATOR + " might be missing.");
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * @return the header for the html file.
     */
    private String getHeader() {
        String header = "<!DOCTYPE HTML>" + LINE_SEPARATOR + "<html>"
                + LINE_SEPARATOR + "<head>" + LINE_SEPARATOR + "<title>"
                + this._FileName + "</title>" + LINE_SEPARATOR + "</head>"
                + LINE_SEPARATOR + "<body>" + LINE_SEPARATOR + "<ol>"
                + LINE_SEPARATOR;
        return header;
    }

    /**
     * Transform an array made of StackTraceElement into a string to be written
     * @param stackTrace
     *            , the StackTraceElement array
     * @return a string representation
     */
    public String getStringFromStackTrace(StackTraceElement[] stackTrace) {
        StringBuilder builder = new StringBuilder();
        for (StackTraceElement e : stackTrace) {
            builder.append(e.toString());
        }
        return builder.toString();
    }
}