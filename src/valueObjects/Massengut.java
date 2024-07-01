package src.valueObjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Massengut extends Artikel {
    private int anzahl;
    private final int packungsgroesse;
    private List<Einlagerung> einlagerungen;
    private List<Auslagerung> auslagerungen;
    private List<Ereignis> ereignisse;

    public Massengut(String bezeichnung, int artikelNummer,int bestand, double preis,  int packungsgroesse) {
        super(bezeichnung, artikelNummer,  bestand,preis);
        this.packungsgroesse = packungsgroesse;
        this.anzahl = 0;
        this.einlagerungen = new ArrayList<>();
        this.auslagerungen = new ArrayList<>();
        this.ereignisse = new ArrayList<>();
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

    public List<Ereignis> getEreignisse() {
        return ereignisse;
    }

    public void setEreignisse(List<Ereignis> ereignisse) {
        this.ereignisse = ereignisse;
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



    public void einlagern(Artikel artikel) {
        int bestand = artikel.getBestand();
        int packungsgroesse = artikel.getPackungsgroesse();
        if (bestand % packungsgroesse != 0) {
            throw new IllegalArgumentException("Die Einlagerungsmenge muss ein Vielfaches der Packungsgröße sein.");
        }

        einlagerungen.add(new Einlagerung(bestand));
        ereignisse.add(new Ereignis("Mitarbeiter", 0, artikel, 1, new Date(), "Bestand erhöhen"));
    }

    public void auslagern(Artikel artikel) {
        int bestand = artikel.getBestand();
        if (bestand % packungsgroesse != 0) {
            throw new IllegalArgumentException("Die Auslagerungsmenge muss ein Vielfaches der Packungsgröße sein.");
        }
        auslagerungen.add(new Auslagerung(bestand));
        ereignisse.add(new Ereignis("Mitarbeiter", 0, artikel, 1, new Date(), "Bestand erhöhen"));
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

    public List<Ereignis> getEreignisseSortedByDate() {
        ereignisse.sort((e1, e2) -> e1.getDateFormat().compareTo(e2.getDateFormat()));
        return ereignisse;
    }
}
