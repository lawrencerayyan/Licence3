import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosophe implements Runnable {
    private int id;
    private Lock baguetteGauche;
    private Lock baguetteDroite;
    private Lock sauce;

    public Philosophe(int id, Lock baguetteGauche, Lock baguetteDroite, Lock sauce) {
        this.id = id;
        this.baguetteGauche = baguetteGauche;
        this.baguetteDroite = baguetteDroite;
        this.sauce = sauce;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Prendre la baguette gauche
                if (baguetteGauche.tryLock()) {
                    Thread.sleep(100); // Ajouter un délai pour éviter les interblocages

                    // Prendre la baguette droite
                    if (baguetteDroite.tryLock()) {
                        Thread.sleep(100); // Ajouter un délai pour éviter les interblocages

                        // Prendre la sauce
                        if (sauce.tryLock()) {
                            Thread.sleep(100); // Ajouter un délai pour éviter les interblocages

                            // Manger les nouilles avec la sauce
                            System.out.println("Philosophe " + id + " mange les nouilles avec la sauce.");

                            // Déposer la sauce
                            sauce.unlock();
                            Thread.sleep(100); // Ajouter un délai pour éviter les interblocages
                        }

                        // Déposer la baguette droite
                        baguetteDroite.unlock();
                        Thread.sleep(100); // Ajouter un délai pour éviter les interblocages
                    }

                    // Déposer la baguette gauche
                    baguetteGauche.unlock();
                    Thread.sleep(100); // Ajouter un délai pour éviter les interblocages
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Lock baguette1 = new ReentrantLock();
        Lock baguette2 = new ReentrantLock();
        Lock baguette3 = new ReentrantLock();
        Lock baguette4 = new ReentrantLock();
        Lock baguette5 = new ReentrantLock();
        Lock sauce = new ReentrantLock();

        Thread philosophe1 = new Thread(new Philosophe(1, baguette1, baguette2, sauce));
        Thread philosophe2 = new Thread(new Philosophe(2, baguette2, baguette3, sauce));
        Thread philosophe3 = new Thread(new Philosophe(3, baguette3, baguette4, sauce));
        Thread philosophe4 = new Thread(new Philosophe(4, baguette4, baguette5, sauce));
        Thread philosophe5 = new Thread(new Philosophe(5, baguette5, baguette1, sauce));

        philosophe1.start();
        philosophe2.start();
        philosophe3.start();
        philosophe4.start();
        philosophe5.start();
    }
}
