package TM3;

public class App {
    public static void main(String[] args) {
        Exo1 tache = new Exo1() ;
        Thread t1 = new Thread(tache) ;
        Thread t2 = new Thread(tache) ;
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("la valuer final de X : " + Exo1.getValeur() );
    }

    // Q2 : 
        // Exo1 tache = new Exo1();
        // Thread[] thds = new Thread[50];
        // for (int i = 0; i < 50; i++) {
        // thds [i] = new Thread(tache);
        // thds[i].start();
        // for (int j = 0; j < 50; j++) {
        //     }try {
        //         thds[i].join();
        //     } catch (Exception e) {
        //         // TODO: handle exception
        //     }
        // }   
        // System.out.println("la valuer final de X : " + Exo1.getValeur() );
        // }
        
}