package glpoo.esiea.peulze.game.pieces;

import glpoo.esiea.peulze.game.TheGame;
import glpoo.esiea.peulze.tools.Dao;
import org.apache.log4j.Logger;
import org.lwjgl.Sys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class QuarterDaoImpl implements QuarterDao {
    private static final Logger LOGGER = Logger.getLogger(QuarterDaoImpl.class);
    private static QuarterDaoImpl quarterDaoImpl;

    private QuarterDaoImpl() {

    }

    public static final QuarterDaoImpl getInstance() {
        if (quarterDaoImpl == null)
            quarterDaoImpl = new QuarterDaoImpl();
        return quarterDaoImpl;
    }

    @Override
    public List<Quarter> readAllQuarters() {
        LOGGER.debug("Chargement des quarters");

        try {
            List<String> lignes = Dao.getLignesFromFile("csv/quarters.csv");


            final List<Quarter> quarters = new ArrayList<>();
            for (String ligne : lignes) {
                final Quarter quarter = transformLigneToQuarter(ligne);
                quarters.add(quarter);
            }

            return quarters;

        } catch (Exception e) {
            e.printStackTrace();

        }
        System.exit(1);
        return null;
    }

    private Quarter transformLigneToQuarter(String ligne) throws TypeNotFoundException {
        final String separator = " ";
        final String[] values = ligne.split(separator);
        final int id;
        final QuarterType type;
        final String couleurFond, forme, couleurForme;

        type = QuarterType.getTypeFromString(values[0]);
        id = new Integer(values[1]);
        couleurFond = values[2];
        if (values.length > 3) {
            forme = values[3];
            couleurForme = values[4];
        } else {
            forme = "";
            couleurForme = "";
        }

        return new QuarterImpl(id, type, couleurFond, forme, couleurForme);
    }


}
