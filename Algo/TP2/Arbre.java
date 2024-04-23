package TP2;

public class Arbre {
    Noeud racine;

    public Arbre() {
        racine = null;
    }

    public Arbre(Arbre ag, int v, Arbre ad) {
        racine = new Noeud(ag.racine, v, ad.racine);
    }

    public String toString() {
        if (racine == null) {
            return "";
        } else {
            return racine.toString();
        }
    }

    public boolean testABR() {
        if (racine == null) {
            return true;
        } else {
            return racine.testABR(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }

    public void inser(int value) {
        if (racine == null) {
            racine = new Noeud(null, value, null);
        } else if (value < racine.v) {
            if (racine.fg == null) {
                racine.fg = new Noeud(null, value, null);
            } else {
                racine.fg.inser(value);
            }
        } else {
            if (racine.fd == null) {
                racine.fd = new Noeud(null, value, null);
            } else {
                racine.fd.inser(value);
            }
        }
    }

    public boolean membre(int value) {
        return vérfiermembre(racine, value);
    }

    private boolean vérfiermembre(Noeud racine, int value) {
        if (racine == null) {
            return false;
        } else if (value == racine.v) {
            return true;
        } else if (value < racine.v) {
            return vérfiermembre(racine.fg, value);
        } else {
            return vérfiermembre(racine.fd, value);
        }
    }   
    
    public boolean testAVL(){
        if(racine == null){
            return true;
        }else if (racine.testAVL()== -1){
            return false;
        } else return true && testABR();
    }

}
    
class ArbreAVL extends Arbre {
    
    public ArbreAVL() {
        super();
    }

    public ArbreAVL(Arbre arbre) throws NonAVLTreeException {
        if (arbre.testAVL()) {
            this.racine = arbre.racine;
        } else {
            throw new NonAVLTreeException("");
        }
    }

    public static class NonAVLTreeException extends Exception {
        public NonAVLTreeException(String message) {
            super(message);
        }
    }
    @Override
    public void inser(int value) {
        racine = inser(value, racine);
    }

    private Noeud inser(int value, Noeud node) {
        if (node == null) {
            return new Noeud(null,value,null);
        }

        if (value < node.v) {
            node.fg = inser(value, node.fg);
        } else if (value > node.v) {
            node.fd = inser(value, node.fd);
        } else {
            // La valeur existe déjà
            return node;
        }

        // Mettre à jour la hauteur du nœud actuel
        node.updateh();

        // Rééquilibrer le nœud
        if (node.equilibre()) {
            node.updateh();
        }

        return node;
    }
}