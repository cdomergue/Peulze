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
        boardInstance = new Board(pieces, quarters, 4, 4, true);
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

    /**
     * On va tester de retirer une pièce
     */
    @Test
    public void testTakePiece() throws ObjectIdNotFoundException {
        //Arrange
        final boolean testHorsLimiteAttendu = false;
        final boolean testCaseVideAttendu = false;
        final boolean testBonneCaseAttendu = true;
        final boolean testCasePrecedenteANouveauAttendu = false;
        PieceImpl piece = (PieceImpl) pieces.get(7);
        boardInstance.putPiece(piece,1,1);

        //Act
        final boolean testHorsLimite = boardInstance.takePiece(6,2);
        final boolean testCaseVide = boardInstance.takePiece(2,2);
        final boolean testBonneCase = boardInstance.takePiece(1,1);
        final boolean testCasePrecedenteANouveau = boardInstance.takePiece(1,1);

        //Assert
        Assert.assertEquals(testHorsLimiteAttendu, testHorsLimite);
        Assert.assertEquals(testCaseVideAttendu, testCaseVide);
        Assert.assertEquals(testBonneCaseAttendu, testBonneCase);
        Assert.assertEquals(testCasePrecedenteANouveauAttendu, testCasePrecedenteANouveau);

    }

    /**
     * On va tester si on peut gagner sur un puzzle 2*2
     * On va créer nos propres pièces
     */
    @Test
    public void testWin() throws ObjectIdNotFoundException {
        //Arrange
        pieces.clear();
        pieces.add(new PieceImpl(1, 1, 3, 2, 1));
        pieces.add(new PieceImpl(2, 1, 3, 1, 2));
        pieces.add(new PieceImpl(3, 3, 1, 4, 1));
        pieces.add(new PieceImpl(4, 3, 1, 1, 4));
        boardInstance = new Board(pieces, quarters, 2, 2, false);
        final boolean premierTestVictoireFauxAttendu = false;
        final boolean deuxiemeTestVictoireVraiAttendu = true;

        //Act
        boardInstance.putPiece(pieces.get(0), 0, 0);
        boardInstance.putPiece(pieces.get(1), 0, 1);
        boardInstance.putPiece(pieces.get(2), 1, 0);
        final boolean premierTestVictoireFaux = boardInstance.checkWin();
        Piece piece = pieces.get(3);
        piece.rotateLeft();
        piece.rotateRight();
        boardInstance.putPiece(piece, 1, 1);
        final boolean deuxiemeTestVictoireVrai = boardInstance.checkWin();

        //Assert
        Assert.assertEquals(premierTestVictoireFauxAttendu, premierTestVictoireFaux);
        Assert.assertEquals(deuxiemeTestVictoireVraiAttendu, deuxiemeTestVictoireVrai);

    }

    /**
     * On va tester la sauvegarde et le chargement
     */
    @Test
    public void testSauvegarde(){
        //Arrange
        final Integer comparateurTailleMain = boardInstance.getHandPieces().size();
        final Piece comparateurPieceN2 = pieces.get(2);

        //Act
        boardInstance.save();
        boardInstance = Board.load();
        final Integer comparéTailleMain = boardInstance.getHandPieces().size();
        final Piece comparéPieceN2 = boardInstance.getPieces().get(2);

        //Assert
        Assert.assertEquals(comparateurTailleMain, comparéTailleMain);
        Assert.assertEquals(comparateurPieceN2.getId(), comparéPieceN2.getId());
    }



}
