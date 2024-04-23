
public class Maze {
    public int[][] mazeData; // Tableau représentant le labyrinthe
    private int numRows;
    private int numCols;
    private int pacmanStartX;
    private int pacmanStartY;

    public Maze() {
        // Initialisez ici le tableau mazeData avec les valeurs de votre labyrinthe.
        // Vous pouvez utiliser des 1 pour les murs, des 0 pour les chemins, et d'autres valeurs pour les pacgommes, etc.
        // Assurez-vous que le tableau a les dimensions appropriées pour votre labyrinthe.

        numRows =15 /* Nombre de lignes */;
        numCols = 15 /* Nombre de colonnes */;
        mazeData = new int[numRows][numCols];
        String[] textMaze = {
            "WWWWWWWWW",
            "WPPPGPWPW",
            "WPWGGGPWW",
            "WPWWWPWPW",
            "WPWPPPWGW",
            "WPWPWWPWP",
            "WPGPWPWGW",
            "WPWPPWPWP",
            "WWWWWWWWW"
        };

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                char cell = textMaze[i].charAt(j);
                if (cell == 'W') {
                    mazeData[i][j] = 1; // C'est un mur
                } else if (cell == 'P') {
                    mazeData[i][j] = 0; // C'est un chemin
                } else if (cell == 'G') {
                    mazeData[i][j] = 100; // C'est une pacgomme (par exemple, 100 points pour les pacgommes bleues)
                } else if (cell == 'B') {
                    mazeData[i][j] = 300; // C'est une pacgomme bonus (par exemple, 300 points pour les pacgommes violettes)
                }
                // Vous pouvez ajouter d'autres conditions pour d'autres éléments du labyrinthe
            }
        }
        // Déterminez les coordonnées de départ de Pac-Man
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (mazeData[i][j] == 0) {
                    pacmanStartX = j;
                    pacmanStartY = i;
                    break;
                }
            }
        }
    }

    public int getCellValue(int row, int col) {
        // Renvoie la valeur de la cellule (row, col) dans le labyrinthe.
        // Par exemple, 1 pour un mur, 0 pour un chemin, etc.
        return mazeData[row][col];
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getPacmanStartX() {
        return pacmanStartX;
    }

    public int getPacmanStartY() {
        return pacmanStartY;
    }
    public void setCellValue(int row, int col, int value) {
        // Vérifiez d'abord si les coordonnées sont valides
        if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
            // Modifiez la valeur de la cellule du labyrinthe aux coordonnées spécifiées
            mazeData[row][col] = value;
        } else {
            // Gérez l'erreur ou lancez une exception si les coordonnées sont invalides
            throw new IllegalArgumentException("Coordonnées invalides.");
        }
    }
}
