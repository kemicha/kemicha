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
        StringBuilder sb = new StringBuilder();
        sb.append("Rechnung\n");
        sb.append("Kunde: ").append(benutzer).append("\n");
        sb.append("Datum: ").append(datum).append("\n");
        sb.append("Artikel:\n");
        for (Warenkorb w : gekaufteArtikel) {
            sb.append("- ").append(w.getArtikel().getBezeichnung())
                    .append(", Menge: ").append(w.getMenge())
                    .append(", Einzelpreis: ").append(w.getArtikel().getPreis()).append("€\n");
        }
        sb.append("Gesamtpreis: ").append(gesamtePreis).append("€\n");
        return sb.toString();
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
