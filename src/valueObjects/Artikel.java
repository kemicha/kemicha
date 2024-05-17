package src.valueObjects;
public class  Artikel{
    // Attribute

    int ArtikelNummer;
    private String bezeichnung;
    private int bestand;
    private double preis;
    private int menge;

    // Konstruktoren
    public Artikel(String bezeichnung, int bestand, double preis, int artikelnummer) {
        this.ArtikelNummer = artikelnummer;
        this.bestand = bestand;
        this.preis = preis;
        this.bezeichnung = bezeichnung;
        this.menge = menge;

    }


    // Getter und Setter
    public int getArtikelNummer() {
        return ArtikelNummer;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public int  getBestand() {
        return bestand;
    }

    public void setBestand(int bestand) {
        this.bestand = bestand;
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

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    // toString-Methode
    @Override
    public String toString() {
        return "Artikelnummer: " + ArtikelNummer + " Bezeichnung: " + bezeichnung +
                " Bestand: " + bestand + " Preis: " + preis + " Euro" ;

    }
}