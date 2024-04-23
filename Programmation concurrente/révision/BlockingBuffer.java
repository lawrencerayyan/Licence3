package révision;
import java.util.LinkedList;

public class BlockingBuffer<T> {
    private LinkedList<T> buffer;
    private int capacity;

    public BlockingBuffer(int capacity) {
        this.buffer = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void put(T item) throws InterruptedException {   
            while (buffer.size() >= capacity) {
                wait(); // Attendez que l'espace soit disponible.
            }
            buffer.add(item);
            notifyAll(); // Réveillez les threads qui attendent get().
    }

    public synchronized T get() throws InterruptedException {
            while (buffer.isEmpty()) {
                wait(); // Attendez que le buffer contienne au moins un élément.
            }
            T item = buffer.remove(0);
            notifyAll(); // Réveillez les threads qui attendent put().
            return item;
    }

    public static void main(String[] args) {
        BlockingBuffer<Integer> buffer = new BlockingBuffer<>(5);

        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    buffer.put(i);
                    System.out.println("Produit : " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    int item = buffer.get();
                    System.out.println("Consommé : " + item);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



class Player implements Runnable {
    private Side playerSide;
    private Ball ball;

    public Player(Side playerSide, Ball ball) {
        this.playerSide = playerSide;
        this.ball = ball;
    }

    public void run() {
        while (true) {
            synchronized (ball) {
                // Vérifiez si la balle est du même côté que le joueur.
                if (ball.getBallSide() == playerSide) {
                    // Le joueur renvoie la balle de l'autre côté.
                    ball.setBallSide(playerSide == Side.LEFT ? Side.RIGHT : Side.LEFT);
                    System.out.println("Joueur du côté " + playerSide + " renvoie la balle.");
                } else {
                    // Le joueur attend passivement que la balle arrive de son côté.
                    try {
                        ball.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class Ball {
    private Side ballSide;

    public Ball(Side side) {
        ballSide = side;
    }

    public Side getBallSide() {
        return ballSide;
    }

    public void setBallSide(Side side) {
        ballSide = side;
        // Réveillez tous les joueurs qui attendent la balle.
        synchronized (this) {
            notifyAll();
        }
    }
}

enum Side { LEFT, RIGHT }

public class App {
    public static void main(String[] args) {
        Ball ball = new Ball(Side.LEFT);
        Player player1 = new Player(Side.LEFT, ball);
        Player player2 = new Player(Side.RIGHT, ball);

        new Thread(player1).start();
        new Thread(player2).start();
    }
}
