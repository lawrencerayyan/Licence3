package TM1;

public class Ex4Dec extends Thread {

    public Ex4Dec (Donnee d){
        this.d = d;
    }
    private Donnee d ;

    @Override
    public void run (){
        
        

     
             for (int j =0; j <= 100000; j++) {
                d.x--;
             }
        

        System.out.println(" "+Thread.currentThread().getId() + " "+"Valeur finale de x décrémenté: " + d.x);
    }
}
