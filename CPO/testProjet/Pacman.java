import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pacman implements KeyListener {
    private int x;
    private int y;
    private int direction; // 0: haut, 1: droite, 2: bas, 3: gauche
    private boolean isSuperPacman; // Vrai si Pac-Man est super Pac-Man
    private int lives; // Nombre de vies restantes

    private Maze maze; // Référence vers la classe Maze pour accéder aux données du labyrinthe

    public Pacman(Maze maze) {
        this.maze = maze;
        x = maze.getPacmanStartX();
        y = maze.getPacmanStartY();
        direction = 1; // Pac-Man commence en se déplaçant vers la droite
        isSuperPacman = false;
        lives = 3;
    }

    public void move() {
    int newX = x;
    int newY = y;

    // Calculer les nouvelles coordonnées en fonction de la direction
    if (direction == 0) { // Haut
        newY--;
    } else if (direction == 1) { // Droite
        newX++;
    } else if (direction == 2) { // Bas
        newY++;
    } else if (direction == 3) { // Gauche
        newX--;
    }

    // Vérifier si les nouvelles coordonnées sont valides (dans les limites du labyrinthe)
    if (isValidMove(newX, newY)) {
        // Gérer les pacgommes et les pacgommes bonus
        int cellValue = maze.getCellValue(newY, newX);
        if (cellValue == 0) { // Chemin vide
            // Déplacer Pac-Man
            x = newX;
            y = newY;
        } else if (cellValue == 100) { // Pacgomme bleue
            // Manger la pacgomme
            score += 100;
            maze.setCellValue(newY, newX, 0); // Mettre à jour la valeur de la cellule
            // Vérifier les conditions de victoire
            checkWinCondition();
        } else if (cellValue == 300) { // Pacgomme violette (Pac-Man devient invisible)
            // Gérer l'effet de la pacgomme violette
            isSuperPacman = true;
            // Mettre à jour la valeur de la cellule
            maze.setCellValue(newY, newX, 0);
        } else if (cellValue == 500) { // Pacgomme orange (Pac-Man devient super Pac-Man)
            // Gérer l'effet de la pacgomme orange
            isSuperPacman = true;
            // Mettre à jour la valeur de la cellule
            maze.setCellValue(newY, newX, 0);
        } else if (cellValue == 1000) { // Pacgomme verte (modifie la structure du labyrinthe)
            // Gérer l'effet de la pacgomme verte
            // Mettre à jour la structure du labyrinthe
            updateMazeStructure();
            // Mettre à jour la valeur de la cellule
            maze.setCellValue(newY, newX, 0);
        }
    }
}
public boolean isValidMove(int newX, int newY) {
    // Vérifiez d'abord si les nouvelles coordonnées sont à l'intérieur des limites du labyrinthe
    if (newX >= 0 && newX < 15 && newY >= 0 && newY < 15) {
        // Ensuite, vérifiez si la nouvelle cellule est un mur (valeur 1) ou un chemin (valeur 0)
        return Maze.getCellValue(newY,newX);  mazeData[newY][newX] == 0;
    }
    // Si les coordonnées sont en dehors des limites, le déplacement n'est pas valide
    return false;
}
public void eatPacgomme(int x, int y) {
    int cellValue = maze.getCellValue(y, x);
    if (cellValue == 100) { // Vérifiez que c'est bien une pacgomme bleue
        score += 100;
        maze.setCellValue(y, x, 0); // Supprimez la pacgomme du labyrinthe
        checkWinCondition(); // Vérifiez les conditions de victoire
    }
}
public void eatPacgommeBonus(int x, int y) {
    int cellValue = maze.getCellValue(y, x);
    if (cellValue == 300 || cellValue == 500) { // Vérifiez que c'est une pacgomme violette ou orange
        // Gérez les effets de ces pacgommes bonus
        if (cellValue == 300) {
            // Effet de la pacgomme violette (Pac-Man devient invisible)
            isSuperPacman = true;
        } else if (cellValue == 500) {
            // Effet de la pacgomme orange (Pac-Man devient super Pac-Man)
            isSuperPacman = true;
        }
        maze.setCellValue(y, x, 0); // Supprimez la pacgomme bonus du labyrinthe
    }
}
public void eatGhost(int x, int y) {
    if (isSuperPacman) {
        // Gérez les effets de Pac-Man super Pac-Man
        // Par exemple, ajoutez des points pour chaque fantôme mangé
        score += 200;
        // Réinitialisez la position du fantôme
        // (vous devrez implémenter une méthode pour gérer le positionnement des fantômes)
    } else {
        // Si Pac-Man n'est pas super Pac-Man, il perd une vie
        lives--;
        if (lives == 0) {
            // Gérez la condition de défaite du jeu
            gameOver();
        } else {
            // Réinitialisez la position de Pac-Man au point de départ
            x = maze.getPacmanStartX();
            y = maze.getPacmanStartY();
            // Réinitialisez d'autres paramètres si nécessaire
        }
    }
}
public void checkWinCondition() {
    boolean hasPacgommesRemaining = false;

    for (int row = 0; row < maze.getNumRows(); row++) {
        for (int col = 0; col < maze.getNumCols(); col++) {
            if (maze.getCellValue(row, col) == 100) { // Vérifiez si une pacgomme bleue est présente
                hasPacgommesRemaining = true;
                break;
            }
        }
        if (hasPacgommesRemaining) {
            break;
        }
    }

    if (!hasPacgommesRemaining) {
        // Toutes les pacgommes ont été mangées, le joueur a gagné
        // Affichez un message de victoire et terminez la partie
        gameOver(true);
    }
}

@Override
  public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            direction = 0; // Haut
        } else if (key == KeyEvent.VK_RIGHT) {
            direction = 1; // Droite
        } else if (key == KeyEvent.VK_DOWN) {
            direction = 2; // Bas
        } else if (key == KeyEvent.VK_LEFT) {
            direction = 3; // Gauche
        }
    }
@Override
    public void keyReleased(KeyEvent e) {
        // Gérez les événements de touche pour arrêter le déplacement de Pac-Man
    }
@Override
    public void keyTyped(KeyEvent e) {
        // Ne rien faire ici
    }

    // Implémentez d'autres méthodes pour gérer les actions spécifiques de Pac-Man, comme manger des pacgommes, etc.

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isSuperPacman() {
        return isSuperPacman;
    }

    public int getLives() {
        return lives;
    }
}
