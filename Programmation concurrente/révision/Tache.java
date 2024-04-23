package révision;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Tache implements Runnable {

    @Override
    public void run() {
        System.out.println("Tache exécuté par le thread :" + Thread.currentThread().getName() );
        sleep(300);
        System.out.println("TFin de la tache :" + Thread.currentThread().getName());
    }
}

public class App {
    public static void main(String[] args) {
        Executor ex = Executors.newSingleThreadExecutor();  
        Runnable t = new Tache ();
        ex.execute(t);
        ex.execute(t);
    }
}
