package glpoo.esiea.tests;

import glpoo.esiea.peulze.game.Game;
import glpoo.esiea.peulze.game.pieces.BasicPiece;
import glpoo.esiea.peulze.game.pieces.PieceFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by christophe on 25/03/15.
 */
public class GameTest {

    private Game game;
    private PieceFactory pieceFactory;

    @Before
    public void before(){
        game = Game.getInstance();
        pieceFactory = PieceFactory.getInstance();
    }

    /**
     * Test la PieceFactory et les pièces
     */
    @Test
    public void createPiece(){
        BasicPiece basicPiece = (BasicPiece) pieceFactory.createPiece("Basic",1,2,3);
        assertNotNull(basicPiece);
        assertEquals(1, basicPiece.getX());
        assertEquals(2, basicPiece.getY());
        assertEquals(3, basicPiece.getOrientation());
    }
}
