package glpoo.esiea.peulze.game;


import glpoo.esiea.peulze.game.pieces.Piece;
import glpoo.esiea.peulze.game.pieces.PieceDaoImpl;
import glpoo.esiea.peulze.game.pieces.Quarter;
import glpoo.esiea.peulze.game.pieces.QuarterDaoImpl;
import glpoo.esiea.peulze.ihm.WindowGame;
import glpoo.esiea.peulze.tools.GameObjectFinder;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.io.IOException;
import java.util.List;


public class TheGame {

    private static Board boardInstance;
    private static List<Piece> pieces;
    private static List<Quarter> quarters;


    public static void main(String[] args) throws IOException, SlickException {
        pieces = PieceDaoImpl.getInstance().readAllPieces();
        quarters = QuarterDaoImpl.getInstance().readAllQuarters();
        AppGameContainer appGameContainer = new AppGameContainer(new WindowGame(), 600, 600, false);
        appGameContainer.setTargetFrameRate(60);
        appGameContainer.setTitle("Peulze");
        appGameContainer.start();
    }

    /**
     * Lance un nouveau jeu avec une main aléaoire
     */
    public static void newGame() {
        boardInstance = new Board(pieces, quarters, 4, 4, true);
    }

    /**
     * Récupère une ancienne partie
     */
    public static void loadGame() {
        boardInstance = Board.load();
    }

    /**
     * Sauvegarde la partie
     */
    public static void save() {
        boardInstance.save();
    }

    public static List<Piece> getPieces() {
        return pieces;
    }

    public static List<Quarter> getQuarters() {
        return quarters;
    }

    public static Board getBoardInstance() {
        return boardInstance;
    }

    /**
     * Place une pièce sur le plateau
     *
     * @param pieceId id de la pièce
     * @param x       coordonnée du jeu (non graphique) de la pièce (x et y inversés)
     * @param y       coordonnée du jeu (non graphique) de la pièce (x et y inversés)
     * @return
     */
    public static boolean putPiece(int pieceId, int x, int y) {
        try {
            return boardInstance.putPiece(GameObjectFinder.getPiece(pieceId, pieces), x, y);
        } catch (ObjectIdNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Récupère une pièce sur le plateau
     *
     * @param xArray coordonnée du jeu (non graphique) de la pièce (x et y inversés)
     * @param yArray coordonnée du jeu (non graphique) de la pièce (x et y inversés)
     */
    public static boolean takePiece(int xArray, int yArray) {
        try {
            return boardInstance.takePiece(xArray, yArray);
        } catch (ObjectIdNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Vérifie si l'on a gaggné
     * @return
     */
    public static boolean checkWin() {
      return  boardInstance.checkWin();
    }
}
