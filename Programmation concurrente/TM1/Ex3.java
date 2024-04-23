package TM1;

public class Ex3 extends Thread {

    public Ex3 (Donnee d){
        this.d = d;
    }
    private Donnee d ;

    @Override
    public void run(){
        for (int i = 1; i <= 1000; i++) {
            // System.out.print(" numero :" + Thread.currentThread().getId() + " " + i  );
             d.x++; 
             
        }
       System.out.println( " "+Thread.currentThread().getId() + " " + d.x );
    }
}
