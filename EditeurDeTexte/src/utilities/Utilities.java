package utilities;

import java.awt.Image;
import java.awt.Toolkit;

import com.apple.eawt.Application;

import tests.LaunchEditor;
import view.MainForm;

/**
 * 1 nov. 2012 - EditeurDeTexte.
 * @author Simon Devineau
 *         Ecole des Mines de Nantes
 *         Major in Computer and Information System Engineering
 *         Utilities.java
 */
public class Utilities {
    public static void changeMenuBar() {
        if (System.getProperty("os.name").toLowerCase().contains("mac os")) {
            System.setProperty(
                    "com.apple.mrj.application.apple.menu.about.name",
                    MainForm.DEFAULT_TITLE);
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            Application application = Application.getApplication();
            Image imageDock = null;
            try {
                imageDock = Toolkit.getDefaultToolkit().getImage(
                        LaunchEditor.class.getResource("images/notes.png"));
            }
            catch (Exception e) {
            }
            application.setDockIconImage(imageDock);
        }
    }
}
