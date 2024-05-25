package src.persistence;

public class MitarbeiterExistiertBereitsException extends Exception {

    public MitarbeiterExistiertBereitsException(String name, int id) {
        super("Mitarbeiter Name: "+ name +"Mitarbeiter Nummer" + id +" Mitarbeiter existiert bereit");
    }
}
