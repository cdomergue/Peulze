package glpoo.esiea.peulze.ihm;

import glpoo.esiea.peulze.game.ObjectIdNotFoundException;
import glpoo.esiea.peulze.game.TheGame;

/**
 * Created by Christophe on 14/05/2015.
 */
public class ImageQuarterFactory {

    /**
     * Récupère l'image en fonction de l'id du quarter et son orientation
     *
     * @param quarterId   id du quarter
     * @param orientation N = nord, E = Est, S = Sud, W = Ouest
     * @return
     */
    public static String getImage(int quarterId, String orientation, boolean small) throws ObjectIdNotFoundException {
        String path;
        if(TheGame.path){
            if (small) {
                path = "src/main/ressources/map/images/quarters/small/";
            } else {
                path = "src/main/ressources/map/images/quarters/";
            }

        } else {
            if (small) {
                path = "map/images/quarters/small/";
            } else {
                path = "map/images/quarters/";
            }
        }
        switch (quarterId) {
            case 1:
                return path + "black" + orientation + ".png";
            case 2:
                return path + "bluered" + orientation + ".png";
            case 3:
                return path + "purplewhite" + orientation + ".png";
            case 4:
                return path + "redblue" + orientation + ".png";
            case 5:
                return path + "yellowred" + orientation + ".png";
            default:
                throw new ObjectIdNotFoundException(quarterId);
        }
    }
}
