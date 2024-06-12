package src.valueObjects;

import java.time.LocalDate;

public class Einlagerung implements Comparable<Einlagerung> {
    private LocalDate datum;
    private int menge;


    public Einlagerung(int menge) {
        this.datum = datum;
        this.menge = menge;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    @Override
    public int compareTo(Einlagerung other) {
        return this.datum.compareTo(other.datum);
    }

}
