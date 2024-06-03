package src.valueObjects;

import java.util.Date;
import java.util.List;

public class Rechnung {
    private Benutzer benutzer;
    private Date datum;
    private List< Warenkorb> gekaufteArtikel;
    private double gesamtePreis;

    public Rechnung(Benutzer benutzer, Date datum, List<Warenkorb> gekaufteArtikel, double gesamtePreis) {
        this.benutzer = benutzer;
        this.datum = datum;
        this.gekaufteArtikel =gekaufteArtikel;
        this.gesamtePreis = gesamtePreis;
    }


    public Kunde getBenutzer() {
        return (Kunde) benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public List<Warenkorb> getGekaufteArtikel() {
        return gekaufteArtikel;
    }

    public List<Warenkorb> setGekaufteArtikel() {
        this.gekaufteArtikel = gekaufteArtikel;
        return null;
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
                "Benutzer=" + benutzer +
                ", datum=" + datum +
                ", gekaufteArtikel=" +gekaufteArtikel +
                ", gesamtePreis=" + gesamtePreis +
                '}';
    }


  /*  public void zeigeRechung() {
        System.out.println("####### Rechnung #######");
        System.out.println("Benutzer: " + Benutzer.getName() );
        System.out.println("Datum: " + datum.toString());
        System.out.println("GesamtPreis: " + this.gesamtePreis);
        for (Warenkorb r : getGekaufteArtikel()) {
            System.out.println(new StringBuilder().append("Artikel: ")
                    .append(r.getArtikel())
                    .append(" Benutzer: ")
                    .append(r.getArtikel())
                    .append(" Stückpreis: ")
                    .append(r.getMenge())
                    .append(" GesamtPreis: ")
                    .append(r.getGesamtePreis()));
        }
        System.out.println("#######  #######");
    }*/


}
