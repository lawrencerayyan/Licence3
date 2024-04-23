package TM4;

class Philosophe implements Runnable {
    private Object leftFork;
    private Object rightFork;
    private int philosopherNumber;

    public Philosophe(Object leftFork, Object rightFork, int philosopherNumber) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.philosopherNumber = philosopherNumber;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Attendre une baguette de gauche
                synchronized (leftFork) {
                    System.out.println("Philosophe " + philosopherNumber + " attend la baguette gauche.");
                    synchronized (rightFork) {
                        // Attendre une baguette de droite
                        System.out.println("Philosophe " + philosopherNumber + " attend la baguette droite.");

                        // Manger
                        System.out.println("Philosophe " + philosopherNumber + " mange.");

                        // Relâcher les baguettes
                        System.out.println("Philosophe " + philosopherNumber + " a fini de manger.");
                        leftFork.notify();// Réveille le philosophe à droite.
                    }
                    rightFork.notify(); // Réveille le philosophe à gauche.
                }

                // Pause pour simuler le temps de manger
                Thread.sleep(100);

                // Attendre un peu avant de reprendre les fourchettes
                synchronized (leftFork) {
                    leftFork.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// public class Main {
//     public static void main(String[] args) {
//         int nombrePhilosophes = 5;
//         Philosophe[] philosophes = new Philosophe[nombrePhilosophes];
//         ReentrantLock[] fourchettes = new ReentrantLock[nombrePhilosophes];

//         for (int i = 0; i < nombrePhilosophes; i++) {
//             fourchettes[i] = new ReentrantLock();
//             int fourchetteGauche = i;
//             int fourchetteDroite = (i + 1) % nombrePhilosophes;
//             philosophes[i] = new Philosophe(fourchettes[fourchetteGauche], fourchettes[fourchetteDroite]);
//             Thread thread = new Thread(philosophes[i]);
//             thread.start();
//         }
//     }
// }**




