//package cm1;

public class Monfil extends Thread {
    @Override
    public void run(){
        for(int i = 0; i<1000; i ++){
            System.out.println("Je suis le thread numero :" + Thread.currentThread().getId() + " au tour" + i );
            try {
                Thread.sleep(30);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}