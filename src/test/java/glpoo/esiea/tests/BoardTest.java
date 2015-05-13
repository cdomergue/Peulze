package glpoo.esiea.tests;

import glpoo.esiea.peulze.game.Board;
import glpoo.esiea.peulze.game.ObjectIdNotFoundException;
import glpoo.esiea.peulze.game.pieces.*;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BoardTest {
    private static final Logger LOGGER = Logger.getLogger(PieceTest.class);
    private Board boardInstance;
    private List<Piece> pieces;
    private List<Quarter> quarters;

    @Before
    public void before(){
        pieces = PieceDaoImpl.getInstance().readAllPieces();
        quarters = QuarterDaoImpl.getInstance().readAllQuarters();
        boardInstance = new Board(pieces, quarters, 4, 4);
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
        Assert.assertEquals(caseAttendue, laCase1);
        Assert.assertEquals(caseAttendue, laCase2);
        Assert.assertEquals(caseAttendue, laCase3);
    }

    /**
     * On va tester si on a bien 16 pièces dans la main
     */
    @Test
    public void testNbPiecesMain(){
        //Arrange
        final int nbPiecesAttendues = 16;

        //Act
        final int nbPieces = boardInstance.getHandPieces().size();

        //Assert
        Assert.assertEquals(nbPiecesAttendues, nbPieces);
    }

    /**
     * On va tester le placement de pièces
     */
    @Test
    public void testPutPiece() throws ObjectIdNotFoundException {
        //Arrange
        final boolean placementHorsZoneAttendu = false;
        final boolean placementEnZoneAttendu = true;
        final boolean placementDeuxiemePieceMauvaisEndroit1Attendu = false;
        final boolean placementDeuxiemePieceMauvaisEndroit2Attendu = false;
        final boolean placementDeuxiemePieceBonEndroitAttendu = true;
        final boolean placementDeuxiemePieceANouveauAttendu = false;
        PieceImpl piece1 = (PieceImpl) pieces.get(0);
        piece1.setIdEst(2);
        piece1.setIdNord(2);
        piece1.setIdOuest(2);
        piece1.setIdSud(2);
        PieceImpl piece2 = (PieceImpl) pieces.get(4);
        piece2.setIdEst(2);
        piece2.setIdNord(3);
        piece2.setIdOuest(1);
        piece2.setIdSud(4);

        //Act
        final boolean placementHorsZone = boardInstance.putPiece(piece1,6,0);
        final boolean placementEnZone = boardInstance.putPiece(piece1,1,1);
        final boolean placementDeuxiemePieceMauvaisEndroit1 = boardInstance.putPiece(piece2,1,3);
        final boolean placementDeuxiemePieceMauvaisEndroit2 = boardInstance.putPiece(piece2, 1,2);
        final boolean placementDeuxiemePieceBonEndroit = boardInstance.putPiece(piece2,1,0);
        final boolean placementDeuxiemePieceANouveau = boardInstance.putPiece(piece2,3,3);

        //Assert
        Assert.assertEquals(placementHorsZoneAttendu, placementHorsZone);
        Assert.assertEquals(placementEnZoneAttendu, placementEnZone);
        Assert.assertEquals(placementDeuxiemePieceMauvaisEndroit1Attendu, placementDeuxiemePieceMauvaisEndroit1);
        Assert.assertEquals(placementDeuxiemePieceMauvaisEndroit2Attendu, placementDeuxiemePieceMauvaisEndroit2);
        Assert.assertEquals(placementDeuxiemePieceBonEndroitAttendu, placementDeuxiemePieceBonEndroit);
        Assert.assertEquals(placementDeuxiemePieceANouveauAttendu, placementDeuxiemePieceANouveau);
    }

}
