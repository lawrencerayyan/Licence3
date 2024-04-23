package TP2;

public class Noeud {
    int h;
    int v;
    Noeud fg;
    Noeud fd;

    Noeud(Noeud fg, int v, Noeud fd) {
        this.fg = fg;
        this.v = v;
        this.fd = fd;
        int h = 1 ;
    }

    public String toString() {
        String sb = "[";
        if (fg != null)
            sb += fg.toString();
        sb += v;
        if (fd != null)
            sb += fd.toString();
        return sb + "]";
    }

    public boolean testABR(int min, int max) {
        if (v <= min || v >= max) {
            return false;
        } else {
            return (this.fg == null || this.fg.testABR(min, this.v))
                    && (this.fd == null || this.fd.testABR(this.v, max));
        }
    }

    public void inser(int value) {
        if (value < v) {
            if (fg == null) {
                fg = new Noeud(null, value, null);
            } else {
                fg.inser(value);
            }
        } else {
            if (fd == null) {
                fd = new Noeud(null, value, null);
            } else {
                fd.inser(value);
            }
        }
    }
    public int testAVL() {
        int gauche = 0;
        int droite = 0;
        if(fg != null) {
            gauche = fg.testAVL();
        }
        if (fd != null) {
            droite = fd.testAVL();
        }
        if (Math.abs(gauche - droite) > 1) {
            return -1;
        }
        return 1 + Math.max(gauche, droite);
    }
    public void rotationGauche ( ) {
        int aval = v ;
        Noeud t1 =fg;
        Noeud t2 = fg.fd;
        v = fd.v;
        fg = fd ;
        fd = fd.fd ;
        fg.v = aval ;
        fg.fg = t1 ;
        fg.fd =  t2 ;
      }
      public void rotationDroite ( ) {
        int cval = v ;
        Noeud t4 = fd ;
        Noeud t3 = fg.fd ;
        v = fg.v ;
        fd = fg ;
        fg = fg.fg ;
        fd.v = cval ;
        fd.fg = t3 ;
        fd.fd = t4 ;
      }
      public boolean equilibre() {
        int equilibre = getEquilibre();

        // Si le nœud est déséquilibré à gauche
        if (equilibre > 1) {
            if (fg.getEquilibre() >= 0) {
                rotationDroite();
            } else {
                fg.rotationGauche();
                rotationDroite();
            }
            return true;
        }
        // Si le nœud est déséquilibré à droite
        else if (equilibre < -1) {
            if (fd.getEquilibre() <= 0) {
                rotationGauche();
            } else {
                fd.rotationDroite();
                rotationGauche();
            }
            return true;
        }
        return false;
    }

    // Mettre à jour les hs après les rotations
    public void updateh() {
        int hG = (fg == null) ? -1 : fg.h;
        int hD = (fd == null) ? -1 : fd.h;
        h = 1 + Math.max(hG, hD);
    }

    // Récupérer l'équilibre du nœud
    private int getEquilibre() {
        int hG = (fg == null) ? -1 : fg.h;
        int hD = (fd == null) ? -1 : fd.h;
        return hG - hD;
    }
    
}
