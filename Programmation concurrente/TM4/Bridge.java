package TM4;


// class Bridge {
//     private int carsOnBridge;
//     private String currentDirection;

//     public Bridge() {
//         carsOnBridge = 0;
//         currentDirection = "Nord";
//     }

//     public synchronized void crossBridge(Car car) throws InterruptedException {
//         while (!car.getDirection().equals(currentDirection) || carsOnBridge >= 4) {
//             wait();
//         }
//         carsOnBridge++;
//         System.out.println("Les voiture partant vers Le  " + car.getDirection() + " Sont sur le Pont.");
//     }

//     public synchronized void exitBridge(Car car) {
//         carsOnBridge--;
//         if (carsOnBridge == 0) {
//             if (currentDirection.equals("Nord")) {
//                 currentDirection = "Sud";
//             } else {
//                 currentDirection = "Nord";
//             }
//         }
//         System.out.println("Les voiture partant vers Le " + car.getDirection() + " ont quittées.");
//         notifyAll();
//     }
// }

class Bridge {
    private int carsOnBridge;
    private String currentDirection;
    private int waitingCount;
    
    public Bridge() {
        carsOnBridge = 0;
        currentDirection = "Nord";
        waitingCount = 0;
    }
    
    public synchronized void crossBridge(Car car) throws InterruptedException {
        if (waitingCount >= 4) {
            // Si 4 voitures ou plus attendent dans la direction opposée, laissons les voitures actuelles passer
            while (!car.getDirection().equals(currentDirection)) {
                wait();
            }
        } else {
            while (!car.getDirection().equals(currentDirection) || carsOnBridge >= 4) {
                waitingCount++;
                wait();
                waitingCount--;
            }
        }
        carsOnBridge++;
        System.out.println("Voitures vers le  " + car.getDirection() + " passent.");
    }

    public synchronized void exitBridge(Car car) {
        carsOnBridge--;
        if (carsOnBridge == 0) {
            if (currentDirection.equals("Nord")) {
                currentDirection = "Sud";
            } else {
                currentDirection = "Nord";
            }
        }
        System.out.println("Les voiture partant vers Le " + car.getDirection() + " ont quittées.");
        notifyAll();
    }
}