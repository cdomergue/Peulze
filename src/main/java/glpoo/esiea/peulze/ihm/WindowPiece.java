package glpoo.esiea.peulze.ihm;

import org.newdawn.slick.Image;

public class WindowPiece {

    private float x, y, handx, handy;
    private int id;
    private Image north, east, south, west;
    private boolean inBoard;

    public WindowPiece(float x, float y, int id, Image north, Image east, Image south, Image west) {
        this.x = x;
        this.y = y;
        this.handx = x;
        this.handy = y;
        this.id = id;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.inBoard = false;
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getNorth() {
        return north;
    }

    public void setNorth(Image north) {
        this.north = north;
    }

    public Image getEast() {
        return east;
    }

    public void setEast(Image east) {
        this.east = east;
    }

    public Image getSouth() {
        return south;
    }

    public void setSouth(Image south) {
        this.south = south;
    }

    public Image getWest() {
        return west;
    }

    public void setWest(Image west) {
        this.west = west;
    }

    public float getHandx() {
        return handx;
    }

    public void setHandx(float handx) {
        this.handx = handx;
    }

    public float getHandy() {
        return handy;
    }

    public void setHandy(float handy) {
        this.handy = handy;
    }

    public boolean isInBoard() {
        return inBoard;
    }

    public void setInBoard(boolean inBoard) {
        this.inBoard = inBoard;
    }
}
