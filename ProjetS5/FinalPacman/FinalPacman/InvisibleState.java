package FinalPacman;

import java.awt.Color;

public class InvisibleState implements GameState {

    @Override
    public void handleState(Board board) {
        board.pacman.setColor(new Color(255,255,153));
        board.ghost1.move();
        board.ghost2.move();
        board.ghost3.move();
        board.ghost4.move();
    }
}