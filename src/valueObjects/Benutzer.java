package src.valueObjects;


import java.util.List;
import java.util.Objects;

public class Benutzer {
    private int id;
    private String name;
    private String passwort;
    private Kunde kunde;
    private Mitarbeiter mitarbeiter;
    private List<Warenkorb> warenkorb;

    public Benutzer( String name,int id,  String passwort) {
        this.name = name;
        this.id = id;
        this.passwort = passwort;
        this.warenkorb= warenkorb;

    }

    public Benutzer() {
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public List<Warenkorb> getWarenkorb() {
        return warenkorb;
    }

    public void setWarenkorb(List<Warenkorb> warenkorb) {
        this.warenkorb = warenkorb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }


    public Kunde getKunde() {
        return kunde;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Benutzer)) return false;
        Benutzer benutzer = (Benutzer) o;
        return getId() == benutzer.getId() && Objects.equals(getName(), benutzer.getName()) && Objects.equals(getPasswort(), benutzer.getPasswort());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPasswort());
    }

    @Override
    public String toString() {
        return "Benutzer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passwort='" + passwort + '\'' +
                '}';
    }



}

