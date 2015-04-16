package glpoo.esiea.peulze.game.pieces;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
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
            final List<String> lignes = getLignesFromFile();

            final List<Quarter> quarters = new ArrayList<>();
            for (String ligne : lignes) {
                final Quarter quarter = transformLigneToQuarter(ligne);
                quarters.add(quarter);
            }

            return quarters;

        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite...", e);
            return null;
        }
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

    private List<String> getLignesFromFile() throws IOException {

        final List<String> lignes = new ArrayList<>();
        final String file = "src/main/ressources/csv/quarters.csv";
        final FileReader fr = new FileReader(file);
        final BufferedReader br = new BufferedReader(fr);


        for (String ligne = br.readLine(); ligne != null; ligne = br.readLine()) {

            // Suppression des espaces en trop
            ligne = ligne.trim();

            // Filtre des lignes vides
            if (ligne.isEmpty()) {
                continue;
            }

            // Filtre des lignes de commentaire
            if (ligne.startsWith("#")) {
                continue;
            }

            lignes.add(ligne);
        }

        br.close();
        fr.close();
        return lignes;

    }
}
