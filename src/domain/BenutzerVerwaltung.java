package src.domain;

import src.persistence.*;
import src.valueObjects.Benutzer;
import src.valueObjects.Kunde;
import src.valueObjects.Mitarbeiter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BenutzerVerwaltung {
    List<Benutzer> benutzerList = new ArrayList<>();
    private Benutzer benutzer;
    private PersistenceManager pm = new FilePersistenceManager();
    private Benutzer angemeldeteBenutzer;

    public BenutzerVerwaltung() {
        this.benutzerList = benutzerList;
        this.benutzer = benutzer;
        this.pm = pm;
    }


    public boolean eingelogt(String name, String passwort) throws IOException, BenutzerExistiertBereitsException {
        List<Benutzer> benutzerListe = pm.leseAlleBenutzer();
        for (Benutzer benutzer : benutzerListe) {
            System.out.println(benutzer.getName() + benutzer.getPasswort());
            if (benutzer.getName().equals(name) && benutzer.getPasswort().equals(passwort)) {
                this.angemeldeteBenutzer = benutzer;
                System.err.println("true");
                return true;
            }
        }

        throw new BenutzerExistiertBereitsException(name, passwort);
    }

    public void ausloggen() {
        this.angemeldeteBenutzer = null;
    }

    public boolean istEingeloggt() {
        return this.angemeldeteBenutzer != null;
    }

}













