package glpoo.esiea.peulze.game.pieces;

import glpoo.esiea.peulze.game.GameObject;

/**
 * Created by Christophe on 03/04/2015.
 */
public interface Piece extends GameObject {
    public int getX();

    public int getY();

    public Quarter getNord();

    public Quarter getSud();

    public Quarter getEst();

    public Quarter getOuest();

}
