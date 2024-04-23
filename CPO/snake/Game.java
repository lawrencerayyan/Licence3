package snake;

import javax.swing.*;
import java.awt.*;


public class Game {
	
	private int height;
	private int width;
	private SnakeObservable snake;

	private JFrame frame;
	private SnakeComponent snakeComponent;

	public Game(int width, int height, Coordinate c) {
		this.height = height;
		this.width = width;
		snake = new SnakeObservable(this, c);

		snakeComponent = new SnakeComponent(this);

		// Créez la JFrame
		frame = new JFrame("Snake Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(snakeComponent);

		// Définissez la taille de la fenêtre en fonction de la taille du jeu
		frame.setSize(width * 10, height * 10); // Ajustez la taille en fonction de votre grille
		frame.setVisible(true);


	}	

	public SnakeObservable getSnake() {
		return snake;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	 boolean isOut(Coordinate c) {
		if (c.getX() < 0 || c.getY() < 0)
			return true;
		if (c.getX() >= width || c.getY() >= height)
			return true;
		return false;
	}

	public void step() {
		snake.move();

		snake.register(new SnakeObserver() {
			@Override
			public void update() {
				snakeComponent.update();
			}
		});
	}


}
