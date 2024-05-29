package src.valueObjects;

import java.util.Date;

public class RechnungObjekt {
    private Kunde kunde;
    private Date datum;
    private  int anzahl;
    private double gesamtePreis;

    public RechnungObjekt(Kunde kunde, Date datum, int anzahl, double gesamtePreis) {
        this.kunde = kunde;
        this.datum = datum;
        this.anzahl = anzahl;
        this.gesamtePreis = gesamtePreis;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public double getGesamtePreis() {
        return gesamtePreis;
    }

    public void setGesamtePreis(double gesamtePreis) {
        this.gesamtePreis = gesamtePreis;
    }

    @Override
    public String toString() {
        return "RechnungObjekt{" +
                "kunde=" + kunde +
                ", datum=" + datum +
                ", anzahl=" + anzahl +
                ", gesamtePreis=" + gesamtePreis +
                '}';
    }
}
