package révision;

public class révisionCC1 {
 public static void main(String[] args) {
   
    // Donnee d = new Donnee();
    // Thread t1 = new q1(d);
    // Thread t2 = new q1(d);
    // t1.start();
    // t2.start();

    // try {
    //     t1.join();
    //     t2.join();
    // } catch (Exception e) {
    //     // TODO: handle exception
    // }


    // Donnee d = new Donnee() ;
    // Thread t1 = new q2(d);
    // Thread t2 = new q2(d);

    // t1.start();
    // t2.start();
    
    // try {
    //     t1.join();
    //     t2.join();
    // } catch (Exception e) {
    //     // TODO: handle exception
    // }
    
    
    q4 tache = new q4();
    Thread t1 = new Thread(tache);
    Thread t2 = new Thread(tache);

    t1.start();
    t2.start();
    
    try {
        t1.join();
        t2.join();
    } catch (Exception e) {
        // TODO: handle exception
    }
 }
}
