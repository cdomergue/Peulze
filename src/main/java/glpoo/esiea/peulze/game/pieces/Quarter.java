package glpoo.esiea.peulze.game.pieces;

import glpoo.esiea.peulze.game.GameObject;

/**
 * Created by Christophe on 03/04/2015.
 */
public interface Quarter extends GameObject {

    public QuarterType getType();

    public String getCouleurFond();

    public String getForme();

    public String getCouleurForme();
}
