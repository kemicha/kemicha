package src.Exeptions;

public class BenutzerExistiertBereitsException extends Exception {
    public BenutzerExistiertBereitsException(String name, String passwort) {
  super("name"+name+ "passwort"+ passwort+ "Benutzer existiert bereit!");
    }
}
