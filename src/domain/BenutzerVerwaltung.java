package src.domain;

import src.valueObjects.Benutzer;

import java.util.ArrayList;
import java.util.List;

public class BenutzerVerwaltung {
    List<Benutzer> benutzerList = new ArrayList<>();
    private Benutzer benutzer;


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













