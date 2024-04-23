package révision;

public class q3dec extends Thread {
   
    Donnee d ; 
    public q3dec (Donnee d ){
        this.d=  d ;
    }

    @Override
    public void run  ()  {
        for (int  i = 0; i < 1000; i++) {
            d.x--;
        }
        System.out.println( "Thread numero : "+Thread.currentThread().getId() + " Decreamente => " + d.x );
    }
}
