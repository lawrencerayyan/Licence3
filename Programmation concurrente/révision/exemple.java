package révision;

public class exemple {
    public static int x = 0;

    public static void main(String[] args) {
        // Tableau pour stocker les threads
        Thread[] threads = new Thread[50];
        Incrementer tache = new Incrementer();
        // Crée et démarre les threads
        for (int i = 0; i < 50; i++) {
            threads[i] = new Thread(tache);
            threads[i].start();
        }

        // Attend que tous les threads se terminent
        for (int i = 0; i < 50; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Affiche la valeur finale de la variable partagée
        System.out.println("Valeur finale de la variable partagée : " + x);
    }

    // Classe qui incrémente la variable partagée
    static class Incrementer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                
                    x++;
                
            }
            System.out.println(Thread.currentThread().getId() + " a terminé. Valeur de la variable : " + x);
        }
    }
}

class Player implements Runnable{
    private Side playerSide ;
    private Ball ball ;
    // les getteurs et les setteurs 
    public Player (Side playerSide , Ball ball ){
        this.playerSide= playerSide;
        this.ball= ball;
    }
    public void run(){
        while (true) {
            // A FAIRE
        }
    }
}

class Ball {
private Side ballSide;
// les getteurs et les setteurs
public Ball(Side side ){
    ballSide=side;
}
    // ON PEUT AJOUTER DES METHODES
}

enum Side {LEFT,RIGHT}

class App{
     public static void main(String[] args) {
        Ball ball = new Ball(Side,LEFT);
        Player player1 = new Player(Side,LEFT, ball);
        Player player2 = new Player(Side,RIGHT, ball);
        new Thread(player1).start();
        new Thread(player2).start();
     }
}

ce code veut simuler une partie tennis de table entre 2 joueurs . On a une balle, qui est une ressource partagée et qui est à chaque moment d'un coté de la table(Side). chauqe joueur joue parfaitement, et quand la ball est de son coté, il la renvoie toujours de l'autre . Si la balle n'est pas de son coté, il attend passivement qu'lle arrive)
Aider moi à compléter ce code pour que le programme produiseun déroulement correcte de la partie :