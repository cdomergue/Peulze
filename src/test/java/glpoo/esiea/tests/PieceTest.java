package glpoo.esiea.tests;

import glpoo.esiea.peulze.game.pieces.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class PieceTest {
    private static final Logger LOGGER = Logger.getLogger(PieceTest.class);
    private PieceDao pieceDao;

    @Before
    public void before() {
        LOGGER.debug("do before");
        pieceDao = PieceDaoImpl.getInstance();
        LOGGER.debug("end before");
    }

    @Test
    public void testNbPieces() {
        LOGGER.debug("do testNbPieces");
        //Arrange
        final int nbPiecesAttendues = 16;

        //Act
        final List<Piece> pieces = pieceDao.readAllPieces();

        //Assert
        Assert.assertEquals(nbPiecesAttendues, pieces.size());
    }

    @Test
    public void testPremierElement() {
        LOGGER.debug("do testPremierElement");

        //Arrange
        List<Piece> quarters = pieceDao.readAllPieces();
        final int idPieceAttendu = 1;
        final int idFormeNordAttendu = 1;
        final int idFormeEstAttendu = 5;
        final int idFormeSudAttendu = 4;
        final int idFormeOuestAttendu = 1;


        //Act
        final int idPiece = quarters.get(0).getId();
        final int idFormeNord = quarters.get(0).getIdNord();
        final int idFormeEst = quarters.get(0).getIdEst();
        final int idFormeSud = quarters.get(0).getIdSud();
        final int idFormeOuest = quarters.get(0).getIdOuest();


        //Assert
        junit.framework.Assert.assertEquals(idPieceAttendu, idPiece);
        junit.framework.Assert.assertEquals(idFormeNord, idFormeNordAttendu);
        junit.framework.Assert.assertEquals(idFormeEst, idFormeEstAttendu);
        junit.framework.Assert.assertEquals(idFormeSud, idFormeSudAttendu);
        junit.framework.Assert.assertEquals(idFormeOuest, idFormeOuestAttendu);

        LOGGER.debug("end testPremierElement");
    }

    @Test
    public void testOnzièmeElement() {
        LOGGER.debug("do testOnzièmeElement");

        //Arrange
        List<Piece> quarters = pieceDao.readAllPieces();
        final int idPieceAttendu = 11;
        final int idFormeNordAttendu = 3;
        final int idFormeEstAttendu = 3;
        final int idFormeSudAttendu = 5;
        final int idFormeOuestAttendu = 2;


        //Act
        final int idPiece = quarters.get(10).getId();
        final int idFormeNord = quarters.get(10).getIdNord();
        final int idFormeEst = quarters.get(10).getIdEst();
        final int idFormeSud = quarters.get(10).getIdSud();
        final int idFormeOuest = quarters.get(10).getIdOuest();


        //Assert
        junit.framework.Assert.assertEquals(idPieceAttendu, idPiece);
        junit.framework.Assert.assertEquals(idFormeNord, idFormeNordAttendu);
        junit.framework.Assert.assertEquals(idFormeEst, idFormeEstAttendu);
        junit.framework.Assert.assertEquals(idFormeSud, idFormeSudAttendu);
        junit.framework.Assert.assertEquals(idFormeOuest, idFormeOuestAttendu);

        LOGGER.debug("end testOnzièmeElement");
    }




}
