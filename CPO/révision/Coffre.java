import java.util.ArrayList;
import java.util.List;

public class Coffre {
    private List<Bijou> bijoux;
    public Coffre(){
        bijoux = new ArrayList<>();
    }
    public void ajouterBijou(Bijou bijou) {
        bijoux.add(bijou);
    }

    public int calculerValeurTotale() {
        int valeurTotale = 0;
        for (Bijou bijou : bijoux) {
            valeurTotale += bijou.getValeur();
        }
        return valeurTotale;
    }
}
