
public class App {
    public static void main(String[] args) {
        Ex1Increamente tache = new Ex1Increamente();


        
        Thread t1 = new Thread (tache);
        Thread t2 = new Thread (tache); 

        t1.start();
        t2.start();

        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println( tache.GetX() );
    }

    //Q2 
    // public static void main(String[] args) {
    //     Ex1Increamente tache = new Ex1Increamente();


    //     for (int i = 0; i < 50 ; i++) {
    //         Thread t = new Thread (tache);
    //         t.start();
    //         try {
    //             t.join();
    //         } catch (Exception e) {
    //             // TODO: handle exception
    //         }
    //     }
    //     System.out.println( tache.GetX() );
    // }
    
}
