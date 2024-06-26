package src.valueObjects;

import java.util.Date;

public class Ereignis {
    private Artikel artikel;
    private int menge;
    private Date dateFormat;
    private int mitarbeiterId;
    private String benutzer= "";
    private String ereignis;


    public Ereignis(String benutzer, int mitarbeirId, Artikel artikel,int menge, Date dateFormat,String ereignis) {
        this.menge = menge;
        this.artikel = artikel;
        this.dateFormat = (dateFormat != null) ? dateFormat : new Date();
        this.mitarbeiterId= mitarbeirId;
        this.benutzer = benutzer;
        this.ereignis= ereignis;
    }



    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public Date getDateFormat() {
        return dateFormat;
    }

    public int getMitarbeiterId() {
        return mitarbeiterId;
    }

    public void setMitarbeiterId(int mitarbeiterId) {
        this.mitarbeiterId = mitarbeiterId;
    }

    public String getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(String benutzer) {
        this.benutzer = benutzer;
    }

    public void setDateFormat(Date dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public String toString() {
        return "Ereignis{" +
                ", artikel=" + artikel +
                ", menge=" + menge +
                ", dateFormat=" + dateFormat +
                '}';
    }
}
