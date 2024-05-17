package src.valueObjects;

public class Mitarbeiter extends  Benutzer {
    public Mitarbeiter(int id, String name, String passwort,String adresse) {
        super(id, name, passwort,adresse);
    }
    public String toString() {
     return new StringBuilder()
             .append("Name: ")
             .append(getName())
             .append("Id: ")
             .append(getId())
             .toString();
    }
}
