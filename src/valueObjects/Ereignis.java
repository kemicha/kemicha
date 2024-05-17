package src.valueObjects;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ereignis {
    private String benutzer="";
    private String ergeinis="";
    private Artikel artikel;
    private int anzahl=0;
    private Date datum = new Date();
    private int id;
    private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public Ereignis(String benutzer, int id, String ergeinis, Artikel artikel,int anzahl){
        this.benutzer = benutzer;
        this.ergeinis= ergeinis;
        this.artikel= artikel;
        this.anzahl= anzahl;
        this.datum = new Date();
        this.dateFormat= dateFormat;
        this.id = id;
    }

    public Integer getId() {
        return getId();
    }

    public String getBenutzer() {
        return benutzer;
    }

    public String getErgeinis()
    {
        return this.ergeinis;
    }
    // Getters und Setters
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


}

