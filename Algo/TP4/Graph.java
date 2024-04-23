package TP4;import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

enum Color {
    Blanc,
    Gris,
    Noir
}

class Arrete {
    double poids;
    int cible;

    Arrete(double poids, int cible) {
        this.poids = poids;
        this.cible = cible;
    }
}

class Sommet {
    ArrayList<Arrete> voisins;
    Color couleur;
    int debut, fin, pi, id;
    double distance;

    Sommet() {
        couleur = Color.Blanc;
        debut = Integer.MAX_VALUE;
        voisins = new ArrayList<>();
    }
}

public class Graph {
    private ArrayList<Sommet> sommets;
    private int nbAretes;

    public Graph() {
        sommets = new ArrayList<>();
        nbAretes = 0;
    }

    public void ajoutSommet() {
        Sommet sommet = new Sommet();
        sommet.id = sommets.size();
        sommets.add(sommet);
    }

    public void ajoutArete(int i, int j, double poids) {
        if (i < 0 || i >= sommets.size() || j < 0 || j >= sommets.size())
            throw new IllegalArgumentException("Sommet invalide");

        Arrete arete = new Arrete(poids, j);
        sommets.get(i).voisins.add(arete);
        nbAretes++;
    }

    
    public double getDistance(int i) {
        if (i < 0 || i >= sommets.size())
            throw new IllegalArgumentException("Sommet invalide");

        return sommets.get(i).distance;
    }

