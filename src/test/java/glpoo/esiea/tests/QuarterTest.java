package glpoo.esiea.tests;

import glpoo.esiea.peulze.game.pieces.Quarter;
import glpoo.esiea.peulze.game.pieces.QuarterDao;
import glpoo.esiea.peulze.game.pieces.QuarterDaoImpl;
import glpoo.esiea.peulze.game.pieces.QuarterType;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Christophe on 03/04/2015.
 */
public class QuarterTest {
    private static final Logger LOGGER = Logger.getLogger(QuarterTest.class);
    private QuarterDao quarterDao;

    @Before
    public void before() {
        LOGGER.debug("de before");
        quarterDao = QuarterDaoImpl.getInstance();
        LOGGER.debug("end before");
    }

    @Test
    public void testNbQuarter() {
        LOGGER.debug("do testNbQuarter");
        //Arrange
        final int nbQuartersAttendus = 5;

        //Act
        final List<Quarter> quarters = quarterDao.readAllQuarters();

        //Assert
        Assert.assertEquals(nbQuartersAttendus, quarters.size());
        LOGGER.debug("end testNbQuarter");
    }

    @Test
    public void testPremierElement() {
        LOGGER.debug("do testPremierElement");

        //Arrange
        List<Quarter> quarters = quarterDao.readAllQuarters();
        final String couleurFondAttendu = "noir";
        final QuarterType quarterTypeAttendu = QuarterType.BORD;
        final int idAttendu = 1;
        final String formeAttendu = "";

        //Act
        final String couleurFond = quarters.get(0).getCouleurFond();
        final QuarterType quarterType = quarters.get(0).getType();
        final int id = quarters.get(0).getId();
        final String forme = quarters.get(0).getForme();

        //Assert
        Assert.assertEquals(couleurFondAttendu, couleurFond);
        Assert.assertEquals(quarterTypeAttendu, quarterType);
        Assert.assertEquals(idAttendu, id);
        Assert.assertEquals(formeAttendu, forme);

        LOGGER.debug("end testPremierElement");
    }

    @Test
    public void testTroisièmeElement() {
        LOGGER.debug("do testTroisièmeElement");

        //Arrange
        List<Quarter> quarters = quarterDao.readAllQuarters();
        final String couleurFondAttendu = "bleu";
        final QuarterType quarterTypeAttendu = QuarterType.FACE;
        final int idAttendu = 3;
        final String formeAttendu = "zigzag";

        //Act
        final String couleurFond = quarters.get(2).getCouleurFond();
        final QuarterType quarterType = quarters.get(2).getType();
        final int id = quarters.get(2).getId();
        final String forme = quarters.get(2).getForme();

        //Assert
        Assert.assertEquals(couleurFondAttendu, couleurFond);
        Assert.assertEquals(quarterTypeAttendu, quarterType);
        Assert.assertEquals(idAttendu, id);
        Assert.assertEquals(formeAttendu, forme);

        LOGGER.debug("end testTroisièmeElement");
    }
}
