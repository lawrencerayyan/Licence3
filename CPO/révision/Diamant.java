public class Diamant extends Bijou{
    private int poids;
    private int qualite ;

    public Diamant(int poids, int qualite) {
        super(poids*qualite);
        this.poids = poids;
        this.qualite= qualite;
    }
    
}
