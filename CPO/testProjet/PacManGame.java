import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class PacManGame extends JPanel implements ActionListener {
    private Timer timer;
    private Maze maze;
    private Pacman pacman;
    private Ghost[] ghosts;
    private int score;
    private int lives;

    public PacManGame() {
        // Initialisation du jeu
        maze = new Maze();
        pacman = new Pacman(maze);
        ghosts = new Ghost[4];
        for (int i = 0; i < 4; i++) {
            ghosts[i] = new Ghost(maze);
        }
        score = 0;
        lives = 3;

        // Configuration de la fenêtre du jeu
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(pacman);

        // Création d'une boucle de jeu
        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Mettre à jour la logique du jeu ici
        pacman.move();
        for (Ghost ghost : ghosts) {
            ghost.move();
        }
        // Gérer les collisions, les pacgommes, les conditions de victoire/défaite, etc.

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessiner le labyrinthe, les entités, les points, le score, etc.
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pac-Man Game");
        PacManGame game = new PacManGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}






