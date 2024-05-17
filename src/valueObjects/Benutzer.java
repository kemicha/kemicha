package src.valueObjects;


import java.util.Objects;

public class Benutzer {
    private int id;
    private String name;
    private String passwort;
    private String adresse;

    public Benutzer(int id, String name,  String passwort, String adresse) {
        this.id = id;
        this.name = name;
        this.passwort = passwort;
        this.adresse = adresse;

    }

    public Benutzer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Benutzer)) return false;
        Benutzer benutzer = (Benutzer) o;
        return getId() == benutzer.getId() && Objects.equals(getName(), benutzer.getName()) && Objects.equals(getPasswort(), benutzer.getPasswort()) && Objects.equals(getAdresse(), benutzer.getAdresse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPasswort(), getAdresse());
    }

    @Override
    public String toString() {
        return "Benutzer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passwort='" + passwort + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}

