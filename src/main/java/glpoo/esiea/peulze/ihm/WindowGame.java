package glpoo.esiea.peulze.ihm;

import glpoo.esiea.peulze.game.ObjectIdNotFoundException;
import glpoo.esiea.peulze.game.TheGame;
import glpoo.esiea.peulze.game.pieces.Piece;
import glpoo.esiea.peulze.tools.GameObjectFinder;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.List;


public class WindowGame extends org.newdawn.slick.BasicGame {

    private static final String MAIN_MENU = "map/mainMenu.tmx";
    private static final String GAME_WINDOW = "map/game.tmx";
    private GameContainer container;
    private TiledMap map;
    private boolean isMainMenu;
    private boolean isReady;
    private List<WindowPiece> windowPieces;
    private Graphics graphics;
    private int selectedPiece;
    private int savedSelectedPiece;
    private boolean loadGame;

    public WindowGame() {
        super("Peulze");
        isMainMenu = true;
        isReady = false;
        windowPieces = new ArrayList<WindowPiece>();
        selectedPiece = 0;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.container = gameContainer;
        this.map = new TiledMap(MAIN_MENU);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        //Si jamais on choisis une autre pièce, on remet celle sélectionnée à sa place
        if(savedSelectedPiece != 0 && selectedPiece != savedSelectedPiece){
            WindowPiece windowPiece = getWindowPiece(savedSelectedPiece);
            if(!windowPiece.isInBoard()) {
                windowPiece.setX(windowPiece.getHandx());
                windowPiece.setY(windowPiece.getHandy());
            }
        }
        savedSelectedPiece = selectedPiece;
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        this.map.render(0, 0);
        if (isReady) {
            printWindowPieces(graphics);
        }
    }

