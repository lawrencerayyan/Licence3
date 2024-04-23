import java.util.concurrent.Semaphore;

class Philosopher extends Thread {
    private Semaphore leftFork;
    private Semaphore rightFork;
    private int philosopherNumber;

    public Philosopher(Semaphore leftFork, Semaphore rightFork, int philosopherNumber) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.philosopherNumber = philosopherNumber;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Attendre une baguette de gauche
                leftFork.acquire();

                // Attendre une baguette de droite
                rightFork.acquire();

                // Manger
                System.out.println("Philosophe " + philosopherNumber + " mange.");

                // Relâcher les baguettes
                leftFork.release();
                rightFork.release();

                // Temps passé à manger 
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
