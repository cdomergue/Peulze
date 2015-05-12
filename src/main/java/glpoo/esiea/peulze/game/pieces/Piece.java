package glpoo.esiea.peulze.game.pieces;

import glpoo.esiea.peulze.game.GameObject;

/**
 * Created by Christophe on 03/04/2015.
 */
public interface Piece extends GameObject {

    public void rotateLeft();

    public void rotateRight();

    public int getIdNord();

    public int getIdSud();

    public int getIdEst();

    public int getIdOuest();

}
