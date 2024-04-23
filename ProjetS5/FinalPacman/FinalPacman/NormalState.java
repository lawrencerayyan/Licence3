package FinalPacman;

public class NormalState implements GameState {

    @Override
    public void handleState(Board board) {
        board.ghost1.move();
        if (board.ghost1.getShape().intersects(board.pacman.getShape())) {
            board.reset();
        }

        board.ghost2.move();
        if (board.ghost2.getShape().intersects(board.pacman.getShape())) {
            board.reset();
        }

        board.ghost3.move();
        if (board.ghost3.getShape().intersects(board.pacman.getShape())) {
            board.reset();
        }

        board.ghost4.move();
        if (board.ghost4.getShape().intersects(board.pacman.getShape())) {
            board.reset();
        }
    }

}