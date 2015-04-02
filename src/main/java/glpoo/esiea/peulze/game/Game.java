package glpoo.esiea.peulze.game;

/**
 * Created by Christophe on 02/04/2015.
 */
public class Game {
    private static Game ourInstance = new Game();

    public static Game getInstance() {
        return ourInstance;
    }

    private Game() {
    }
}
