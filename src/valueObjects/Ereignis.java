package src.valueObjects;

import java.util.Date;

public class Ereignis {
    private Artikel artikel;
    private int menge;
    private Date dateFormat;

    public Ereignis( int menge, Artikel artikel, Date dateFormat) {
        this.menge = menge;
        this.artikel = artikel;
        this.dateFormat = (dateFormat != null) ? dateFormat : new Date();
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
