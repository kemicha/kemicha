/*
package src.domain;

import src.valueObjects.Artikel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warenkorb {
    private ArtikelVerwaltung artikelVerwaltung;
    private Map<Artikel, Integer> warenkorbArtikel;
    private double gesamtpreis;
    private List<Artikel> artikelList;


    public Warenkorb(ArtikelVerwaltung artikelVerwaltung) {
        this.artikelVerwaltung = artikelVerwaltung;
        this.warenkorbArtikel = new HashMap<>();
        this.gesamtpreis = 0.0;

    }

    public Warenkorb(Artikel artikel, int menge) {
        this.artikelList = new ArrayList<>();
    }

    */
/*public void artikelHinzufuegen(Artikel artikel, int menge) {
        warenkorbArtikel.put(artikel, menge);
    }*//*


    public boolean artikelEntfernen(Artikel artikel) {
        if (warenkorbArtikel.containsKey(artikel)) {
            warenkorbArtikel.remove(artikel);
            return true;
        } else {
            return false;
        }
    }

    public void artikelMengeAendern(Artikel artikel, int neueMenge){
        if(warenkorbArtikel.containsKey(artikel)){
            int mengeAlt = warenkorbArtikel.get(artikel);
            warenkorbArtikel. remove(artikel);
            warenkorbArtikel.put(artikel, neueMenge);
            gesamtpreis -= mengeAlt*artikel.getPreis();
            gesamtpreis += neueMenge*artikel.getPreis();
    }
    }

    public void warenkorbLeeren() {
        warenkorbArtikel.clear();
    }
    public Map <Artikel ,Integer >getWarenkorbInhalt() {
        return warenkorbArtikel ;

    }

    public void getGesamtpreis() {
        Object gesamtpreis;

    }

    private void aktualisiereGesamtpreis() {
        gesamtpreis = 0.0;
        for (Artikel artikel : warenkorbArtikel.keySet()) {
            gesamtpreis += artikel.getPreis() * warenkorbArtikel.get(artikel);
        }
    }
}
*/
