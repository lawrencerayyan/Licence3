package révision;

public class q3 extends Thread {
    
    Donnee d ; 
    public q3 (Donnee d ){
        this.d=  d ;
    }

    @Override
    public void run  ()  {
        for (int  i = 0; i < 1000; i++) {
            d.x++;
        }
        System.out.println( "Thread numero : "+Thread.currentThread().getId() + " Increamnte => " + d.x );
    }
}
