package src.valueObjects;

import java.util.Collections;
import java.util.List;

public class  Artikel{


    private int ArtikelNummer;
    private String bezeichnung;
    private int bestand;
    private double preis;
    private int packungsgroesse;
    private List<Einlagerung> einlagerungen;
    private List<Auslagerung> auslagerungen;





    public Artikel(String bezeichnung, int artikelNummer, int bestand,double preis) {
        this.bezeichnung = bezeichnung;
        this.ArtikelNummer = artikelNummer;
        this.bestand = bestand;
        this.preis = preis;
        this.packungsgroesse=packungsgroesse;


    }




    public int getArtikelNummer() {
        return ArtikelNummer;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public int getAnzahl() {
        return bestand;
    }

    public void setAnzahl(int anzahl) {
        this.bestand = anzahl;
    }

    public double getPreis() {
        return preis;
    }

    public void setArtikelNummer(int artikelNummer) {
        ArtikelNummer = artikelNummer;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public int getBestand() {
        return bestand;
    }

    public int getPackungsgroesse() {
        return packungsgroesse;
    }

    public void sortiereEinlagerungen() {
        Collections.sort(einlagerungen);
    }


    public void sortiereAuslagerungen() {
        Collections.sort(auslagerungen);
    }



    @Override
    public String toString() {
        return    "  Bezeichnung: " + bezeichnung +  " Artikelnummer: " + ArtikelNummer +
                " Bestand: " + bestand + " Preis: " + preis + " Euro" ;

    }

    public void setArtikel(Artikel artikel) {
            this.setArtikelNummer(artikel.getArtikelNummer());
            this.setBezeichnung(artikel.getBezeichnung());
            this.setPreis(artikel.getPreis());
            this.setAnzahl(artikel.getAnzahl());
        }
    }
