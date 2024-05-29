/*
package src.valueObjects;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Rechnung {

        private Kunde kunde;
        private LocalDate datum;
        private List<Artikel> gekaufteArtikel;
        private double gesamtePreis;
        private List<RechnungInfos>rechnungsListe;

    public Rechnung(Kunde kunde, LocalDate datum, List<Artikel> gekaufteArtikel) {
        this.kunde = kunde;
        this.datum = datum;
        this.gekaufteArtikel = gekaufteArtikel;
        this.gesamtePreis = gesamtePreis;
    }

    public Rechnung(Kunde benutzer, Map<Artikel, Integer> artikelMap) {

    }


    public static class RechnungInfos {
        public String kunde;
        public String artikel;
        public String artikelPreis;
        public String artikelGesamtPreis;
        public String anzahl;
    }
    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public List<Artikel> getGekaufteArtikel() {
        return gekaufteArtikel;
    }

    public void setGekaufteArtikel(List<Artikel> gekaufteArtikel) {
        this.gekaufteArtikel = gekaufteArtikel;
    }

    public double getGesamtePreis() {
        return gesamtePreis;
    }

    public void setGesamtePreis(double gesamtePreis) {
        this.gesamtePreis = gesamtePreis;
    }

    public void ereignisEinzeigen(String ereignisBeschreibung, int anzahl, Mitarbeiter mitarbeiter) {


        public void drückeRechnung(Warenkorb gekaufteArtikel) {
            for (Warenkorb warenkorb : gekaufteArtikel) {
                Artikel artikel = warenkorb.getArtikel();
            }
        }
    }




    }
    public List<RechnungInfos> getRechnungsListe() {
        return rechnungsListe;
    }

    public void RechnungErzeugen(Kunde kunde, Map<Artikel, Integer> artikelListe)
    {
        rechnungsListe.clear();
        double _gesamtPreis = 0.0;
        for (Map.Entry<Artikel, Integer> mapArtikel : artikelListe.entrySet()) {
            // 1 artikel bezeichnung aus der Liste holen
            Artikel artikel = mapArtikel.getKey();
            int anzahl = mapArtikel.getValue();
            double total =  (anzahl * artikel.getPreis());
            _gesamtPreis +=total;
            RechnungInfos r = new RechnungInfos();
            r.anzahl = String.valueOf(anzahl);
            r.artikel = artikel.getBezeichnung();
            r.artikelPreis = String.valueOf(artikel.getPreis());
            r.artikelGesamtPreis = String.valueOf(total);
            rechnungsListe.add(r);
            // Zyklus wiederholen.
        }
        this.gesamtePreis = _gesamtPreis;
    }

    public void showRechnung(){
        System.out.println("####### Rechnung #######");
        System.out.println("Kunde: " + kunde.getName());
        System.out.println("Datum: " + datum.toString());
        System.out.println("GesamtPreis: " + this.gesamtePreis);
        for (RechnungInfos r : rechnungsListe) {
            System.out.println("Artikel: " + r.artikel +
                    " Anzahl: " + r.anzahl +
                    " Stückpreis: " + r.artikelPreis +
                    " GesamtPreis: " + r.artikelGesamtPreis);
        }
        System.out.println("#######  #######");
    }
}


*/
