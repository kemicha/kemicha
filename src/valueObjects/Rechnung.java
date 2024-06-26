package src.valueObjects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Rechnung {
    private Benutzer kunde;
    private Date datum;
    private double gesamtePreis;
    private String bezeichnung;
    private int anzahl;


    public Rechnung(Benutzer kunde, Date datum, double gesamtePreis,int anzahl, String bezeichnung){
        this.kunde = kunde;
        this.anzahl=anzahl;
        this.datum= datum;
        this.gesamtePreis= gesamtePreis;
        this.bezeichnung= bezeichnung;


    }

    public List<Rechnungsposition> getPositionen() {
        return null;
    }


    public static class RechnungInfos {
        public String kunde;
        public String artikel;
        public String artikelPreis;
        public String artikelGesamtPreis;
        public String anzahl;
    }
    private List<Ereignis> artikelList;
    private List<RechnungInfos>rechnungsListe;



    /* public String genererFacture(Kunde kunde, List<Artikel> articles) {
         StringBuilder facture = new StringBuilder();
         double total = 0;*/
    /*Kunde, Datum, die
gekauften Artikel inkl. Stückzahl und Preisinformation sowie den Gesamtprei
    * */


    public Benutzer getKunde() {
        return kunde;
    }

    public double getGesamtePreis() {
        return gesamtePreis;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public List<Ereignis> getArtikelList() {
        return artikelList;
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

    public Date getDatum() {
        return datum;
    }

    public double getGesamtPreis(){
        return gesamtePreis;
    }

    public void setGesamtePreis(double gesamtePreis) {
        this.gesamtePreis = gesamtePreis;
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


    public class Rechnungsposition {
        private Artikel artikel;
        private int stueckzahl;

        public Rechnungsposition(Artikel artikel, int stueckzahl) {
            this.artikel = artikel;
            this.stueckzahl = stueckzahl;
        }

       public  Artikel getArtikel() {
            return artikel;
        }

        public  int getStueckzahl() {
            return stueckzahl;
        }

    }
}
