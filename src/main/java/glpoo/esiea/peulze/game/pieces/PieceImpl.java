package glpoo.esiea.peulze.game.pieces;

/**
 * Created by Christophe on 02/04/2015.
 */
public class PieceImpl implements Piece {
    private int x;
    private int y;
    private int id;
    private Quarter nord, sud, est, ouest;

    public PieceImpl(int id, int x, int y, Quarter nord, Quarter sud, Quarter est, Quarter ouest) {
        this.x = x;
        this.y = y;
        this.nord = nord;
        this.sud = sud;
        this.est = est;
        this.ouest = ouest;
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Quarter getNord() {
        return nord;
    }

    public void setNord(Quarter nord) {
        this.nord = nord;
    }

    public Quarter getSud() {
        return sud;
    }

    public void setSud(Quarter sud) {
        this.sud = sud;
    }

    public Quarter getEst() {
        return est;
    }

    public void setEst(Quarter est) {
        this.est = est;
    }

    public Quarter getOuest() {
        return ouest;
    }

    public void setOuest(Quarter ouest) {
        this.ouest = ouest;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
