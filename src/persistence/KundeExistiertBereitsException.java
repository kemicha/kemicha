package src.persistence;

public class KundeExistiertBereitsException extends Exception {

    public KundeExistiertBereitsException(String name, String passwort, int id, String adresse) {
        super(" Kunde name" + name+"passwort"+passwort+ " Kunde Nummer" + id +"adresse"+adresse+ " Kunde existiert bereit");
    }
}
