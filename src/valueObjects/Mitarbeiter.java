package src.valueObjects;

public class Mitarbeiter extends  Benutzer {
    public Mitarbeiter( String name,int id, String passwort) {
        super(name,id, passwort);
    }
    public String toString() {
     return new StringBuilder()
             .append("\n Name: ")
             .append(getName())
             .append("\n Id: ")
             .append(getId())
             .append("\n passwort: ")
             .append(getPasswort())
             .toString();
    }
}