    /**
     * Ici sont gérés les relaches de touches dans le jeu
     *
     * @param key la touche
     * @param c
     */
    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }

    /**
     * Gère les mouvements de la souris
     * @param oldx ancienne position souris
     * @param oldy ancienne position souris
     * @param newx nouvelle position souris
     * @param newy nouvelle position souris
     */
    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        super.mouseMoved(oldx, oldy, newx, newy);
        if (selectedPiece != 0) {
            WindowPiece windowPiece = getWindowPiece(selectedPiece);
            windowPiece.setX(newx-24);
            windowPiece.setY(newy-24);
        }
    }

    /**
     * Ici sont gérés les clics de la souris dans le jeu
     *
     * @param button     Bouton appuyé (0 = gauche, 1 = droite)
     * @param x          Position x de la souris
     * @param y          Posisition y de la souris
     * @param clickCount Nombre de clics
     */
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        super.mouseClicked(button, x, y, clickCount);
        //Si on est dans le menu principal
        if (isMainMenu) {

            //Si on fait un clic gauche
            if (button == 0) {
                //Si on clique sur Jouer
                if (x > 225 && x < 375 && y > 177 && y < 251) {
                    newGame();
                    isMainMenu = false;
                    loadGame = false;
                }

                //Si on clique sur Charger
                if (x > 225 && x < 375 && y > 275 && y < 350) {
                    loadGame = true;
                    loadGame();
                    isMainMenu = false;

                }

                //Si on clique sur Aide
                if (x > 225 && x < 375 && y > 375 && y < 450) {
                    //TODO: Menu aide
                }

                //Si on clique sur Quitter
                if (x > 225 && x < 375 && y > 475 && y < 550) {
                    System.exit(0);
                }
            }

        }
        //Si on est dans le jeu
        else {

            if(button != 1) {
                //Si on clique sur Quitter
                if (x > 226 && x < 372 && y > 527 && y < 571) {
                    System.exit(0);
                }

                //Si on clique sur Sauvegarder
                if (x > 200 && x < 400 && y > 25 && y < 75) {
                    save();
                }

                //Gestion des clics des pièces
                clickPieces(button, x, y);

                //Si on appui sur clique droit
            }
            else {
                //Si on a selectionné une pièce
                if(selectedPiece != 0){
                    //On pivote vers la gauche
                    rotate(false);
                }
            }

        }


    }

    /**
     * On remet les anciennes pièces après un chargement
     */
    private void putOldPieceToBoard() {
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        List<Integer> id = new ArrayList<>();
        int board[][] = TheGame.getBoardInstance().getBoard();
        for(int i = 0; i < 3; i++){
            for(int j =0; j < 3; j++){
                if(board[i][j] != 0){
                    for(WindowPiece windowPiece : windowPieces){
                        if(board[i][j] == windowPiece.getId()){
                            windowPiece.setX(j*100+100);
                            windowPiece.setY(i * 100 + 100);
                            windowPiece.setInBoard(true);
                            updateGraphically(windowPiece,false);
                        }
                    }
                }
            }
        }




    }

    /**
     * Sauvegarde la partie
     */
    private void save() {
        TheGame.save();
    }

    /**
     * Nouvelle partie
     */
    private void newGame() {
        TheGame.newGame();
        play();
    }

    /**
     * Charger dernière partie
     */
    private void loadGame() {
        TheGame.loadGame();
        play();
    }

    /**
     * Jouer
     */
    private void play() {

        try {
            this.map = new TiledMap(GAME_WINDOW);
            createPieces();
        } catch (ObjectIdNotFoundException e) {
            e.printStackTrace();
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }

    /**
     * Créer les pièces à l'écran
     */
    private void createPieces() throws ObjectIdNotFoundException, SlickException {
        List<Integer> handPieces = TheGame.getBoardInstance().getHandPieces();
        int x = 30;
        int y = 30;
        int i = 0;

        for (Piece piece : TheGame.getPieces()) {
            windowPieces.add(new WindowPiece(x, y, piece.getId(), new Image(ImageQuarterFactory.getImage(piece.getIdNord(), "N", true)),
                    new Image(ImageQuarterFactory.getImage(piece.getIdEst(), "E", true)),
                    new Image(ImageQuarterFactory.getImage(piece.getIdSud(), "S", true)),
                    new Image(ImageQuarterFactory.getImage(piece.getIdOuest(), "W", true))));

            //Algo C style!
            i++;
            y += 70;
            if (i < 8) {

            } else {
                x = 520;
                y = 30;
                i = -10;
            }

        }
        isReady = true;
        if(loadGame){
            putOldPieceToBoard();
        }

    }


    /**
     * Affiche les pièces à l'écran
     *
     * @param graphics
     */
    private void printWindowPieces(Graphics graphics) {
        for (WindowPiece windowPiece : windowPieces) {
            graphics.drawImage(windowPiece.getWest(), windowPiece.getX(), windowPiece.getY());
            graphics.drawImage(windowPiece.getNorth(), windowPiece.getX(), windowPiece.getY());
            graphics.drawImage(windowPiece.getEast(), windowPiece.getX(), windowPiece.getY());
            graphics.drawImage(windowPiece.getSouth(), windowPiece.getX(), windowPiece.getY());
        }
    }

    /**
     * Gère les clics sur les pièces
     *
     * @param button
     * @param x
     * @param y
     */
    private void clickPieces(int button, int x, int y) {

        //Si on clique sur la main de gauche
        if (x > 30 && x < 75) {
            for (int i = 30; i < 555; i += 70) {
                searchPiece(30, y, i, i + 45, button);
            }
        }
        //Si on clique sur la main de droite
        if (x > 520 && x < 565) {
            for (int i = 30; i < 555; i += 70) {
                searchPiece(520, y, i, i + 45, button);
            }
        }
        //Si on clique sur le plateau
        if (x > 100 && x < 500 && y > 100 && y < 500) {

            //On teste toutes les lignes
            int i = 100;
            int k = 0;
            while (i <= 400) {
                testLigne(x, y, i, i + 100, k);
                i += 100;
                k++;
            }

        }
    }

    /**
     * Va tester pour chaque lignes les colonnes
     *
     * @param x      coordonnée graphique du clic
     * @param y      coordonnée graphique du clic
     * @param borne1 borne minimale
     * @param borne2 borne maximale
     * @param k      coordonnée du jeu (non graphique)
     */
    private void testLigne(int x, int y, int borne1, int borne2, int k) {
        if (y > borne1 && y < borne2) {
            //On teste toutes les colonnes
            int i = 100, j = 0;
            while (i <= 400) {
                testColonne(x, i, i + 100, k, j);
                i += 100;
                j++;
            }
        }
    }

    /**
     * Va tester chaque colonne
     *
     * @param x      //coordonée graphique du clic
     * @param borne1 //borne minimale
     * @param borne2 //borne maximale
     * @param xArray coordonnée coté jeu (non graphique), x et y inversé
     * @param yArray coordonnée coté jeu (non graphique), x et y inversé
     */
    private void testColonne(int x, int borne1, int borne2, int xArray, int yArray) {
        if (x > borne1 && x < borne2) {
            checkIfAlreayPiece(borne1, (xArray * 100) + 100, xArray, yArray);
            if (selectedPiece != 0) {
                if (TheGame.putPiece(selectedPiece, xArray, yArray)) {
                    for (WindowPiece windowPiece : windowPieces) {
                        if (windowPiece.getId() == selectedPiece) {
                            windowPiece.setInBoard(true);
                        }
                    }
                    graphicallyPutPiece(selectedPiece, borne1, (xArray * 100) + 100);
                }
            } else {
                for (WindowPiece windowPiece : windowPieces) {
                    if (windowPiece.getX() == x && windowPiece.getY() == (xArray * 100) + 100) {
                        TheGame.takePiece(xArray, yArray);
                        makeSmaller(windowPiece);
                        graphicallyPutInHand(windowPiece);
                    }
                }
            }
        }
    }

    /**
     * Vérifie s'il y a déjà une pièce, et l'échange si c'est le cas
     *
     * @param x
     * @param y
     */
    private void checkIfAlreayPiece(int x, int y, int xArray, int yArray) {
        for (WindowPiece windowPiece : windowPieces) {
            if (windowPiece.getX() == x && windowPiece.getY() == y) {
                TheGame.takePiece(xArray, yArray);
                makeSmaller(windowPiece);
                graphicallyPutInHand(windowPiece);
            }
        }

    }

    /**
     * Remet graphiquement une pièce en main
     *
     * @param windowPiece
     */
    private void graphicallyPutInHand(WindowPiece windowPiece) {
        windowPiece.setX(windowPiece.getHandx());
        windowPiece.setY(windowPiece.getHandy());
    }


    /**
     * Place graphiquement une pièce
     *
     * @param id de la pièce
     * @param x  coordonnée graphique
     * @param y  coordonnée graphique
     */
    private void graphicallyPutPiece(int id, int x, int y) {
        for (WindowPiece windowPiece : windowPieces) {
            if (id == windowPiece.getId()) {
                windowPiece.setX(x);
                windowPiece.setY(y);
                makeBigger(windowPiece);
                selectedPiece = 0;
            }
        }
    }

    /**
     * Rend une pièce plus grosse (de la main vers le plateau)
     *
     * @param windowPiece Pièce graphique
     */
    private void makeBigger(WindowPiece windowPiece) {
        updateGraphically(windowPiece, false);
    }

    private void makeSmaller(WindowPiece windowPiece) {
        updateGraphically(windowPiece, true);
    }

    /**
     * Cherche quelle pièce est sur la case (main)
     *
     * @param x      coordonnée graphique du clic
     * @param y      coordonnée graphique du clic
     * @param borne1 borne minimale
     * @param borne2 borne maximale
     */
    private void searchPiece(int x, int y, int borne1, int borne2, int button) {
        if (y > borne1 && y < borne2) {
            //On cherche quelle pièce est sur cette case
            for (WindowPiece windowPiece : windowPieces) {
                if (windowPiece.getX() == x && windowPiece.getY() == borne1) {
                    selectedPiece = windowPiece.getId();
                    break;
                }
            }
        }
    }

    /**
     * Pivote la pièce sélectionnée
     *
     * @param rotateToRight Si on tourne à droite, sinon c'est à gauche
     */
    private void rotate(boolean rotateToRight) {
        try {
            if (rotateToRight)
                GameObjectFinder.getPiece(selectedPiece, TheGame.getPieces()).rotateRight();
            else
                GameObjectFinder.getPiece(selectedPiece, TheGame.getPieces()).rotateLeft();
            for (WindowPiece windowPiece : windowPieces) {
                if (windowPiece.getId() == selectedPiece) {
                    updateGraphically(windowPiece, true);
                    break;
                }
            }
        } catch (ObjectIdNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateGraphically(WindowPiece windowPiece, boolean small) {
        Piece piece = null;
        try {
            piece = GameObjectFinder.getPiece(windowPiece.getId(), TheGame.getPieces());
            windowPiece.setNorth(new Image(ImageQuarterFactory.getImage(piece.getIdNord(), "N", small)));
            windowPiece.setEast(new Image(ImageQuarterFactory.getImage(piece.getIdEst(), "E", small)));
            windowPiece.setSouth(new Image(ImageQuarterFactory.getImage(piece.getIdSud(), "S", small)));
            windowPiece.setWest(new Image(ImageQuarterFactory.getImage(piece.getIdOuest(), "W", small)));
        } catch (ObjectIdNotFoundException e) {
            e.printStackTrace();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private WindowPiece getWindowPiece(int id) {
        for(WindowPiece windowPiece : windowPieces){
            if(windowPiece.getId() == id){
                return windowPiece;
            }
        }
        System.err.println("Impossible de récupérer la window pièce : npe");
        return null;
    }
}
