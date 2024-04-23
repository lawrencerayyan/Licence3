package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class SnakeComponent extends JComponent implements KeyListener {

    private Game game;

    public SnakeComponent(Game game) {
        this.game = game;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            game.getSnake().setDirection(Direction.Up);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            game.getSnake().setDirection(Direction.Down);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            game.getSnake().setDirection(Direction.Left);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            game.getSnake().setDirection(Direction.Right);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessinez le fond de l'écran
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Obtenez la liste des coordonnées du corps du serpent
        List<Coordinate> body = game.getSnake().getBody();

        // Dessinez le serpent
        g.setColor(Color.GREEN);
        for (Coordinate coordinate : body) {
            int x = coordinate.getX() * 10; // Ajustez la taille en fonction de votre grille
            int y = coordinate.getY() *10 ; // Ajustez la taille en fonction de votre grille
            g.fillRect(x, y, 9, 9); // Ajustez la taille en fonction de votre grille

        }

    }

    public void update() {
        repaint(); // Redessinez le composant lorsque le serpent se déplace
    }
}
