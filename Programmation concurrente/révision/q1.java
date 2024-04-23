package révision;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class q1 extends Thread {

      public q1 (Donnee x){
            this.d = x;
        }
        Lock l = new ReentrantLock() ;
        private Donnee d ;
    @Override
    public void run(){
        for (int i = 0; i <1000; i++) {
          
          l.lock();
             System.out.println("Thread numero : " + Thread.currentThread().getId()+ " : "+d.x++);
            d.x++;
          l.unlock();
        }
       
        // System.out.println(d.x);
    }
}
