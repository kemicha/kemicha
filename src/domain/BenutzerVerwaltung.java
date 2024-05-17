package src.domain;

import src.valueObjects.Benutzer;

import java.util.ArrayList;
import java.util.List;

public class BenutzerVerwaltung {
    List<Benutzer> benutzerList = new ArrayList<>();
    private Benutzer benutzer;

    public BenutzerVerwaltung() {
        benutzerList.add(new Benutzer(01, "kev", "123", "Josefstr 12"));
        benutzerList.add(new Benutzer(02, "shilan", "qwe", "Josefstr 142"));
        benutzerList.add(new Benutzer(03, "Suzan", "asd", "Josefstr 13"));
    }

    public boolean login (String name, String passwort) {
        for (Benutzer benutzer : benutzerList) {
            if (benutzer.getName().equals(name) && benutzer.getPasswort().equals(passwort)) {
                return true;
            }
        }
        return false;
    }

    public void BenutezerAnzeigen() {
        for (Benutzer benutzer : benutzerList) {
        }
    }
}













