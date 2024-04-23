
public class Ghost {
    private int x;
    private int y;
    private int direction; // 0: haut, 1: droite, 2: bas, 3: gauche
    private boolean isVulnerable; // Vrai si le fantôme est vulnérable
    private int vulnerabilityDuration; // Durée de vulnérabilité
    private int respawnX; // Position de réapparition du fantôme
    private int respawnY;

    private Maze maze; // Référence vers la classe Maze pour accéder aux données du labyrinthe

    public Ghost(Maze maze) {
        this.maze = maze;
        // Initialisez la position initiale du fantôme et d'autres attributs selon vos besoins
        isVulnerable = false;
        vulnerabilityDuration = 0;
        x = 5/* position initiale x */;
        y = 5/* position initiale y */;
        direction = 0/* direction initiale */;
        
        respawnX = 10/* position de réapparition x */;
        respawnY =10 /* position de réapparition y */;
    }

    public void move() {
        if (!isVulnerable) {
            // Si Pac-Man n'est pas super Pac-Man, le fantôme se déplace de manière aléatoire
            Random random = new Random();
            int newDirection;
            
            do {
                // Génère une nouvelle direction aléatoire (0: haut, 1: droite, 2: bas, 3: gauche)
                newDirection = random.nextInt(4);
            } while (!isValidMove(newDirection));

            // Mettez à jour la direction du fantôme
            direction = newDirection;

            // Déplacez le fantôme dans la nouvelle direction
            int newX = x;
            int newY = y;

            if (direction == 0) { // Haut
                newY--;
            } else if (direction == 1) { // Droite
                newX++;
            } else if (direction == 2) { // Bas
                newY++;
            } else if (direction == 3) { // Gauche
                newX--;
            }

            if (isValidMove(direction)) {
                x = newX;
                y = newY;
            }
        } else {
            // Gérez le comportement du fantôme lorsque Pac-Man est super Pac-Man
            // Par exemple, faites-le se déplacer plus lentement ou évitez Pac-Man
        }
    }

    private boolean isValidMove(int dir) {
        // Vérifiez si le mouvement dans la direction donnée est valide
        int newX = x;
        int newY = y;

        if (dir == 0) { // Haut
            newY--;
        } else if (dir == 1) { // Droite
            newX++;
        } else if (dir == 2) { // Bas
            newY++;
        } else if (dir == 3) { // Gauche
            newX--;
        }

        // Assurez-vous que les nouvelles coordonnées ne sortent pas du labyrinthe et ne touchent pas un mur
        return newX >= 0 && newX < maze.getNumCols() && newY >= 0 && newY < maze.getNumRows()
                && maze.getCellValue(newY, newX) != 1; // 1 représente un mur dans le labyrinthe
    }

    public void respawn() {
        // Réinitialisez la position et d'autres attributs du fantôme après qu'il ait été capturé
        x = respawnX;
        y = respawnY;
        isVulnerable = false;
        vulnerabilityDuration = 0;
    }

    public void setVulnerable() {
        // Définissez le fantôme comme vulnérable (par exemple, lorsque Pac-Man mange une pacgomme spéciale)
        isVulnerable = true;
        vulnerabilityDuration = 10/* définissez une durée de vulnérabilité */;
    }

    public void capture() {
    if (isVulnerable) {
        // Le fantôme est vulnérable, il est capturé et doit être réinitialisé
        respawn();
        // Ajoutez des points à Pac-Man pour avoir capturé le fantôme
        pacman.eatGhost();
    } else {
        // Le fantôme capture Pac-Man
        // Vous pouvez implémenter ici la logique pour la perte de vie de Pac-Man
        // Par exemple, décrémentez le nombre de vies de Pac-Man et réinitialisez sa position
        pacman.eatGhost();
    }
}

    // Ajoutez d'autres méthodes et attributs selon vos besoins pour gérer le comportement des fantômes
}
