package FinalPacman;

import java.awt.Color;

public class SuperPacmanState implements GameState {

    @Override
    public void handleState(Board board) {
        
        board.pacman.setColor(Color.ORANGE);
        board.ghost1.setColor(Color.BLUE);
        board.ghost2.setColor(Color.BLUE);
        board.ghost3.setColor(Color.BLUE);
        board.ghost4.setColor(Color.BLUE);

        board.ghost1.move();
        if (board.ghost1.getShape().intersects(board.pacman.getShape())) {
            board.ghost1.x = 9*Componant.cellSize;
            board.ghost1.y = 7*Componant.cellSize;
        }
        board.ghost2.move();
        if (board.ghost2.getShape().intersects(board.pacman.getShape())) {
            board.ghost2.x = 9*Componant.cellSize;
            board.ghost2.y = 7*Componant.cellSize;
        }
        board.ghost3.move();
        if (board.ghost3.getShape().intersects(board.pacman.getShape())) {
            board.ghost3.x = 9*Componant.cellSize;
            board.ghost3.y = 7*Componant.cellSize;
        }
        board.ghost4.move();
        if (board.ghost4.getShape().intersects(board.pacman.getShape())) {
            board.ghost4.x = 9*Componant.cellSize;
            board.ghost4.y = 7*Componant.cellSize;
        }
    }
}
