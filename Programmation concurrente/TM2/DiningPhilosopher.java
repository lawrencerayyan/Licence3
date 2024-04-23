import java.util.concurrent.Semaphore;

public class DiningPhilosopher {
    public static final int NUM_PHILOSOPHERS = 5;

    public static void main(String[] args) {
        Semaphore[] forks = new Semaphore[NUM_PHILOSOPHERS];

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Semaphore(1);
        }

        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(forks[i], forks[(i + 1) % NUM_PHILOSOPHERS], i);
            philosophers[i].start();
        }
    }
}

