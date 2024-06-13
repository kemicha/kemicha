package src.domain;
import src.persistence.FilePersistenceManager;
import src.Exeptions.MitarbeiterExistiertBereitsException;
import src.persistence.PersistenceManager;
import src.valueObjects.Mitarbeiter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class MitarbeiterVerwaltung {
    private List<Mitarbeiter> mitarbeiterList = new ArrayList<>();

    private PersistenceManager pm = new FilePersistenceManager();
    public MitarbeiterVerwaltung() {
        this.pm = pm;
    }



        public void liesDatenVonMitarbeiter(String datei) throws IOException {
            try {
                mitarbeiterList = pm.leseMitarbeiterListe(datei);
            } catch (MitarbeiterExistiertBereitsException e) {
                System.err.println("Mitarbeiter Liste enthaelt Duplikate und konnte nicht geladen werden: " + e);
            }
        }

        public void schreibeDatenVonMitarbeiter(String datei) throws IOException {
            pm.schreibeMitarbeiterListe(mitarbeiterList, datei);
        }

        public List<Mitarbeiter> getMitarbeiterList() {
            return mitarbeiterList;
        }

        public List<Mitarbeiter> sucheMitarbeiter(String name, int id, String passwort) {
            List<Mitarbeiter> suche = new ArrayList<>();
            Iterator it = getMitarbeiterList().iterator();
            while (it.hasNext()) {
                Mitarbeiter mitarbeiter = (Mitarbeiter) it.next();
                if (mitarbeiter.getName().equals(name) && mitarbeiter.getId() == id && mitarbeiter.getPasswort().equals(passwort)) {
                    suche.add(mitarbeiter);
                }
            }
            return suche;
        }

        public void mitarbeiterLoeschen(int id) {
            mitarbeiterList.removeIf(Mitarbeiter -> Mitarbeiter.getId() == id);
        }
    }




















    /*public MitarbeiterVerwaltung() {
        this.mitarbeiterListe = new ArrayList<>();
        mitarbeiterListe.add(new Mitarbeiter(01, "Shilan", "123","langemarstr 23"));
    }
    public boolean fuegeMitarbeiterHinzu(int id, String name,String passwort, String adresse){
        boolean hinzugefuegt = false;
        int neueId = ermittleNaechsteFreieId();
        if (!istMitarbeiterRegistriert(name)) {
            Mitarbeiter m = new Mitarbeiter(neueId, name, passwort, adresse);
            mitarbeiterListe.add(m);
            hinzugefuegt = true;
        }
        return hinzugefuegt;
    }
    private boolean istMitarbeiterRegistriert(String name) {
        for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
            if (mitarbeiter.getName().equals(name))
                return true;
        }
        return false;
    }
    public List<Mitarbeiter> gibAlleMitarbeiter() {
        return mitarbeiterListe;
    }
    private int ermittleNaechsteFreieId() {
        int maxId = 0;
        for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
            if (mitarbeiter.getId() > maxId) {
                maxId = mitarbeiter.getId();
            }
        }
        return maxId + 1;
    }*/


