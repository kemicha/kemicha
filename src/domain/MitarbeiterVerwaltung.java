package src.domain;
import src.valueObjects.Mitarbeiter;
import java.util.ArrayList;
import java.util.List;
public class MitarbeiterVerwaltung {
    private List<Mitarbeiter> mitarbeiterListe;
    public MitarbeiterVerwaltung() {
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
    }
}

