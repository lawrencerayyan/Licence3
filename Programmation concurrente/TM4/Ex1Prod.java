package TM4;

public class Ex1Prod implements Runnable {
    private Donnee d;

    public Ex1Prod(Donnee d) {
        this.d = d;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (d) {
                while (d.getFlag()) {
                    try {
                        d.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                d.x++;
                d.setFlag(true);
                d.notify();
            }
        }
    }
}


