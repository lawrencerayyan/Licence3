package TM1;

public class Ex1 extends Thread {
    @Override
    public void run (){

        for (int i = 0; i < 10; i++) {
            System.out.print(" numero :" + Thread.currentThread().getId() + " k /"  );
        }
    }
}
