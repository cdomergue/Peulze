package glpoo.esiea.peulze.ihm;

import glpoo.esiea.peulze.game.Board;

import javax.swing.*;
import java.awt.*;


public class GameWindow extends JFrame {

    private Board boardInstance;

    public GameWindow() throws HeadlessException {
        super();
        setTitle("Peulze");
        setPreferredSize(new Dimension(1024, 768));
        setSize(new Dimension(1024, 768));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    public void setBoardInstance(Board boardInstance) {
        this.boardInstance = boardInstance;
    }
}
