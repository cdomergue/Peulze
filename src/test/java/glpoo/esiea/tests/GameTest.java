package glpoo.esiea.tests;

import glpoo.esiea.peulze.game.pieces.Piece;
import glpoo.esiea.peulze.game.pieces.Quarter;
import glpoo.esiea.peulze.game.pieces.QuarterType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by christophe on 25/03/15.
 */
public class GameTest {

    /**
     * Test la création de pièces
     */
    @Test
    public void createPiece(){
        Quarter nord = new Quarter(1, QuarterType.BORD, "noir", "", "");
        Quarter sud = new Quarter(2, QuarterType.FACE, "jaune", "triangle", "bleu");
        Quarter est = new Quarter(3, QuarterType.FACE, "bleu", "zigzag", "blanc");
        Quarter ouest = new Quarter(4, QuarterType.FACE, "rouge", "couronne", "jaune");
        Piece piece = new Piece(1, 1, 2, nord, sud, est, ouest);
        assertNotNull(piece);
        assertEquals(1, piece.getId());
        assertEquals(1, piece.getX());
        assertEquals(2, piece.getY());
        assertEquals(nord, piece.getNord());
        assertEquals(sud, piece.getSud());
        assertEquals(est, piece.getEst());
        assertEquals(ouest, piece.getOuest());
    }
}
