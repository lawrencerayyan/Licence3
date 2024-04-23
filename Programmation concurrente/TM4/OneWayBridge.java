package TM4;
public class OneWayBridge {
    public static void main(String[] args) {
        Bridge bridge = new Bridge();
        Car[] cars = new Car[10];

        for (int i = 0; i < 5; i++) {
            cars[i] = new Car("north", bridge);
            new Thread(cars[i]).start();
        }

        for (int i = 5; i < 10; i++) {
            cars[i] = new Car("south", bridge);
            new Thread(cars[i]).start();
        }
    }
}