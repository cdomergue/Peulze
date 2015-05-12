package glpoo.esiea.tests;

import glpoo.esiea.peulze.game.Board;
import glpoo.esiea.peulze.game.pieces.*;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BoardTest {
    private static final Logger LOGGER = Logger.getLogger(PieceTest.class);
    private Board boardInstance;

    @Before
    public void before(){
        List<Piece> pieces = PieceDaoImpl.getInstance().readAllPieces();
        List<Quarter> quarters = QuarterDaoImpl.getInstance().readAllQuarters();
        boardInstance = Board.getInstance(pieces,quarters,4,4);
    }

    /**
     * On va tester si le plateau est bien vide (toutes les cases à 0)
     * On en prend quelques une parmis toutes
     */
    @Test
    public void testIfBoardEmpty(){
        //Arrange
        final int caseAttendue = 0;

        //Act
        final int laCase1 = boardInstance.getBoard()[2][3];
        final int laCase2 = boardInstance.getBoard()[0][2];
        final int laCase3 = boardInstance.getBoard()[1][0];

        //Assert
        Assert.assertEquals(laCase1, caseAttendue);
        Assert.assertEquals(laCase2, caseAttendue);
        Assert.assertEquals(laCase3, caseAttendue);
    }

    /**
     * On va tester si on a bien 16 pièces dans la main
     */
    @Test
    public void testNbPiecesMain(){
        //Arrange
        final int nbPiecesAttendues = 16;

        //Act
        final int nbPieces = boardInstance.getPiecesMain().size();

        //Assert
        Assert.assertEquals(nbPiecesAttendues, nbPieces);
    }

}
