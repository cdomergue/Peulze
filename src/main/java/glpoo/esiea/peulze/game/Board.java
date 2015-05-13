package glpoo.esiea.peulze.game;


import glpoo.esiea.peulze.game.pieces.Piece;
import glpoo.esiea.peulze.game.pieces.Quarter;
import glpoo.esiea.peulze.game.pieces.QuarterType;
import glpoo.esiea.peulze.tools.GameObjectFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Board {

    private List<Piece> pieces;
    private List<Quarter> quarters;
    private int columns;
    private int lines;
    private List<Integer> handPieces;
    private int board[][];

    /**
     * Constructeur
     *
     * @param pieces   Liste des pièces du jeu
     * @param quarters Liste des quarters du jeu
     * @param lines    Nombre de lignes du plateau
     * @param columns  Nombre de colonnes du plateau
     * @param random   DEBUG : si on doit ou non mélanger les pièces de la main
     */
    public Board(List<Piece> pieces, List<Quarter> quarters, int lines, int columns, boolean random) {
        this.pieces = pieces;
        this.quarters = quarters;
        this.columns = columns;
        this.lines = lines;
        initBoard();
        initHand(random);
    }

    public List<Integer> getHandPieces() {
        return handPieces;
    }

    public int[][] getBoard() {
        return board;
    }

    /**
     * Initialise le plateau à 0 (toutes les cases sont vides)
     */
    private void initBoard() {
        board = new int[lines][];
        for (int i = 0; i < lines; i++) {
            board[i] = new int[columns];
        }
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * Initialise la main du joueur avec la liste des pièces
     * Mélange les pièces aléatoirement en changeant l'ordre et la rotation
     *
     * @param random Si false, ne mélange pas les pièces aléatoirement
     */
    private void initHand(boolean random) {
        handPieces = new ArrayList();
        //On ajoute les pièces dans la main
        for (Piece piece : pieces) {
            handPieces.add(piece.getId());
        }
        if (random) {
            //On mélange aléatoirement les pièces de la main
            Collections.shuffle(handPieces);
            //On pivote aléatoirement les pièces

            for (Piece piece : pieces) {
                Random rand = new Random();
                int choice = rand.nextInt(2);
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

    /**
     * Place une pièce de sa main dans le plateau
     * Vérifie que la pièce puisse être bien placée
     *
     * @param piece Pièce à placer
     * @param x     coordonnées
     * @param y     coordonnée
     * @return booléen qui informe si l'action à réussie
     * @throws ObjectIdNotFoundException
     */
    public boolean putPiece(Piece piece, int x, int y) throws ObjectIdNotFoundException {
        //Si on a pas la pièce dans notre main
        if (notInHand(piece)) return false;

        //Si on est en dehors du plateau
        if (x >= lines || y >= columns || x < 0 || y < 0) return false;

        //Si la case est déjà occupée
        if (board[x][y] != 0) return false;

        //On test si les pièces à coté sont bonnes
        if (x != 0) {
            if (board[x - 1][y] != 0 && (GameObjectFinder.getPiece(board[x - 1][y], pieces).getIdSud() != piece.getIdNord()))
                return false;
        } else {
            if (!GameObjectFinder.getQuarter(piece.getIdNord(), quarters).getType().equals(QuarterType.BORD))
                return false;
        }
        if (x != lines - 1) {
            if (board[x + 1][y] != 0 && (GameObjectFinder.getPiece(board[x + 1][y], pieces).getIdNord() != piece.getIdSud()))
                return false;
        } else {
            if (!GameObjectFinder.getQuarter(piece.getIdSud(), quarters).getType().equals(QuarterType.BORD))
                return false;
        }
        if (y != 0) {
            if (board[x][y - 1] != 0 && (GameObjectFinder.getPiece(board[x][y - 1], pieces).getIdEst() != piece.getIdOuest()))
                return false;
        } else {
            if (!GameObjectFinder.getQuarter(piece.getIdOuest(), quarters).getType().equals(QuarterType.BORD))
                return false;
        }
        if (y != columns - 1) {
            if (board[x][y + 1] != 0 && (GameObjectFinder.getPiece(board[x][y + 1], pieces).getIdOuest() != piece.getIdEst()))
                return false;
        } else {
            if (!GameObjectFinder.getQuarter(piece.getIdEst(), quarters).getType().equals(QuarterType.BORD))
                return false;
        }
        board[x][y] = piece.getId();
        removeFromHand(piece);
        return true;
    }

    /**
     * Retirer une pièce du plateau pour l'ajouter dans la main
     *
     * @param x coordonée
     * @param y coordonée
     * @return booléen qui informe si l'opération à réussie
     * @throws ObjectIdNotFoundException
     */
    public boolean takePiece(int x, int y) throws ObjectIdNotFoundException {
        //Si on est en dehors du plateau
        if (x >= lines || y >= columns || x < 0 || y < 0) return false;
        //Si la case n'est pas occupée;
        if (board[x][y] == 0) return false;
        //On ajoute la pièce à la main
        handPieces.add(board[x][y]);
        //On retire la pièce du plateau
        board[x][y] = 0;
        return true;

    }

    /**
     * Vérifie si le puzzle est complet et valide
     *
     * @return booléen de validation
     */
    public boolean checkWin() {
        //Si on a plus de pièces en main, on a gagné
        return handPieces.size() == 0;
    }

    private void removeFromHand(Piece piece) {
        Integer idToRemove = null;
        for (Integer id : handPieces) {
            if (id.equals(piece.getId())) {
                idToRemove = id;
                break;
            }
        }
        if (idToRemove != null) {
            handPieces.remove(idToRemove);
        }
    }

    private boolean notInHand(Piece piece) {
        for (Integer id : handPieces) {
            if (id.equals(piece.getId())) {
                return false;
            }
        }
        return true;
    }


}
