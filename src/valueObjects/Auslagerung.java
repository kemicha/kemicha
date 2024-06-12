package src.valueObjects;

import java.time.LocalDate;

public class Auslagerung implements Comparable<Auslagerung> {
    private LocalDate datum;
    private int menge;


    public Auslagerung(int menge) {
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
    public int compareTo(Auslagerung other) {
        return this.datum.compareTo(other.datum);
    }

}
