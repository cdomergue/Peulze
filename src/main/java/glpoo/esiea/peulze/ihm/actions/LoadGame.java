package glpoo.esiea.peulze.ihm.actions;

import glpoo.esiea.peulze.game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class LoadGame extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        Game.loadGame();
    }
}
