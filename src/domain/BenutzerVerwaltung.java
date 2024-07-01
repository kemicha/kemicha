package src.domain;

import src.Exeptions.BenutzerExistiertBereitsException;
import src.persistence.*;
import src.valueObjects.Benutzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BenutzerVerwaltung {
    private List<Benutzer> benutzerListe;
    private PersistenceManager pm;
    private Benutzer angemeldeteBenutzer;

    public BenutzerVerwaltung() {
       /* this.pm = new FilePersistenceManager();
        try {
            this.benutzerListe = pm.leseAlleBenutzer();
        } catch (IOException e) {
            e.printStackTrace();*/
            this.benutzerListe = new ArrayList<>();
        }


    public boolean einloggen(String name, String passwort) throws BenutzerExistiertBereitsException {
        for (Benutzer benutzer : benutzerListe) {
            if (benutzer.getName().equals(name) && benutzer.getPasswort().equals(passwort)) {
                this.angemeldeteBenutzer = benutzer;
                return true;
            }
        }
        return false;
    }

    public void ausloggen() {
        this.angemeldeteBenutzer = null;
    }

    public boolean istEingeloggt() {
        return this.angemeldeteBenutzer != null;
    }

    public Benutzer getAngemeldeteBenutzer() {
        return angemeldeteBenutzer;
    }
}
