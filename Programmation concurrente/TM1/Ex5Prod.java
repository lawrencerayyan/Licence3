package TM1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ex5Prod extends Thread{
    public Ex5Prod (Donnee d){
        this.d = d;
    }
    private Lock l = new ReentrantLock();
    
    private Donnee d ;

    @Override
    public void run (){
        while (true){
            l.lock();
            if(!d.getFlag()){
                d.setFlag(true);
               l.unlock();
                d.x++;
            }
            else{
                l.unlock();
                }
        }
    }
    public static void main(String[] args) {
        Donnee d = new Donnee();
        Thread t6 = new Ex5Prod(d);
        Thread t7 = new Ex5Prod(d);
        Thread t8 = new Ex5Cons(d);
        Thread t9 = new Ex5Cons(d);
        t7.start();
        t8.start();
        t9.start();
        t6.start();
           try {
               t6.join();
               t7.join();
               t8.join();
               t9.join();
               
           } catch (Exception e) {
               // TODO: handle exception
           }
   }
}

