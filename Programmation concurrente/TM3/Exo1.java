package TM3;

public class Exo1 implements Runnable {

    private static int x = 0;

    public void run() {
        // for (int i = 0; i < 1000; i++) {
        // //synchronized( this ){
        // x++ ;
        // // }
        // }
        incr();
        System.out.println(Thread.currentThread().getId() + " a terminé. la valuer de X : " + x);
    }

    synchronized public void incr() {
        for (int i = 0; i < 1000; i++) {
            // synchronized( this ){
            x++;
            // }
        }
    }

    public static int getValeur() {
        return x;
    }
}
