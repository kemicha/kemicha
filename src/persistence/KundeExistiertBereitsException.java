package src.persistence;

public class KundeExistiertBereitsException extends Exception {

    public KundeExistiertBereitsException(String name, int id) {
        super(" Kunde name" + name+ " Kunde Nummer" + id + " Kunde existiert bereit");
    }
}
