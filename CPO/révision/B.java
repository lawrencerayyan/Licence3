
public class B extends A {
    public void f(){
        System.out.println("f_de_B");
    }
    public void h(){
        System.out.println("h_de_B");
    }

    public static void main(String[] args) {
       
   // A aa =new A();aa.f();
    //A aa =new A();aa.g();
    //A ab = new B(); ab.f();
    //A ab = new B();ab.g();
    // A ab = new B();ab.h();
   // B b= new B();b.h();
    // B b= new B(); b.f();
    B b= new B();b.g();
    }
 

    // // Ci dessous, on donne des lignes de code, indiquez à chaque fois quelle sitiuation se produit :
    // //1- la ligne compile et s'execute sans erreur(indiquez l'ffichage)
    // //2- il y a une erreur à l'exécution ( indiquez dans ce cas la source de l'erreur)
    // //3- il y a une erreur à la compilation (indiquez dans ce cas la source de l'erreur)  

    // 1- A aa =new A();aa.f();
    // 2- A aa =new A();aa.g();
    // 3-A ab = new B(); ab.f();
    // 4-A ab = new B();ab.g();
    // 5-A ab = new B();ab.h();
    // 6-B b= new B();b.h();
    // 7-B b= new B(); b.f();
    // 8-B b= new B();b.g();
}

// écrivez une classe abstaite AbstractToto qui contien une méthode abstraite publique int toto (String x ), un champ privé de type String et un constructeur public qui prend un String en paramétre et intitialise le champ 
// écrivez une classe concrète Toto qui hérite de AbstractToto , avec un constructeur sans arguments 
// compilez la méthode main de la class suivant :
// class App {
//     public static void main(String[] args) {
//         AbstractToto a ;
//         ...
//         System.out.println(a.toto("A"));
//         ...
//     }
// }
// les trois class doivent être écrites de façon qu'elles compilent .