package FinalPacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Game  implements KeyListener {


    Board board = new Board(this);
    Timer timer ;
    char direction = 'U';
    static boolean flag = true;


    public Game(){
        JFrame frame = new JFrame();
        frame.setSize(420,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(board, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setTitle(" Pacman");
        frame.setVisible(true);
        frame.addKeyListener(this);
        timer = new Timer(60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!board.title) {
                    if (flag){
                        try {
                            Thread.sleep(1000);
                            flag=false;
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }

                    }

                    if (board.pacman.isPacmanInvisible) {
                       board.pacman.setGameState(new InvisibleState());
                    } else if (board.pacman.isSuperPacman) {
                        board.pacman.setGameState(new SuperPacmanState());
                    } else {
                        board.pacman.setGameState(new NormalState());
                    }

                    board.ghost1.updateState(board.states);
                    board.ghost2.updateState(board.states);
                    board.ghost3.updateState(board.states);
                    board.ghost4.updateState(board.states);

                    board.pacman.move(direction);
                    if( board.balls[board.pacman.x / 20][board.pacman.y / 20]){
                        board.balls[board.pacman.x / 20][board.pacman.y / 20] = false;
                        board.score = board.score+ 100;
                    }
                    // board.pacman.updateState(board.states);

                    // Vérifier si le pacman collecte les pacgommes bonus et appliquer les effets


                    if (board.purpleGommeX == board.pacman.x / 20 && board.purpleGommeY == board.pacman.y / 20 && !board.purpleGommeCollected ) {
                        board.balls[board.pacman.x / 20][board.pacman.y / 20] = false ;
                        board.purpleGommeCollected = true;
                        board.score += 300;

                        // Appliquer l'effet violet (invisibilité aux fantômes) ici
                        board.pacman.activateInvisibilityEffect();
                        
                    }

                    if (board.orangeGommeX == board.pacman.x / 20 && board.orangeGommeY == board.pacman.y / 20 && !board.orangeGommeCollected ) {
                        board.balls[board.pacman.x / 20][board.pacman.y / 20] =false;
                        board.orangeGommeCollected = true;
                        board.score += 500;
                        // Appliquer l'effet orange (superpacman) ici
                        board.pacman.activateSuperPacmanEffect();
                    }




                    if (board.greenGommeX == board.pacman.x / 20 && board.greenGommeY == board.pacman.y / 20 && !board.greenGommeCollected) {
                        board.balls[board.pacman.x / 20][board.pacman.y / 20] = false;
                        board.greenGommeCollected = true;
                        board.score += 1000;
                        // Appliquer l'effet vert (modifier la structure du labyrinthe) ici
                        board.ghost1.x = 9*Componant.cellSize;
                        board.ghost1.y = 7*Componant.cellSize;

                        board.ghost2.x = 9*Componant.cellSize;
                        board.ghost2.y = 7*Componant.cellSize;

                        board.ghost3.x = 9*Componant.cellSize;
                        board.ghost3.y = 7*Componant.cellSize;

                        board.ghost4.x = 9*Componant.cellSize;
                        board.ghost4.y = 7*Componant.cellSize;

                        board.pacman.x=11*Componant.cellSize;
                        board.pacman.y=15*Componant.cellSize;



                        board.init();

                        // Exemple : board.modifyMazeStructure();
                    }
                    // Mettez à jour l'état des pacgommes bonus
                    board.pacman.updateState(board.states);
                }


            }
        });
        timer.start();
    }
    public void stopGame() {
        // Arrêtez le Timer ici
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        // Autres opérations de fin de jeu
    }

    public static void main(String[] args) {
        new Game();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction ='L';
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction ='R';
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction ='U';
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = 'D';
        }
        // appuie sur entre pour commencer la partie
        else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            board.title=false ;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
