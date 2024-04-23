package TM4;

public class MainBridge {
    public static void main(String[] args) {
        Bridge bridge = new Bridge();
        Car[] cars = new Car[10];

        for (int i = 0; i < 5; i++) {
            cars[i] = new Car("Nord", bridge);
            new Thread(cars[i]).start();
        }

        for (int i = 5; i < 5; i++) {
            cars[i] = new Car("Sud", bridge);
            new Thread(cars[i]).start();
        }
    }
}
