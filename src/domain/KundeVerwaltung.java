package src.domain;

import src.valueObjects.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KundeVerwaltung {
    private ArtikelVerwaltung artikels ;
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
    }
}
