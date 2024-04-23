
package TM4;

public class Ex1Cons implements Runnable {
    private Donnee d;

    public Ex1Cons(Donnee d) {
        this.d = d;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (d) {
                while (!d.getFlag()) {
                    try {
                        d.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                d.setFlag(false);
                System.out.println("Thread cons numero " + Thread.currentThread().getId() + " => " + d.x);
                d.notifyAll();
            }
        }
    }

}