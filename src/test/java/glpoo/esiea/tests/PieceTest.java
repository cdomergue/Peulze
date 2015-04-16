package glpoo.esiea.tests;

import glpoo.esiea.peulze.game.pieces.*;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by christophe on 25/03/15.
 */
public class PieceTest {
    private static final Logger LOGGER = Logger.getLogger(PieceTest.class);

    /**
     * Test la création de pièces
     */

    @Test
    public void createPiece(){
        //Arrange and Act
        Quarter nord = new QuarterImpl(1, QuarterType.BORD, "noir", "", "");
        Quarter sud = new QuarterImpl(2, QuarterType.FACE, "jaune", "triangle", "bleu");
        Quarter est = new QuarterImpl(3, QuarterType.FACE, "bleu", "zigzag", "blanc");
        Quarter ouest = new QuarterImpl(4, QuarterType.FACE, "rouge", "couronne", "jaune");
        Piece piece = new PieceImpl(1, 1, 2, nord, sud, est, ouest);

        //Assert
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
