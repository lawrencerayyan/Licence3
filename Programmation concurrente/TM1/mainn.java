package TM1;

public class mainn {
    public static void main(String[] args) {
        // new Ex1().start();
        // new Ex1().start();
        // new Ex1().start();
        // new Ex1().start();
        // new Ex1().start();
        // new Ex1().start();
        // new Ex1().start();
        // new Ex1().start();
        // new Ex1().start();
        // new Ex1().start();

        // Q2 :

        // Thread t1 = new Ex2();
        // Thread t2 = new Ex2();
        // t1.start();
        // t2.start();
        // // System.out.println(d.x);

        // try {
        // t1.join();
        // t2.join();
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        // Q3 :
        // Donnee d = new Donnee();
        // Thread t3 = new Ex3(d);
        // Thread t4 = new Ex3(d);
        // t3.start();
        // t4.start();

        // try {
        // t3.join();
        // t4.join();
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        // Q4 :
        // Donnee d = new Donnee();
        // int numThreads = 5; // Nombre de threads à exécuter 5 fois

        // for (int i = 0; i < numThreads; i++) {
        //     Thread t5 = new Ex4Inc(d); 
        //      Thread t6 = new Ex4Dec(d); 

        //     t5.start(); 
        //     t6.start(); 

        //     try {
        //         t5.join(); 
        //         t6.join(); 
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }

         //Q5 :
         Donnee d = new Donnee();
         Thread t6 = new Ex5Prod(d);
         Thread t7 = new Ex5Prod(d);
         Thread t8 = new Ex5Cons(d);
         Thread t9 = new Ex5Cons(d);
         t7.start();
         t8.start();
         t9.start();
         t6.start();
            try {
                t6.join();
                t7.join();
                t8.join();
                t9.join();
                
            } catch (Exception e) {
                // TODO: handle exception
            }
    }
}
