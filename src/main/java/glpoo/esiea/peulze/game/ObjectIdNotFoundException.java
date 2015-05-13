package glpoo.esiea.peulze.game;

import org.apache.log4j.Logger;


public class ObjectIdNotFoundException extends Exception {
    private static final Logger LOGGER = Logger.getLogger(ObjectIdNotFoundException.class);

    public ObjectIdNotFoundException(int value) {
        LOGGER.error("Object id " + value + " not found");
    }
}
