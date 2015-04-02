package glpoo.esiea.peulze.game.pieces;

/**
 * Created by Christophe on 02/04/2015.
 */
public class PieceFactory {
    private static PieceFactory ourInstance = new PieceFactory();

    public static PieceFactory getInstance() {
        return ourInstance;
    }

    private PieceFactory() {
    }
}
