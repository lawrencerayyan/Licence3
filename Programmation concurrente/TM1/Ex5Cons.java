package TM1;

public class Ex5Cons extends Thread{
    public Ex5Cons (Donnee d){
        this.d = d;
    }
    private Donnee d ;
    @Override
    public void run (){
        while (true){
            if(d.getFlag()){
                d.setFlag(false);
                System.out.println("Thread cons numero "+Thread.currentThread().getId() +" => "+ d.x );
            }
        }
    }
}
