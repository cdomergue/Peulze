package glpoo.esiea.peulze.game;

import glpoo.esiea.peulze.game.pieces.*;
import glpoo.esiea.peulze.ihm.GameWindow;
import glpoo.esiea.peulze.ihm.MainMenuWindow;


import java.util.List;


public class Game {

    private static Board boardInstance;
    private static List<Piece> pieces;
    private static List<Quarter> quarters;
    private static MainMenuWindow mainMenu;
    private static GameWindow game;

    public static void main(String[] args) {
        mainMenu = new MainMenuWindow();
        game = new GameWindow();
        mainMenu.setVisible(true);
    }

    public static void newGame() {
        mainMenu.setVisible(false);
        pieces = PieceDaoImpl.getInstance().readAllPieces();
        quarters = QuarterDaoImpl.getInstance().readAllQuarters();
        boardInstance = new Board(pieces, quarters, 4, 4, true);
        game.setBoardInstance(boardInstance);
        game.setVisible(true);
    }

    public static void loadGame() {
        mainMenu.setVisible(false);
        boardInstance = Board.load();
        game.setBoardInstance(boardInstance);
        game.setVisible(true);
    }


}
