package src.persistence;

public class MitarbeiterExistiertBereitsException extends Exception {

    public MitarbeiterExistiertBereitsException(String name,String passwort, int id) {
        super("Mitarbeiter Name: "+ name +"Mitarbeiter Nummer" +"passwort"+passwort+ id +" Mitarbeiter existiert bereit");
    }
}
