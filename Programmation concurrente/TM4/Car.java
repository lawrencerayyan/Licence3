package TM4;

class Car implements Runnable {
    private String direction;
    private Bridge bridge;

    public Car(String direction, Bridge bridge) {
        this.direction = direction;
        this.bridge = bridge;
    }

    @Override
    public void run() {
        while (true) {
            try {
                bridge.crossBridge(this);
                // Faites passer la voiture sur le pont
                Thread.sleep(1000);
                bridge.exitBridge(this);
                // Faites un tour et revenez
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getDirection() {
        return direction;
    }
}


