package tests;

import utilities.Utilities;
import controllers.Controller;
import controllers.FactoryController;

public class TestEditor {
    public static final int SLEEP_TIME     = 2000;
    public static final int SLEEP_TIME_FOR = 100;

    @SuppressWarnings("static-access")
    public static void invoke(Controller c, String text, int sleepTime)
            throws InterruptedException {
        c.getInputTextManager().manageInputText(text);
        Thread.currentThread().sleep(sleepTime);
    }

    /**
     * This method is used to test the editor
     * @param args
     * @throws InterruptedException
     */
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws InterruptedException {
        Utilities.changeMenuBar();
        Controller c = FactoryController.createController();
        Thread.currentThread().sleep(SLEEP_TIME);
        invoke(c, "Bonjour, voici le script de test! ", SLEEP_TIME);
        invoke(c, " Un petit append pour prouver l'ajout de texte!", SLEEP_TIME);
        invoke(c, "\\* Test d'ajout d'une section", SLEEP_TIME);
        invoke(c,
                "\\* Test d'ajout d'une autre section (que nous allons cacher et déployer)!",
                SLEEP_TIME);
        invoke(c, "\\p", SLEEP_TIME);
        invoke(c, "\\p", SLEEP_TIME);
        for (int i = 0; i < 10; i++) {
            invoke(c, "\\o", SLEEP_TIME_FOR);
        }
        invoke(c, "\\n", SLEEP_TIME);
        invoke(c, " ----Une insertion après avoir remonté le curseur!---- ",
                SLEEP_TIME);
        for (int i = 0; i < 70; i++) {
            invoke(c, "\\o", SLEEP_TIME_FOR);
        }
        invoke(c, "\\s", SLEEP_TIME);
        invoke(c,
                " ---- Une insertion après avoir descendu le curseur!---- ",
                SLEEP_TIME);
        invoke(c, "\\* Un copier/coller", SLEEP_TIME);
        invoke(c, "\\w", SLEEP_TIME);
        invoke(c, "\\y", SLEEP_TIME);
        invoke(c, "\\* La démonstration va maintenant se quitter!",
                2 * SLEEP_TIME);
        System.exit(0);
    }
}
