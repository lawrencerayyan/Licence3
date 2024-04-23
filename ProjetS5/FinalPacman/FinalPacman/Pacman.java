package FinalPacman;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Pacman extends Componant {
    public boolean isPacmanInvisible = false;
    public boolean isSuperPacman = false;
    private Color color;
    private Board board;

    private GameState currentState;

    public Pacman(Board board, int x, int y) {
        this.board = board;
        this.color = Color.YELLOW;
        this.x = x ;
        this.y= y ;
        this.currentState = new NormalState(); // Commencez avec l'état normal par défaut
    }

    public void setGameState(GameState state) {
        this.currentState = state;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

   
    public void move(char direction) {

        currentState.handleState(board);
        switch (direction){

            case 'L':
                if (board.greenGommeCollected){
                    if(isValid(x-speed,y) && isValid(x-speed,y+cellSize-5))
                        x-=speed ;
                }else{
                if(isValid(x-speed,y) && isValid(x-speed,y+cellSize-5))
                    x-=speed ;
                else if (y == 180 && x ==20) x= 380 ;
                }
                index = 0 ;
                break;
            case 'R':
                if (board.greenGommeCollected){
                    if(isValid(x+cellSize,y)&&isValid(x+cellSize,y+ cellSize-5))
                        x +=speed;
                }else{
                    if(isValid(x+cellSize,y)&&isValid(x+cellSize,y+ cellSize-5))
                        x +=speed;
                    else if (y  == 180 && x ==380) x= 20 ;
                }
                index = 1 ;
                break;


            case 'U':
                if (board.greenGommeCollected) {

                    if (isValid(x, y - speed) && isValid(x + cellSize - 5, y - speed))
                        y -= speed;
                    else if (y == 20 && x == 180) y = 380;
                }else {
                    if (isValid(x, y - speed) && isValid(x + cellSize - 5, y - speed))
                        y -= speed;
                }
                index = 2;
                break;
            case 'D':
                if (board.greenGommeCollected) {
                    if (isValid(x, y + cellSize) && isValid(x + cellSize - 5, y + cellSize - 5))
                        y += speed;
                    else if (y == 380 && x == 180) y = 20;
                }else {
                    if (isValid(x, y + cellSize) && isValid(x + cellSize - 5, y + cellSize - 5))
                        y += speed;
                }
                index = 3;
                break;
        }
    }

    public void activateSuperPacmanEffect() {
        isSuperPacman = true;
        Timer superPacmanTimer = new Timer(20000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.pacman.setColor(Color.YELLOW); // Réglez la couleur de Pacman à sa couleur normale
                board.ghost1.setColor(Color.PINK); // Réglez la couleur des fantômes à leur couleur normale
                board.ghost2.setColor(Color.GREEN);
                board.ghost3.setColor(Color.CYAN);
                board.ghost4.setColor(Color.RED);
                isSuperPacman = false;
            }
        });
        superPacmanTimer.setRepeats(false);
        superPacmanTimer.start();
    }

    public void activateInvisibilityEffect() {
        isPacmanInvisible = true;
        Timer invisibilityTimer = new Timer(20000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.pacman.setColor(Color.YELLOW);
                isPacmanInvisible = false;
            }
        });
        invisibilityTimer.setRepeats(false);
        invisibilityTimer.start();
    }
}
