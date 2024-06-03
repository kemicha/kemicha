package src.valueObjects;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ereignis {
    private Benutzer benutzer;
    private Artikel artikel;
    private int anzahl=0;
    private Date datum = new Date();
    private String ereignis= "";
    private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public Ereignis(Benutzer benutzer, Date datum, Artikel artikel, int anzahl){
        this.benutzer = benutzer;
        this.ereignis= ereignis;
        this.datum= datum;
        this.artikel= artikel;
        this.anzahl= anzahl;
        this.datum = new Date();
        this.dateFormat= dateFormat;

    }

    public Integer getId() {
        return getId();
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public String getEreignis() {
        return ereignis;
    }

    public Date getDatum(){
        return this.datum;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }



    @Override
    public String toString() {
        return "Ereignis{" +
                "benutzer=" + benutzer +
                ", artikel=" + artikel +
                ", anzahl=" + anzahl +
                ", datum=" + datum +
                ", dateFormat=" + dateFormat +
                '}';
    }
}

