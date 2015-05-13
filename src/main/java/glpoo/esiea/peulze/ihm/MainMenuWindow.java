package glpoo.esiea.peulze.ihm;

import glpoo.esiea.peulze.ihm.actions.LoadGame;
import glpoo.esiea.peulze.ihm.actions.NewGame;
import glpoo.esiea.peulze.ihm.actions.QuitGame;

import javax.swing.*;
import java.awt.*;


public class MainMenuWindow extends JFrame {


    public MainMenuWindow() throws HeadlessException {
        super();
        setTitle("Peulze");
        setPreferredSize(new Dimension(1024, 768));
        setSize(new Dimension(1024, 768));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        final JPanel buttons = new JPanel();
        JButton newGame = new JButton(new NewGame());
        JButton loadGame = new JButton(new LoadGame());
        JButton quitGame = new JButton(new QuitGame());
        newGame.setText("Nouveau jeu");
        loadGame.setText("Charger partie");
        quitGame.setText("Quitter");
        buttons.add(newGame);
        buttons.add(loadGame);
        buttons.add(quitGame);
        getContentPane().add(buttons);

    }
}
