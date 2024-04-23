package FinalPacman;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Ghost extends Componant{
    private Color color;
    public int getPosX() {
        return x;
    }

    public int getPosY() {
        return y;
    }
    public char getDirection() {
        return direction;  // Renvoie la direction actuelle du fantôme
    }
    public void setDirection(char direction){
        this.direction = direction ;
    }

    public void setPosX(int newX) {
        x = newX;
    }

    public void setPosY(int newY) {
        y = newY;
    }
    public Ghost(Color color,int x , int y ){
        this.color=color;
        this.x = x ;
        this.y = y ;
        direction = 'L';
    }

    //verife si le choix est valide ou pas pour pouvoir passer dans cette case
    public boolean choice(){
        return x% cellSize == 0 && y % cellSize == 0;
    }
    public char selectDirection() {
        int random = (int) (Math.random() * 4) + 1;
        index = random % 2 ;
        switch (random) {
            case 1:
                return 'L'; // Gauche
            case 2:
                return 'R'; // Droite
            case 3:
                return 'U'; // Haut
            case 4:
                return 'D'; // Bas
            default:

                return 'R'; // Par défaut, retournez 'R' (droite) en cas de valeur inattendue
        }
    }


    public void move() {
        char currentDirection = getDirection();
        int newX = getPosX();
        int newY = getPosY();

        // Vérifiez si le déplacement dans la direction actuelle est valide
        if (currentDirection == 'L' && isValid(newX - Componant.speed, newY)) {
            setPosX(newX - Componant.speed);
        } else if (currentDirection == 'R' && isValid(newX + Componant.cellSize, newY)) {
            setPosX(newX + Componant.speed);
        } else if (currentDirection == 'U' && isValid(newX, newY - Componant.speed)) {
            setPosY(newY - Componant.speed);
        } else if (currentDirection == 'D' && isValid(newX, newY + Componant.cellSize)) {
            setPosY(newY + Componant.speed);
        } else {
            // Si le déplacement dans la direction actuelle n'est pas valide, choisissez une nouvelle direction aléatoire
            char newDirection = selectDirection();
            setDirection(newDirection);

        }
    }


    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}

