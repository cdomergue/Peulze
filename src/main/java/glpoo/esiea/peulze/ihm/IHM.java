package glpoo.esiea.peulze.game.ihm;

/**
 * Created by Christophe on 02/04/2015.
 */
public class IHM {
    private static IHM ourInstance = new IHM();

    private IHM() {
    }

    public static IHM getInstance() {
        return ourInstance;
    }
}
