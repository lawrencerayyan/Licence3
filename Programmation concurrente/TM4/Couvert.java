package TM4;

public class Couvert {
    public static final int NUM_PHILOSOPHERS = 5;

    public static void main(String[] args) {
        Object[] forks = new Object[NUM_PHILOSOPHERS];
        Thread[] philosopherThreads = new Thread[NUM_PHILOSOPHERS];

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            int leftForkIndex = i;
            int rightForkIndex = (i + 1) % NUM_PHILOSOPHERS;
            philosopherThreads[i] = new Thread(new Philosophe(forks[leftForkIndex], forks[rightForkIndex], i));
        }

        // Démarrer les threads des philosophes
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosopherThreads[i].start();
        }

        // Réveiller un philosophe pour commencer le dîner
        synchronized (forks[0]) {
            forks[0].notify();
        }
    }
}