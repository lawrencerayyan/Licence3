package FinalPacman;
import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    int score;
    //Fantomes
    Ghost ghost1 = new Ghost(Color.PINK,9*Componant.cellSize,7*Componant.cellSize);
    Ghost ghost2 = new Ghost(Color.GREEN,9*Componant.cellSize,7*Componant.cellSize);
    Ghost ghost3 = new Ghost(Color.CYAN,9*Componant.cellSize,7*Componant.cellSize);
    Ghost ghost4 = new Ghost(Color.RED,9*Componant.cellSize,7*Componant.cellSize);
    // Pacman
 Pacman pacman  = new Pacman(this,10*Componant.cellSize,15*Componant.cellSize);

    boolean title;
    boolean aObtenuUneVieSupplementaire = false;
    boolean balls[][] ;
    boolean states[][] ;
    int lives = 3 ;


    boolean purpleGommeCollected = false;
    boolean orangeGommeCollected = false;
    boolean greenGommeCollected = false;

    int purpleGommeX = 1; // Modifier les positions et les couleurs au besoin
    int purpleGommeY = 1;

    int orangeGommeX = 19;
    int orangeGommeY = 1;

    int greenGommeX = 1;
    int greenGommeY = 19;
    private Game game;

    public void drawLives(Graphics g){
        for (int i = 0; i <lives ; i++) {

            g.setColor(Color.YELLOW);
            g.fillOval((Componant.cellSize+5)*i + 20,Componant.max+15, 20,20);
        } if (score >= 5000 && !aObtenuUneVieSupplementaire) {
            lives = lives + 1;
            aObtenuUneVieSupplementaire = true; // Marquez qu'une vie supplémentaire a été accordée
            g.setColor(Color.YELLOW);
            g.fillOval((Componant.cellSize+5) + 20,Componant.max+15,20,20);
        }
    }

    public void drawPacman(Graphics g) {
        //pacman.move();
        g.setColor(pacman.getColor());
        g.fillOval(pacman.x, pacman.y, 20, 20);
    }

    

    public void init() {
        if(greenGommeCollected){
            for (int i = 1; i <Componant.cellSize ; i++) {
                for (int j = 1; j <Componant.cellSize ; j++) {
                    balls[i][j]= true;
                    states[i-1][j-1]=true;
                }
            }
           
            // pacman
            balls[11][15] = false ;
            // fantomes
            balls[10][7]=false;
            // pacgommes effets
            balls[1][1]= false;
            balls[1][19]= false;
            balls[19][1]= false;
        }else{
            for (int i = 1; i <Componant.cellSize ; i++) {
                for (int j = 1; j <Componant.cellSize ; j++) {
                    balls[i][j]= true;
                    states[i-1][j-1]=true;
                }
            }
            
            // initialiser les fantomes
            balls[10][7]=false;
            // le pacman
            balls[10][15]= false;
    
            // pacgommes effets
            balls[1][1]= false;
            balls[1][19]= false;
            balls[19][1]= false;
        }
        pacman.setGameState(new NormalState()); // Initialiser Pacman avec l'état normal
       
    }
    public Board(Game game){
        this.game = game;
        title= true;
        balls = new boolean[Componant.cellSize][Componant.cellSize];
        states = new boolean[Componant.cellSize][Componant.cellSize];
        init();
    }
    public void update(Graphics g,int x , int y , int width ,int height){

        g.fillRect(x ,y , width, height);
        for (int i = x/20; i < x/20 + width/20; i++) {
            for (int j = y/20; j <y/20 + height/20; j++) {
                balls[i][j]= false;
                states[i-1][j-1]=false; // pour que les fantomes ne marche pas sur les cases fermées
            }
        }
    }

    public void drawBalls(Graphics g){

        g.setColor(Color.BLUE);
        for (int i = 1; i <Componant.cellSize  ; i++) {
            for (int j = 1; j <Componant.cellSize  ; j++) {
                if ( balls[i][j]){
                    g.fillOval(i*20 +8 ,j*20+8,4,4); // +8 est pour adjuster les pacogommes
                }
            }
        }
    }


    public void drawBoard(Graphics g) {
        g.setColor(Color.WHITE);
        // le jeu va dérouler dans cette réctangle
        g.drawRect(19, 19, 382, 382);

        g.setColor(Color.RED);

        if (!greenGommeCollected) {
            drawInitialMaze(g);
        } else {
            drawModifiedMaze(g);

        }

        // Dessinez les pacgommes, etc., en fonction de l'état du jeu
        drawPacGommes(g);

        repaint();
    }
    private void drawPacGommes(Graphics g) {
        if (!purpleGommeCollected) {
            g.setColor(Color.MAGENTA);
            g.fillOval(purpleGommeX * 20 + 8, purpleGommeY * 20 + 8, 4, 4);
        }

        if (!orangeGommeCollected) {
            g.setColor(Color.ORANGE);
            g.fillOval(orangeGommeX * 20 + 8, orangeGommeY * 20 + 8, 4, 4);
        }

        if (!greenGommeCollected) {
            g.setColor(Color.GREEN);
            g.fillOval(greenGommeX * 20 + 8, greenGommeY * 20 + 8, 4, 4);
        }
    }
    private void drawInitialMaze(Graphics g) {
        
       /* g.setColor(Color.WHITE);
        g.drawRect(19,19,382,382);

        g.setColor(Color.RED);*/
        update(g,40,40,60,20);
        update(g,120,40,60,20);
        update(g,200,20,20,40);
        update(g,240,40,60,20);
        update(g,320,40,60,20);
        update(g,40,80,60,20);
        update(g,160,80,100,20);
        update(g,200,80,20,60);
        update(g,320,80,60,20);
        update(g,20,120,80,60);
        update(g,320,120,80,60);
        update(g,20,200,80,60);
        update(g,320,200,80,60);
        // la moitie
        update(g,160,160,40,20);
        update(g,220,160,40,20);
        update(g,160,180,20,20);
        update(g,160,200,100,20);
        update(g,240,180,20,20);
        update(g,120,120,60,20);
        update(g,120,60,20,120);
        update(g,280,80,20,100);
        update(g,240,120,60,20);
        update(g,280,200,20,60);
        update(g,120,200,20,60);
        update(g,160,240,100,20);
        update(g,200,260,20,40);
        update(g,120,280,60,20);
        update(g,240,280,60,20);
        update(g,40,280,60,20);
        update(g,80,280,20,60);
        update(g,320,280,60,20);
        update(g,320,280,20,60);
        update(g,20,320,40,20);
        update(g,360,320,40,20);
        update(g,160,320,100,20);
        update(g,200,320,20,60);
        update(g,40,360,140,20);
        update(g,240,360,140,20);
        update(g,280,320,20,60);
        update(g,120,320,20,60);
        repaint();
    }

    private void drawModifiedMaze(Graphics g) {
       
       
       /* g.setColor(Color.WHITE);
        g.drawRect(19, 19, 382, 382);

        g.setColor(Color.RED);*/

        update(g,40,40,60,20);
        update(g,40,120,20,60);
        update(g,20,200,40,20);
        update(g,40,240,20,60);
        update(g,40,320,20,60);
        update(g,80,40,20,60);
        update(g,80,160,20,100);
        update(g,80,200,60,20);
        update(g,80,320,20,60);
        update(g,120,20,60,80);
        update(g,120,320,60,80);
        update(g,200,20,60,80);
        update(g,200,320,60,80);
        // la moitie

        update(g,120,120,60,20);
        update(g,60,120,120,20);
        update(g,80,280,20,100);
        update(g,120,240,60,20);
        update(g,200,280,20,60);
        update(g,160,160,40,20);
       // update(g,160,220,40,20);
        //update(g,180,160,20,20);
        update(g,200,160,20,100);
       // update(g,180,240,20,20);
        // had hwe update(g,200,120,20,60);
        //update(g,240,160,100,20);
        update(g,260,200,40,20);
       update(g,280,120,20,60);
        update(g,280,240,20,60);
        update(g,280,40,20,60);
        update(g,280,80,60,20);

        //update(g,380,180,20,40);
        //update(g,360,180,20,40);


        update(g,320,200,60,20);
        update(g,360,40,20,140);
        update(g,360,240,20,140);
        update(g,320,280,20,60);
        update(g,320,120,20,60);
        update(g,280,320,20,60);
        update(g,320,320,20,60);
        update(g,320,20,20,40);
        update(g,320,360,20,40);
       // update(g,320,160,20,100);



        repaint();

    }
    private void drawGhost(Graphics g){
        g.setColor(ghost1.getColor());
        g.fillRect(ghost1.x,ghost1.y,20,20);

        g.setColor(ghost2.getColor());
        g.fillRect(ghost2.x,ghost2.y,20,20);

        g.setColor(ghost3.getColor());
        g.fillRect(ghost3.x,ghost3.y,20,20);

        g.setColor(ghost4.getColor());
        g.fillRect(ghost4.x,ghost4.y,20,20);
    }
    @Override
    public void paint (Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,420,500);

        drawBoard(g);
        drawGhost(g);
        drawPacman(g);
        drawBalls(g);
        drawLives(g);
        Font f = new Font("Arial",Font.BOLD,20);
        g.setFont(f);
        g.drawString("Score :" + score , Componant.max /2 +50 , Componant.max + 30);



        if (title) {
            // Afficher le titre du jeu
            g.setColor(Color.YELLOW); // Choisissez la couleur du texte
            g.setFont(new Font("Arial", Font.BOLD, 36)); // Définissez la police et la taille du texte
            String titleText = "Appuyez sur Entrée"; // Texte du titre
            int textWidth = g.getFontMetrics().stringWidth(titleText); // Largeur du texte
            g.drawString(titleText, (Componant.max - textWidth) / 2, Componant.max / 2); // Dessinez le texte centré
        }

        if (lives == 0) {
            // Afficher l'écran de fin de partie
            g.setColor(Color.WHITE); // Choisissez la couleur du texte
            g.setFont(new Font("Arial", Font.BOLD, 24)); // Définissez la police et la taille du texte
            String gameOverText = "Game Over"; // Texte de fin de partie
            int textWidth = g.getFontMetrics().stringWidth(gameOverText); // Largeur du texte
            g.drawString(gameOverText, (Componant.max - textWidth) / 2, Componant.max / 2); // Dessinez le texte centré
            game.stopGame();
        }

        if(check()){
            g.setColor(Color.WHITE); // Choisissez la couleur du texte
            g.setFont(new Font("Arial", Font.BOLD, 24)); // Définissez la police et la taille du texte
            String gameOverText = "YOU WON !"; // Texte de fin de partie
            int textWidth = g.getFontMetrics().stringWidth(gameOverText); // Largeur du texte
            g.drawString(gameOverText, (Componant.max - textWidth) / 2, Componant.max / 2); // Dessinez le texte centré
            game.stopGame();
        }

    }



    public void reset(){
        if(lives>0){
            lives--;



           // pacman.x = 10*Componant.cellSize ;
           // pacman.y = 15*Componant.cellSize;

            ghost1.x = 9*Componant.cellSize;
            ghost1.y = 7*Componant.cellSize;

            ghost2.x = 9*Componant.cellSize;
            ghost2.y = 7*Componant.cellSize;

            ghost3.x = 9*Componant.cellSize;
            ghost3.y = 7*Componant.cellSize;

            ghost4.x = 9*Componant.cellSize;
            ghost4.y = 7*Componant.cellSize;

            pacman.x=11*Componant.cellSize;
            pacman.y=15*Componant.cellSize;
            Game.flag =true ;
        }

    }





    public boolean check() {
        for (int i = 0; i < Componant.cellSize; i++) {
            for (int j = 0; j < Componant.cellSize; j++) {
              //  System.out.println("i = " + i + ", j = " + j + ", balls[i][j] = " + balls[i][j]);
           
                if (balls[i][j])
                    return false;
            }
        }
        return true ;
    }
}