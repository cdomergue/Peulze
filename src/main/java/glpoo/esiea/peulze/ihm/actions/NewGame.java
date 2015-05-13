package glpoo.esiea.peulze.ihm.actions;

import glpoo.esiea.peulze.game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class NewGame extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        Game.newGame();
    }
}