    public int getParent(int i) {
        if (i < 0 || i >= sommets.size())
            throw new IllegalArgumentException("Sommet invalide");

        return sommets.get(i).pi;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("digraph{\n");
        for (int i = 0; i < sommets.size(); i++) {
            sb.append(i).append(";\n");
        }
        for (int i = 0; i < sommets.size(); i++) {
            for (Arrete arrete : sommets.get(i).voisins) {
                sb.append(i).append("->").append(arrete.cible).append(" [label=\"").append(arrete.poids).append("\"] ;\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }
    
    public boolean existeArete(int i, int j) 
    {
        if (i < 0 || i >= sommets.size() || j < 0 || j >= sommets.size())
            throw new IllegalArgumentException("Sommet inexistant");

        return sommets.get(i).voisins.contains(j);
    }
    
    public double getNbSommets()
    {
        return sommets.size();
    }
    
    public int getNbAretes()
    {
        return nbAretes;
    }
    
    public void poidsTotal() {
        double poidsTotal = 0;
        for (Sommet sommet : sommets) {
            for (Arrete arrete : sommet.voisins) {
                poidsTotal += arrete.poids;
            }
        }
        System.out.println("Poids total des arêtes : " + poidsTotal);
    }

    public void initSourceUnique(int s) {
        if (s < 0 || s >= sommets.size())
            throw new IllegalArgumentException("Sommet source invalide");

        for (Sommet sommet : sommets) {
            sommet.distance = Double.MAX_VALUE;
            sommet.pi = -1;
        }

        sommets.get(s).distance = 0;
    }
    public void relacherVoisins(int u) {
        if (u < 0 || u >= sommets.size())
            throw new IllegalArgumentException("Sommet invalide");

        Sommet sommetU = sommets.get(u);

        for (Arrete arrete : sommetU.voisins) {
            int v = arrete.cible;
            double poidsUV = arrete.poids;

            if (sommetU.distance + poidsUV < sommets.get(v).distance) {
                sommets.get(v).distance = sommetU.distance + poidsUV;
                sommets.get(v).pi = u;
            }
        }
    }

    public boolean bellmanFord(int s) {
        initSourceUnique(s);

        for (int i = 0; i < sommets.size() - 1; i++) {
            for (int j = 0; j < sommets.size(); j++) {
                relacherVoisins(j);
            }
        }

        // Vérifier la présence de cycles de poids négatif
        for (int i = 0; i < sommets.size(); i++) {
            Sommet sommet = sommets.get(i);
            for (Arrete arrete : sommet.voisins) {
                int v = arrete.cible;
                double poidsUV = arrete.poids;

                if (sommet.distance + poidsUV < sommets.get(v).distance) {
                    System.out.println("Cycle de poids négatif détecté.");
                    return false;
                }
            }
        }

        return true;
    }
public void dijkstra(int s) {
        if (s < 0 || s >= sommets.size())
            throw new IllegalArgumentException("Sommet source invalide");

        initSourceUnique(s);

        PriorityQueue<Sommet> filePriorite = new PriorityQueue<>((u, v) -> Double.compare(u.distance, v.distance));
        filePriorite.addAll(sommets);

        while (!filePriorite.isEmpty()) {
            Sommet u = filePriorite.poll();

            for (Arrete arrete : u.voisins) {
                int v = arrete.cible;
                double poidsUV = arrete.poids;

                if (u.distance + poidsUV < sommets.get(v).distance) {
                    sommets.get(v).distance = u.distance + poidsUV;
                    sommets.get(v).pi = u.id;

                    // Mise à jour de la file de priorité
                    filePriorite.remove(sommets.get(v));
                    filePriorite.add(sommets.get(v));
                }
            }
        }
    }

//      public void parcoursProf() {
//         for (Sommet sommet : sommets) {
//             sommet.couleur = Color.Blanc;
//             sommet.pi = -1;
//         }
//         int temps = 0;
//         for (Sommet sommet : sommets) {
//             if (sommet.couleur == Color.Blanc)
//                 temps = parcoursProfVisite(sommet, temps);
//         }
//     }

//     private int parcoursProfVisite(Sommet u, int temps) {
//         temps++;
//         u.debut = temps;
//         u.couleur = Color.Gris;
//         for (int v : u.voisins) {
//             if (sommets.get(v).couleur == Color.Blanc) {
//                 sommets.get(v).pi = u.id;
//                 temps = parcoursProfVisite(sommets.get(v), temps);
//             }
//         }
//         u.couleur = Color.Noir;
//         temps++;
//         u.fin = temps;
//         return temps;
//     }

//     public String toStringDates() {
//         StringBuilder sb = new StringBuilder();
//         for (Sommet sommet : sommets) {
//             sb.append("sommet ").append(sommet.id).append("[").append(sommet.debut).append(";").append(sommet.fin).append("]\n");
//         }
//         return sb.toString();
//     }
    
//     private void ffconnexeVisite(Graph g, Sommet u, List<Integer> composante) {
//         u.couleur = Color.Gris;
//         composante.add(u.id);
//         for (Arrete arrete : u.voisins) {
//             int v = arrete.cible;
//             if (g.sommets.get(v).couleur == Color.Blanc)
//                 ffconnexeVisite(g, g.sommets.get(v), composante);
//         }
    
//     }

// public List<List<Integer>> ffconnexe() {
//     parcoursProf();
//     Graph transpose = transposeGraph();
//     List<Integer> sommetsTries = new ArrayList<>();
//     for (int i = 0; i < sommets.size(); i++) {
//         sommetsTries.add(i);
//     }
//     sommetsTries.sort((a, b) -> sommets.get(b).fin - sommets.get(a).fin);
//     List<List<Integer>> composantes = new ArrayList<>();
//     for (int sommet : sommetsTries) {
//         if (transpose.sommets.get(sommet).couleur == Color.Blanc) {
//             List<Integer> composante = new ArrayList<>();
//             ffconnexeVisite(transpose, transpose.sommets.get(sommet), composante);
//             composantes.add(composante);
//         }
//     }
//     return composantes;
// }


//     private Graph transposeGraph() {
//         Graph transpose = new Graph();
//         for (int i = 0; i < sommets.size(); i++) {
//             transpose.ajoutSommet();
//         }
//         for (int i = 0; i < sommets.size(); i++) {  
//             for (int j : sommets.get(i).voisins) {
//                 transpose.ajoutArete(j, i);
//             }
//         }
//         return transpose;
//     }
 

}
