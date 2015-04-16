package glpoo.esiea.peulze.game.pieces;

/**
 * Created by Christophe on 03/04/2015.
 */
public enum QuarterType {
    BORD, FACE;

    public static QuarterType getTypeFromString(String value) throws TypeNotFoundException {
        switch (value) {
            case "B":
                return QuarterType.BORD;
            case "F":
                return QuarterType.FACE;
            default:
                throw new TypeNotFoundException(value);
        }
    }
}
