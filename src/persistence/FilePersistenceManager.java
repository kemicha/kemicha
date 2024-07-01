package src.persistence;

import src.Exeptions.ArtikelExistiertBereitsException;
import src.Exeptions.EreignisExistierBereitsException;
import src.valueObjects.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilePersistenceManager implements PersistenceManager {

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private BufferedReader reader = null;
    private PrintWriter writer = null;

    public FilePersistenceManager() {
    }

    // ArtikelVerwaltung
    @Override
    public List<Artikel> leseArtikelList(String daten) throws IOException, ArtikelExistiertBereitsException {
        reader = new BufferedReader(new FileReader(daten));
        List<Artikel> artikelBestand = new ArrayList<>();
        Artikel artikel;
        do {
            artikel = ladeArtikel();
            if (artikel != null) {
                if (artikelBestand.contains(artikel)) {
                    throw new ArtikelExistiertBereitsException(artikel.getArtikelNummer(), artikel.getBezeichnung());
                }
                artikelBestand.add(artikel);
            }
        } while (artikel != null);
        reader.close(); // Ensure the reader is closed
        return artikelBestand;
    }

    private Artikel ladeArtikel() throws IOException {
        String bezeichnung = liesZeile();
        if (bezeichnung == null) {
            return null;
        }
        int artikelNummer;
        try {
            artikelNummer = Integer.parseInt(liesZeile());
        } catch (NumberFormatException e) {
            return null;
        }
        int bestand;
        try {
            bestand = Integer.parseInt(liesZeile());
        } catch (NumberFormatException e) {
            return null;
        }
        double preis;
        try {
            preis = Double.parseDouble(liesZeile());
        } catch (NumberFormatException e) {
            return null;
        }
        return new Artikel(bezeichnung, artikelNummer,  bestand,preis);
    }

    @Override
    public void schreibeArtikelList(List<Artikel> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
        for (Artikel artikel : liste) {
            speichereArtikel(artikel);
        }
        writer.close(); // Ensure the writer is closed
    }

    private void speichereArtikel(Artikel artikel) {
        schreibeZeile(artikel.getBezeichnung());
        schreibeZeile(String.valueOf(artikel.getArtikelNummer()));
        schreibeZeile(String.valueOf(artikel.getAnzahl()));
        schreibeZeile(String.valueOf(artikel.getPreis()));
    }

    private String liesZeile() throws IOException {
        return reader != null ? reader.readLine() : "";
    }

    private void schreibeZeile(String daten) {
        if (writer != null) {
            writer.println(daten);
        }
    }

    // KundeVerwaltung
    @Override
    public List<Kunde> leseKundeListe(String datenquelle) throws IOException {
        reader = new BufferedReader(new FileReader(datenquelle));
        List<Kunde> kundelist = new ArrayList<>();
        Kunde kunde;
        do {
            kunde = ladeKunde();
            if (kunde != null && !kundelist.contains(kunde)) {
                kundelist.add(kunde);
            }
        } while (kunde != null);
        reader.close();
        return kundelist;
    }

    private Kunde ladeKunde() throws IOException {
        String name = liesZeile();
        if (name == null) {
            return null;
        }
        int id;
        try {
            id = Integer.parseInt(liesZeile());
        } catch (NumberFormatException e) {
            return null;
        }
        String passwort = liesZeile();
        String adresse = liesZeile();
        return new Kunde(name, id, passwort, adresse);
    }

    @Override
    public void schreibeKundeListe(List<Kunde> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
        for (Kunde kunde : liste) {
            speicherKunde(kunde);
        }
        writer.close();
    }

    private void speicherKunde(Kunde kunde) {
        schreibeZeile(kunde.getName());
        schreibeZeile(String.valueOf(kunde.getId()));
        schreibeZeile(kunde.getPasswort());
        schreibeZeile(kunde.getAdresse());
    }

    // Benutzer
    private boolean existierteFile(String weg) {
        return new File(weg).exists();
    }

    @Override
    public List<Benutzer> leseAlleBenutzer() throws IOException {
        List<Benutzer> benutzerListe = new ArrayList<>();
        if (existierteFile("Eshop_MitarbeiterDB.txt")) {
            benutzerListe.addAll(leseMitarbeiterListe("Eshop_MitarbeiterDB.txt"));
        }
        if (existierteFile("Eshop_KundeDB.txt")) {
            benutzerListe.addAll(leseKundeListe("Eshop_KundeDB.txt"));
        }
        return benutzerListe;
    }

    // MitarbeiterVerwaltung
    @Override
    public List<Mitarbeiter> leseMitarbeiterListe(String datenquelle) throws IOException {
        reader = new BufferedReader(new FileReader(datenquelle));
        List<Mitarbeiter> mitarbeiterList = new ArrayList<>();
        Mitarbeiter mitarbeiter;
        do {
            mitarbeiter = ladeMitarbeiter();
            if (mitarbeiter != null && !mitarbeiterList.contains(mitarbeiter)) {
                mitarbeiterList.add(mitarbeiter);
            }
        } while (mitarbeiter != null);
        reader.close(); // Ensure the reader is closed
        return mitarbeiterList;
    }

    private Mitarbeiter ladeMitarbeiter() throws IOException {
        String name = liesZeile();
        if (name == null) {
            return null;
        }
        int id;
        try {
            id = Integer.parseInt(liesZeile());
        } catch (NumberFormatException e) {
            return null;
        }
        String passwort = liesZeile();
        return new Mitarbeiter(name, id, passwort);
    }

    @Override
    public void schreibeMitarbeiterListe(List<Mitarbeiter> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
        for (Mitarbeiter mitarbeiter : liste) {
            speichereMitarbeiter(mitarbeiter);
        }
        writer.close(); // Ensure the writer is closed
    }

    private void speichereMitarbeiter(Mitarbeiter mitarbeiter) {
        schreibeZeile(mitarbeiter.getName());
        schreibeZeile(String.valueOf(mitarbeiter.getId()));
        schreibeZeile(mitarbeiter.getPasswort());
    }

    // Ereignis
    @Override
    public List<Ereignis> leseEreignisList(String datenquelle) throws IOException, EreignisExistierBereitsException {
        reader = new BufferedReader(new FileReader(datenquelle));
        List<Ereignis> ereignisMenge = new ArrayList<>();
        Ereignis ereignis;
        do {
            ereignis = ladeEreignis();
            if (ereignis != null) {
                if (ereignisMenge.contains(ereignis)) {
                    throw new EreignisExistierBereitsException(ereignis.getArtikel(), ereignis.getMenge());
                }
                ereignisMenge.add(ereignis);
            }
        } while (ereignis != null);
        reader.close(); // Ensure the reader is closed
        return ereignisMenge;
    }

    @Override
    public void schreibeInEreignisList(List<Ereignis> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
        for (Ereignis ereignis : liste) {
            speichereEreignis(ereignis);
        }
        writer.close(); // Ensure the writer is closed
    }

    private Ereignis ladeEreignis() throws IOException {
        String bezeichnung = liesZeile();
        if (bezeichnung == null) {
            return null;
        }
        int artikelNummer;
        try {
            artikelNummer = Integer.parseInt(liesZeile());
        } catch (NumberFormatException e) {
            return null;
        }
        int menge;
        try {
            menge = Integer.parseInt(liesZeile());
        } catch (NumberFormatException e) {
            return null;
        }
        double preis;
        try {
            preis = Double.parseDouble(liesZeile());
        } catch (NumberFormatException e) {
            return null;
        }
        Date dateFormat;
        try {
            String textZeileAusDAtei = liesZeile();
            if (textZeileAusDAtei == null) {
                return null;
            }
            dateFormat = DATE_FORMAT.parse(textZeileAusDAtei);
        } catch (ParseException e) {
            return null;
        }

        Artikel artikel = new Artikel(bezeichnung, artikelNummer,  menge,preis);
        return new Ereignis("", 1, artikel, 1, dateFormat, "");
    }

    private void speichereEreignis(Ereignis ereignis) {
        schreibeZeile(ereignis.getArtikel().getBezeichnung());
        schreibeZeile(String.valueOf(ereignis.getArtikel().getArtikelNummer()));
        schreibeZeile(String.valueOf(ereignis.getMenge()));
        schreibeZeile(DATE_FORMAT.format(ereignis.getDateFormat()));
    }
}