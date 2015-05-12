package glpoo.esiea.peulze.game;


import glpoo.esiea.peulze.game.pieces.Piece;
import glpoo.esiea.peulze.game.pieces.Quarter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Board {

    private static Board boardInstance;
    private List<Piece> pieces;
    private List<Quarter> quarters;
    private int columns;
    private int lines;
    private List<Piece> piecesMain;
    private int board[][];

    public List<Piece> getPiecesMain() {
        return piecesMain;
    }

    public int[][] getBoard() {
        return board;
    }

    private Board(List<Piece> pieces, List<Quarter> quarters, int lines, int columns) {
        this.pieces = pieces;
        this.quarters = quarters;
        this.columns = columns;
        this.lines = lines;
        initBoard();
        initMain();
    }

    public static Board getInstance(List<Piece> pieces, List<Quarter> quarters, int lines, int columns){
        if(boardInstance == null) {
            boardInstance = new Board(pieces, quarters, lines, columns);
        }
            return boardInstance;
    }


    /**
     * Initialise le plateau à 0 (toutes les cases sont vides)
     */
    private void initBoard() {
        board = new int[lines][];
        for(int i =0; i < lines; i++){
            board[i] = new int[columns];
        }
        for(int i=0; i < lines; i++){
            for(int j = 0; j < columns; j++){
                board[i][j] = 0;
            }
        }
    }

    /**
     * Initialise la main du joueur avec la liste des pièces
     * Mélange les pièces aléatoirement en changeant l'ordre et la rotation
     */
    private void initMain() {
        piecesMain = new ArrayList();
        //On copie les pièces dans la main
        piecesMain.addAll(pieces);
        //On mélange aléatoirement les pièces de la main
        Collections.shuffle(piecesMain);
        //On rotatione aléatoirement les pièces

        for (Piece piece : piecesMain) {
            Random rand = new Random();
            int choice = rand.nextInt(1);
            int nbRotate = rand.nextInt(10);
            switch (choice) {
                case 0:
                    for (int j = 0; j < nbRotate; j++) {
                        piece.rotateLeft();
                    }
                    break;
                case 1:
                    for (int j = 0; j < nbRotate; j++) {
                        piece.rotateRight();
                    }
                    break;
            }
        }

    }



}
