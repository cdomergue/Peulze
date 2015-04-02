package glpoo.esiea.peulze.game.pieces;

/**
 * Created by Christophe on 02/04/2015.
 */
public abstract class Piece {
    private int x;
    private int y;
    private int orientation;
    private Face face1, face2, face3, face4;

    public Face getFace1() {
        return face1;
    }

    public void setFace1(Face face1) {
        this.face1 = face1;
    }

    public Face getFace2() {
        return face2;
    }

    public void setFace2(Face face2) {
        this.face2 = face2;
    }

    public Face getFace3() {
        return face3;
    }

    public void setFace3(Face face3) {
        this.face3 = face3;
    }

    public Face getFace4() {
        return face4;
    }

    public void setFace4(Face face4) {
        this.face4 = face4;
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

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
