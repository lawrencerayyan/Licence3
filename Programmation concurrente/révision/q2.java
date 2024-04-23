package révision;

public class q2 extends  Thread{
   
    Donnee d ;
    public q2 (Donnee d){
        this.d = d ;
    }

    @Override
    public void run(){
        for (int i = 0; i < 1000; i++) {
           d.x++;
            // System.out.println("Thread numero : "+Thread.currentThread().getId() + " " + d.x++);
           
        }
         System.out.println( "Thread numero : "+Thread.currentThread().getId() + " => " + d.x );
    }

}
