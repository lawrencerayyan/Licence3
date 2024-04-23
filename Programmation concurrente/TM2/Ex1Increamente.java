import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ex1Increamente implements Runnable {

    private Lock l = new ReentrantLock();

    public int GetX(){
        return x ; 
    }
    public int x = 0 ;
    @Override
    public void run (){
        for (int i = 0; i < 10000; i++) {
            l.lock();
            x++;
            l.unlock();
        }
        System.out.println(Thread.currentThread().getId()+" " + x );
    }
}