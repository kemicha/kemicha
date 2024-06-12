package src.domain;

import src.persistence.FilePersistenceManager;
import src.persistence.KundeExistiertBereitsException;
import src.persistence.PersistenceManager;
import src.valueObjects.*;

import java.io.IOException;
import java.util.*;

public class KundeVerwaltung {

    private List<Kunde> kundeList = new ArrayList<>();
    private List<Benutzer> benutzerList = new ArrayList<>();
    private List<Ereignis> ereignisList = new ArrayList<>();
    private PersistenceManager pm;

    public KundeVerwaltung() {
        this.pm = new FilePersistenceManager();
    }


    public Kunde kundeRegistrierung(int id, String name, String passwort, String adresse) {
        for (Kunde kunde : kundeList) {
            if (kunde.getName().equals(name)) {
                return null;
            }
        }
        Kunde neuerKunde = new Kunde( name,id, passwort, adresse);
        kundeList.add(neuerKunde);
        return neuerKunde;
    }

        public void liesDatenVonKunde(String datei) throws IOException {
            try {
                kundeList = pm.leseKundeListe(datei);
            } catch (KundeExistiertBereitsException e) {
                System.err.println("kundeliste enthaelt Duplikate und konnte nicht geladen werden: " + e);
            }
        }

        public void schreibeDatenVonKunde(String datei) throws IOException {
            pm.schreibeKundeListe(kundeList, datei);
        }

        public List<Kunde> sucheKunde(String name, int id, String passwort, String adresse) {
            List<Kunde> suche = new ArrayList<>();
            Iterator it = getKundeList().iterator();
            while (it.hasNext()) {
                Kunde kunde = (Kunde) it.next();
                if (kunde.getName().equals(name) && kunde.getId() == id && kunde.getPasswort().equals(passwort)
                        && kunde.getAdresse().equals(adresse)) {
                    suche.add(kunde);
                }
            }
            return suche;
        }

        public List<Kunde> getKundeList() {
            return kundeList;
        }

    public void kundeLoeschen(int id) {
        kundeList.removeIf(Kunde -> Kunde.getId() == id);
    }
    }



















    /*  private ArtikelVerwaltung artikels ;
    private List<Kunde> kundeList;
    private List<Artikel> artikelList;
    private Map<Artikel,Integer> artikelMap;
    private List <Rechnung> rechnung;
    private Map<Artikel, Integer> warenkorb;

    public KundeVerwaltung() {
        this.kundeList = new ArrayList<>();
        this.artikelList = new ArrayList<>();
        this.rechnung = new ArrayList<>();
        this.warenkorb = new HashMap<>();
        this.artikelMap = new HashMap<>();
    }

    public Kunde kundeRegistrierung(int id, String name, String passwort, String adresse) {
        for (Kunde kunde : kundeList) {
            if (kunde.getName().equals(name)) {
                return null;
            }
        }
        Kunde neuerKunde = new Kunde(id, name, passwort, adresse);
        kundeList.add(neuerKunde);
        return neuerKunde;
    }

    public void artikelInWarenkorbHinzufuegen(Artikel artikel, int menge) {
        if (artikelMap.containsKey(artikel)) {
            int vorhandeneMenge = artikelMap.get(artikel);
            artikelMap.put(artikel, vorhandeneMenge + menge);
        } else {
            artikelMap.put(artikel, menge);
        }
    }

    public String warenkorbInhaltAnzeigen() {
        if (artikelMap.isEmpty()) {
            return "Warenkorb ist leer.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Warenkorbinhalt:\n");
            for (Map.Entry<Artikel, Integer> entry : artikelMap.entrySet()) {
                Artikel artikel = entry.getKey();
                int menge = entry.getValue();
                sb.append(artikel.toString()).append(" - Menge: ").append(menge).append("\n");
            }
            return sb.toString();
        }
    }

    public void artikelMengeAendern(Artikel artikel, int neueMenge) {
        if (warenkorb.containsKey(artikel)) {
            int mengeAlt = warenkorb.get(artikel);
            warenkorb.remove(artikel);
            warenkorb.put(artikel, neueMenge);
        } else {
            warenkorb.put(artikel, neueMenge);
        }
    }

    public void warenkorbLeeren() {
        artikelList.clear();
    }

    public boolean artikelAusWarenkorbKaufen() {
        if (artikelList.isEmpty()) {
            System.out.println("Der Warenkorb ist leer. Bitte fügen Sie Artikel hinzu, bevor Sie kaufen.");
            return false;
        } else {
            double gesamtPreis = 0.0;
            Kunde kunde = null;
            Rechnung rechnung = new Rechnung(kunde, LocalDate.now(), artikelList);

            for (Artikel artikel : artikelList) {
                gesamtPreis += artikel.getPreis() * artikel.getBestand();
            }
            rechnung.setGesamtePreis(gesamtPreis);

            String ereignisBeschreibung = "Artikel wurden aus dem Warenkorb gekauft";
            int anzahl = artikelList.size();
            Mitarbeiter mitarbeiter = null;
            rechnung.ereignisEinzeigen(ereignisBeschreibung, anzahl, mitarbeiter);

            artikelList.clear();

            return true;
        }
    }

    public void ereignisseBehandeln() {
        // Implementiere die Logik zum Behandeln von Ereignissen im Warenkorb
    }*/

