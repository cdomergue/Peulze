package glpoo.esiea.peulze.game.pieces;

import org.apache.log4j.Logger;

/**
 * Created by Christophe on 10/04/2015.
 */
public class TypeNotFoundException extends Exception {
    private static final Logger LOGGER = Logger.getLogger(TypeNotFoundException.class);

    public TypeNotFoundException(String value) {
        LOGGER.error("Type " + value + " not found");
    }
}
