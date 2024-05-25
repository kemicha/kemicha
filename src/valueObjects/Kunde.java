package src.valueObjects;

public class Kunde extends Benutzer {

    private String adresse;
    public Kunde( String name,int id, String passwort,String adresse) {
        super( name,id,  passwort);
        this.adresse= adresse;

    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("\n Name: ")
                .append(getName())
                .append("\n Id: ")
                .append(getId())
                .append("\n passwort: ")
                .append(getPasswort())
                .append("\n adresse: ")
                .append(getAdresse())
                .toString();
}
}
