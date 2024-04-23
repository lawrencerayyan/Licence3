package révision;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class q4 implements Runnable {
    public int GetX(){
        return x ; 
    }
    public int x = 0 ;
    public q4(){}
    
    Lock l = new ReentrantLock();

    public void run(){
        
        for (int i = 0; i < 1000000; i++) {
           l.lock();
            x++;
        l.unlock();
        }
        
        System.out.println(x);
    }
    
}
