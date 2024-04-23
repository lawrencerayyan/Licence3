import java.util.ArrayList;

public class Tas {
    private ArrayList<Integer> t;

    // Constructeur qui initialise t à un tas vide
    public Tas() {
        t = new ArrayList<>();
    }

    public static int getFilsGauche(int i) {
        return 2 * i + 1;
    }

    public static int getFilsDroit(int i) { 
        return 2 * i + 2;
    }

    public static int getParent(int i) {
        return (i - 1) / 2;
    }

    // Constructeur qui construit un tas avec le contenu de tab
    public Tas(ArrayList<Integer> tab) {
        t = new ArrayList<>(tab);

    }

    @Override
    public String toString(){
        return toString(0);
    }

    public String toString(int index){
        if(index< 0 || index >t.size() -1) return"" ;
        return "["+t.get(index) +toString(2*index+1)+toString(2*index+2)+"]";
    } 

    public boolean testTas() {
        int size = t.size();
        for (int i = size / 2 - 1; i >= 0; i--) {
            int leftChildIndex = getFilsGauche(i);
            int rightChildIndex = getFilsDroit(i);
            

            if (leftChildIndex < size && t.get(i) > t.get(leftChildIndex)) {
                return false;
            }

            if (rightChildIndex < size && t.get(i) > t.get(rightChildIndex)) {
                return false;
            }
        }
        return true;
    }
    public void inser(int value) {
        t.add(value);
        int index = t.size() - 1;

        while (index > 0) {
            int parentIndex = getParent(index);
            if (t.get(parentIndex) < t.get(index)) {
                // Échanger la valeur avec le parent
                int temp = t.get(index);
                t.set(index, t.get(parentIndex));
                t.set(parentIndex, temp);

                index = parentIndex;
            } else {
                break;
            }
        }
    }


    
    public int supprMax(){
        int max = t.get(0);
        t.set(0,t.get(t.size()- 1));
        t.remove(t.size()-1);
        int size = t.size();
        int index = 0;
        while (index < size) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int smallest = index;
    
            // Trouver l'index du plus petit élément entre le noeud actuel, le fils gauche et le fils droit
            if (leftChildIndex < size && t.get(leftChildIndex) < t.get(smallest)) {
                smallest = leftChildIndex;
            }
            if (rightChildIndex < size && t.get(rightChildIndex) < t.get(smallest)) {
                smallest = rightChildIndex;
            }
    
            // Échanger avec le plus petit fils si nécessaire
            if (index != smallest) {
                int temp = t.get(index);
                t.set(index, t.get(smallest));
                t.set(smallest, temp);
    
                // Mettre à jour l'index pour continuer la vérification vers le bas
                index = smallest;
            } else {
                // Le tas est déjà valide
                break;
            }
        }
        return max ;
    }
}
/* 

 // Ajouter un élément au tas
 public void ajouterElement(int element) {
    t.add(element);
    // Appeler la méthode pour maintenir la propriété du tas
    entasserVersLeHaut(t.size() - 1);
}

// Retirer et retourner l'élément minimum du tas
public int retirerMin() {
    if (estVide()) {
        throw new IllegalStateException("Le tas est vide");
    }

    // Échanger le minimum avec le dernier élément
    int min = t.get(0);
    t.set(0, t.get(t.size() - 1));
    t.remove(t.size() - 1);

    // Appeler la méthode pour maintenir la propriété du tas
    entasserVersLeBas(0);

    return min;
}

// Vérifier si le tas est vide


// Méthode pour maintenir la propriété du tas lors de l'ajout d'un nouvel élément
private void entasserVersLeHaut(int index) {
    while (index > 0) {
        int parentIndex = (index - 1) / 2;
        if (t.get(index) < t.get(parentIndex)) {
            // Échanger avec le parent si l'élément est plus petit
            int temp = t.get(index);
            t.set(index, t.get(parentIndex));
            t.set(parentIndex, temp);

            // Mettre à jour l'index pour continuer la vérification vers le haut
            index = parentIndex;
        } else {
            // Le tas est déjà valide
            break;
        }
    }
}

// Méthode pour maintenir la propriété du tas lors du retrait de l'élément minimum
private void entasserVersLeBas(int index) {
    int size = t.size();
    while (index < size) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallest = index;

        // Trouver l'index du plus petit élément entre le noeud actuel, le fils gauche et le fils droit
        if (leftChildIndex < size && t.get(leftChildIndex) < t.get(smallest)) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < size && t.get(rightChildIndex) < t.get(smallest)) {
            smallest = rightChildIndex;
        }

        // Échanger avec le plus petit fils si nécessaire
        if (index != smallest) {
            int temp = t.get(index);
            t.set(index, t.get(smallest));
            t.set(smallest, temp);

            // Mettre à jour l'index pour continuer la vérification vers le bas
            index = smallest;
        } else {
            // Le tas est déjà valide
            break;
        }
    }
}*/