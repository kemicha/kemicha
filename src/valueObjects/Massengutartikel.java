package src.valueObjects;

import java.util.ArrayList;
import java.util.List;

public class Massengutartikel extends Artikel {
    private int anzahl;
    private final int packungsgroesse;
    private List<Einlagerung> einlagerungen;
    private List<Auslagerung> auslagerungen;

    public Massengutartikel(String bezeichnung, int artikelNummer, double preis,int bestand, int packungsgroesse) {
        super(bezeichnung,artikelNummer,preis,bestand);
        this.packungsgroesse = packungsgroesse;
        this.anzahl = 0;
        this.einlagerungen = new ArrayList<>();
        this.auslagerungen = new ArrayList<>();

    }

    @Override
    public int getAnzahl() {
        return anzahl;
    }

    @Override
    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public int getPackungsgroesse() {
        return packungsgroesse;
    }

    public List<Einlagerung> getEinlagerungen() {
        return einlagerungen;
    }

    public void setEinlagerungen(List<Einlagerung> einlagerungen) {
        this.einlagerungen = einlagerungen;
    }

    public List<Auslagerung> getAuslagerungen() {
        return auslagerungen;
    }

    public void setAuslagerungen(List<Auslagerung> auslagerungen) {
        this.auslagerungen = auslagerungen;
    }

    public int berechneLagerbestand() {
        return (int) Math.ceil((double) anzahl / packungsgroesse) * packungsgroesse;
    }


    public void artikelHinzufuegen(int menge) {
        anzahl += menge;
    }

    public void artikelEntfernen(int menge) {
        anzahl -= menge;
    }

    public void einlagern(int menge) {
        if (menge % packungsgroesse != 0) {
            throw new IllegalArgumentException("Die Einlagerungsmenge muss ein Vielfaches der Packungsgröße sein.");
        }
        einlagerungen.add(new Einlagerung(menge));
    }

    public void auslagern(int menge) {
        if (menge % packungsgroesse != 0) {
            throw new IllegalArgumentException("Die Auslagerungsmenge muss ein Vielfaches der Packungsgröße sein.");
        }
        auslagerungen.add(new Auslagerung(menge));
    }

    public int getLagerbestand() {
        int einlagerungMenge = einlagerungen.stream().mapToInt(Einlagerung::getMenge).sum();
        int auslagerungMenge = auslagerungen.stream().mapToInt(Auslagerung::getMenge).sum();
        return einlagerungMenge - auslagerungMenge;
    }

    public List<Einlagerung> getEinlagerungenSortedByDate() {
        einlagerungen.sort((e1, e2) -> e1.getDatum().compareTo(e2.getDatum()));
        return einlagerungen;
    }

    public List<Auslagerung> getAuslagerungenSortedByDate() {
        auslagerungen.sort((a1, a2) -> a1.getDatum().compareTo(a2.getDatum()));
        return auslagerungen;
    }

}

