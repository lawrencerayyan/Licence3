package TP3;

import java.util.ArrayList;

enum Color {
    Blanc,
    Gris,
    Noir
}

class Sommet {
    ArrayList<Integer> voisins;
    Color couleur;
    int debut, fin, pi, id;

    Sommet() {
        couleur = Color.Blanc;
        debut = Integer.MAX_VALUE;
        voisins = new ArrayList<>();
    }

}

public class Graph {
    private ArrayList<Sommet> sommets;
    private int nextId;

    public Graph() {
        sommets = new ArrayList<>();
        nextId = 0;
    }

    public void ajoutSommet() {
        Sommet nouveauSommet = new Sommet();
        nouveauSommet.id = nextId++;
        sommets.add(nouveauSommet);
    }

    public void ajoutArete(int i, int j) {
        if (i < 0 || i >= sommets.size() || j < 0 || j >= sommets.size()) {
            throw new IllegalArgumentException("Invalid vertex index");
        }

        Sommet sommetI = sommets.get(i);
        Sommet sommetJ = sommets.get(j);

        if (!sommetI.voisins.contains(j)) {
            sommetI.voisins.add(j);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("digraph{\n");
        for (int i = 0; i < sommets.size(); i++) {
            sb.append(i).append(";\n");
        }
        for (int i = 0; i < sommets.size(); i++) {
            for (int j : sommets.get(i).voisins) {
                sb.append(i).append("->").append(j).append(";\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public boolean existeArete(int i, int j) {
        if (i < 0 || i >= sommets.size() || j < 0 || j >= sommets.size()) {
            throw new IllegalArgumentException("Invalid vertex index");
        }
        Sommet sommetI = sommets.get(i);
        return sommetI.voisins.contains(j);
    }   

    public int getNbSommets() {
        return sommets.size();
    }

    public int getNbAretes() {
        int count = 0;
        for (Sommet sommet : sommets) {
            count += sommet.voisins.size();
        }
        return count;
    }
    public void parcoursProf() {
        for (Sommet sommet : sommets) {
            sommet.couleur = Color.Blanc;
            sommet.pi = -1;
        }

        int temps = 0;

        for (Sommet sommet : sommets) {
            if (sommet.couleur == Color.Blanc) {
                parcoursProfRec(sommet, temps);
            }
        }
    }

    private void parcoursProfRec(Sommet sommet, int temps) {
        sommet.couleur = Color.Gris;
        sommet.debut = ++temps;

        for (int voisin : sommet.voisins) {
            if (sommets.get(voisin).couleur == Color.Blanc) {
                sommets.get(voisin).pi = sommet.id;
                parcoursProfRec(sommets.get(voisin), temps);
            }
        }

        sommet.couleur = Color.Noir;
        temps++;
        sommet.fin = temps;
    }

    public String toStringDates() {
        StringBuilder sb = new StringBuilder();
        for (Sommet s : sommets) {
            sb.append("sommet ").append(s.id)
              .append("[").append(s.debut)
              .append(";").append(s.fin)
              .append("]\n");
        }
        return sb.toString();
    }
}