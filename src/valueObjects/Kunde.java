package src.valueObjects;

public class Kunde extends Benutzer {

    public Kunde(int id, String name, String passwort,String adresse) {
        super(id, name,  passwort,adresse);

    }

    @Override
    public String toString() {
        return "Kunde{}";
    }
}
