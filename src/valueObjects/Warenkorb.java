package src.valueObjects;

import java.util.HashMap;
import java.util.Map;

public class Warenkorb {
    public double gesamtePreis;
    private Artikel artikel;
    private int menge;
    private Map<Artikel ,Integer > artikelListe;


    public Warenkorb(Artikel artikel, int menge) {
        this.artikelListe = new HashMap<>();
        this.artikel = artikel;
        this.menge = menge;
        this.gesamtePreis= gesamtePreis;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public double getGesamtePreis() {
        return gesamtePreis;
    }

    public void setGesamtePreis(double gesamtePreis) {
        this.gesamtePreis = gesamtePreis;
    }


    public void artikelInWarenkorbRein() {
        if (artikelListe.containsKey(artikel)) {
            int vorhandeneMenge = artikelListe.get(artikel);
            artikelListe.put(artikel, vorhandeneMenge + menge);
        } else {
            artikelListe.put(artikel, menge);
        }
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("\n Warenkorb: ")
                .append("Artikel")
                .append(artikel)
                .append("\n menge = ")
                .append(menge)
                .toString();
    }

}
