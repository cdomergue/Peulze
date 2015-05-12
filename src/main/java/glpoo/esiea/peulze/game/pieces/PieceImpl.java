package glpoo.esiea.peulze.game.pieces;

/**
 * Created by Christophe on 02/04/2015.
 */
public class PieceImpl implements Piece {
    private int id;
    private int idNord, idSud, idEst, idOuest;

    public PieceImpl(int id, int nord, int sud, int est, int ouest) {
        this.idNord = nord;
        this.idSud = sud;
        this.idEst = est;
        this.idOuest = ouest;
        this.id = id;
    }


    @Override
    public void rotateLeft() {
        int tmpNord, tmpSud, tmpEst, tmpOuest;
        tmpNord = idEst;
        tmpEst = idSud;
        tmpSud = idOuest;
        tmpOuest = idNord;
        idNord = tmpNord;
        idEst = tmpEst;
        idSud = tmpSud;
        idOuest = tmpOuest;
    }

    @Override
    public void rotateRight() {
        int tmpNord, tmpSud, tmpEst, tmpOuest;
        tmpNord = idOuest;
        tmpEst = idNord;
        tmpSud = idEst;
        tmpOuest = idSud;
        idNord = tmpNord;
        idEst = tmpEst;
        idSud = tmpSud;
        idOuest = tmpOuest;
    }

    public int getIdNord() {
        return idNord;
    }

    public void setIdNord(int idNord) {
        this.idNord = idNord;
    }

    public int getIdSud() {
        return idSud;
    }

    public void setIdSud(int idSud) {
        this.idSud = idSud;
    }

    public int getIdEst() {
        return idEst;
    }

    public void setIdEst(int idEst) {
        this.idEst = idEst;
    }

    public int getIdOuest() {
        return idOuest;
    }

    public void setIdOuest(int idOuest) {
        this.idOuest = idOuest;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
