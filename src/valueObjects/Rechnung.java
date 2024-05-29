package src.valueObjects;

import java.util.ArrayList;
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


    public void showRechnung(){
        System.out.println("####### Rechnung #######");
        System.out.println("Benutzer: " + Benutzer.getName());
        System.out.println("Datum: " + datum.toString());
        System.out.println("GesamtPreis: " + this.gesamtePreis);
        for (Warenkorb r : gekaufteArtikel) {
            System.out.println("Artikel: " + r.getArtikel() +
                    " Benutzer: " + r.getArtikel()+
                    " Stückpreis: " + r.getMenge() +
                    " GesamtPreis: " + r.getGesamtePreis());
        }
        System.out.println("#######  #######");
    }

   /* public void kauftArtikel(Benutzer benutzer) {
        List<Warenkorb> warenkorb = new ArrayList<>(benutzer.getWarenkorb());
        double gesamtpreis = warenkorb.stream()
                .mapToDouble(w -> w.getMenge() * w.getArtikel().getPreis())
                .sum();
    }*/
    /*public void drueckeRechnung() {
            System.out.println("Rechnung für: " + Benutzer.getName());
            System.out.println("Datum: " + datum);
            System.out.println("Artikel:");
        for (Warenkorb warenkorb :gekaufteArtikel) {
                Artikel artikel = warenkorb.getArtikel();
                System.out.println(artikel.getBezeichnung() + " - " + warenkorb.getMenge() + " Stück zu " + artikel.getPreis() + " Euro");
            }
            System.out.println("Gesamtpreis: " + gesamtePreis + " Euro");
        }*/

}
