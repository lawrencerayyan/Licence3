public class Toto extends AbstactToto {
    public Toto() {
        super("");
    }

    
    public int toto(String x) {
        // Implémentation de la méthode toto
        System.out.println(x);
        return 0;
    }
}






// Dans un Coffre il y a diffrents types de Bihou. Chaque bijou a une valeur en euros, exprimée par un entier 
// Parmi les bijoux il y a le Diamant. Un diamant a un poids et une qualité et sa valeur s'obtient en multipliant le poids par la qualité 
// Parmi les bijoux il y a le Collier. un collier a un nombre de perles, et sa valeur est égale au nombre de perles. D'autres types de bijoux sont possible 
// On veut pouvoir calculer la valeur totale des bijoux dan un coffre. écrivez en Java les classe nécessaires pour réaliser ce scénario. Dessinez le diagramme UML des classes 