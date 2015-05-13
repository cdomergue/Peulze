package glpoo.esiea.peulze.game.pieces;

import glpoo.esiea.peulze.tools.Dao;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class PieceDaoImpl implements PieceDao {
    private static final Logger LOGGER = Logger.getLogger(PieceDaoImpl.class);
    private static PieceDaoImpl pieceDaoImpl;

    private PieceDaoImpl() {

    }

    public static final PieceDaoImpl getInstance() {
        if (pieceDaoImpl == null)
            pieceDaoImpl = new PieceDaoImpl();
        return pieceDaoImpl;
    }

    @Override
    public List<Piece> readAllPieces() {
        LOGGER.debug("Chargement des pièces");

        try {
            final List<String> lignes = Dao.getLignesFromFile("src/main/ressources/csv/pieces.csv");

            final List<Piece> pieces = new ArrayList<>();
            for (String ligne : lignes) {
                final Piece piece = transformLigneToPiece(ligne);
                pieces.add(piece);
            }

            return pieces;

        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite...", e);
            return null;
        }
    }

    private Piece transformLigneToPiece(String ligne) throws TypeNotFoundException {
        final String separator = " ";
        final String[] values = ligne.split(separator);
        final int idPiece, idQuarterNord, idQuarterEst, idQuarterSud, idQuarterOuest;

        idPiece = new Integer(values[1]);
        idQuarterNord = new Integer(values[2]);
        idQuarterEst = new Integer(values[3]);
        idQuarterSud = new Integer(values[4]);
        idQuarterOuest = new Integer(values[5]);


        return new PieceImpl(idPiece, idQuarterNord, idQuarterSud, idQuarterEst, idQuarterOuest);
    }
}
