package glpoo.esiea.peulze.tools;

import glpoo.esiea.peulze.game.Game;
import glpoo.esiea.peulze.game.GameObject;
import glpoo.esiea.peulze.game.ObjectIdNotFoundException;
import glpoo.esiea.peulze.game.pieces.Piece;
import glpoo.esiea.peulze.game.pieces.Quarter;

import java.util.ArrayList;
import java.util.List;

public class GameObjectFinder {

    private GameObjectFinder() {

    }

    /**
     * Récupère une pièce parmi une liste de pièce donnée
     * @param id de la pièce cherchée
     * @param pieces liste de pièces
     * @return La pièce trouvée
     * @throws ObjectIdNotFoundException
     */
    public static Piece getPiece(int id, List<Piece> pieces) throws ObjectIdNotFoundException {
        return (Piece) getGameObject(id, pieces);
    }

    /**
     * Récupère un quarter parmi une liste de quarter donnée
     * @param id du quarter cherché
     * @param quarters liste de quarters
     * @return Le quarter trouvé
     * @throws ObjectIdNotFoundException
     */
    public static Quarter getQuarter(int id, List<Quarter> quarters) throws ObjectIdNotFoundException {
        return (Quarter) getGameObject(id, quarters);
    }

    /**
     * Récupère un GameObject parmi une liste de GameObject donnée
     * @param id de l'objet recherché
     * @param list list d'objets
     * @return L'objet recherché
     * @throws ObjectIdNotFoundException
     */
    private static GameObject getGameObject(int id, List<?> list) throws ObjectIdNotFoundException {
        ArrayList<GameObject> objects = (ArrayList<GameObject>) list;
        for(GameObject object : objects){
            if(object.getId() == id){
                return object;
            }
        }
        throw new ObjectIdNotFoundException(id);
    }
}
