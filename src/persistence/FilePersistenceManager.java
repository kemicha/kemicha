package src.persistence;

import src.valueObjects.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public  class FilePersistenceManager implements PersistenceManager {

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private BufferedReader reader = null;
    private PrintWriter writer = null;

    public FilePersistenceManager() {
    }

    // ArtikelVerwaltung
    @Override
    public List<Artikel> leseArtikelList(String daten) throws IOException, ArtikelExistiertBereitsException {
        reader = new BufferedReader(new FileReader(daten));
        List<Artikel> ArtikelBestand = new ArrayList<>();
        Artikel artikel;
        do {

            artikel = ladeArtikel();
            if (artikel != null) {
                if (ArtikelBestand.contains(artikel)) {
                    throw new ArtikelExistiertBereitsException(artikel.getArtikelNummer(), artikel.getBezeichnung());
                }

                ArtikelBestand.add(artikel);
            }
        } while (artikel != null);

        return ArtikelBestand;
    }

    private Artikel ladeArtikel() throws IOException {
        String bezeichnung = liesZeile();
        if (bezeichnung == null) {
            return null;
        }
        int artikelNummer;
        try {
            artikelNummer = Integer.parseInt((liesZeile()));
        } catch (NumberFormatException e) {
            return null;
        }

        int bestand;
        try {
            bestand = Integer.parseInt((liesZeile()));
        } catch
        (NumberFormatException e) {
            return null;
        }

        double preis;
        try {
            preis = Double.parseDouble(liesZeile());
        } catch (NumberFormatException e) {
            return null;
        }

        return new Artikel(bezeichnung, artikelNummer, preis, bestand);
    }


    @Override
    public void schreibeArtikelList(List<Artikel> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

        for (Artikel artikel : liste)
            speichereArtikel(artikel);

        writer.close();
    }

    private void speichereArtikel(Artikel artikel) {
        schreibeZeile(artikel.getBezeichnung());
        schreibeZeile(String.valueOf(artikel.getArtikelNummer()));
        schreibeZeile(String.valueOf(artikel.getAnzahl()));
        schreibeZeile(String.valueOf(artikel.getPreis()));
    }



    private String liesZeile() throws IOException {
        if (reader != null)
            return reader.readLine();
        else
            return "";
    }

    private void schreibeZeile(String daten) {
        if (writer != null)
            writer.println(daten);
    }





    // KundeVerwaltung
    @Override
    public List<Kunde> leseKundeListe(String datenquelle) throws IOException  {
        reader= new BufferedReader(new FileReader(datenquelle));
        List<Kunde> kundelist = new ArrayList<>();
        Kunde kunde;
        do {
            kunde = ladeKunde();
            if (kunde != null) {
                if (!kundelist.contains(kunde)) {
                    kundelist.add(kunde);
                }
            }
        } while (kunde != null);

        return kundelist;
    }

    private Kunde ladeKunde() throws IOException {
        String name= liesZeile();
        if (name == null) {
            return null;
        }
         int id;
        try{id= Integer.parseInt((liesZeile()));
        }
        catch   (NumberFormatException e){
            return null;}

        String passwort = liesZeile();
        String adresse = liesZeile();


        return new Kunde( name, id, passwort,adresse);
}

    @Override
    public void schreibeKundeListe(List<Kunde> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

        for(Kunde kunde: liste)
            speicherKunde(kunde);

        writer.close();
    }

    private void speicherKunde(Kunde kunde) {
	  schreibeZeile(kunde.getName());
      schreibeZeile(String.valueOf(kunde.getId()));
      schreibeZeile(String.valueOf(kunde.getPasswort()));
      schreibeZeile(String.valueOf(kunde.getAdresse()));
    }


// Benutzer


    private boolean existierteFile(String weg) {
        File file = new File(weg);
        return file.exists();
    }

    public List<Benutzer> leseAlleBenutzer() throws IOException {
        List<Benutzer> benutzerListe = new ArrayList<>();
        if (existierteFile("_MitarbeiterDB.txt")) {
            benutzerListe.addAll(leseMitarbeiterListe("_MitarbeiterDB.txt"));
        } else {

        }
        if (existierteFile("_KundeDB.txt")) {
            benutzerListe.addAll(leseKundeListe("_KundeDB.txt"));
        } else {

        }
        return benutzerListe;
    }





    // MitarbeiterVerwaltung
    @Override
    public List<Mitarbeiter> leseMitarbeiterListe(String datenquelle) throws IOException {
        reader= new BufferedReader(new FileReader(datenquelle));
        List<Mitarbeiter> mitarbeiterList = new ArrayList<>();
        Mitarbeiter mitarbeiter;
        do {
            mitarbeiter= ladeMitarbeiter();
            if (mitarbeiter != null) {
                if (!mitarbeiterList.contains(mitarbeiter)) {
                    mitarbeiterList.add(mitarbeiter);
                }
            }
        } while (mitarbeiter != null);

        return mitarbeiterList;
    }

    private Mitarbeiter ladeMitarbeiter() throws IOException {
        String name= liesZeile();
        if (name == null) {
            return null;
        }

        int id;
        try{
            id= Integer.parseInt((liesZeile()));
        }catch   (NumberFormatException e){
            return null;}

        String passwort = liesZeile();
        
        return new Mitarbeiter( name,id,passwort);

    }

    @Override
    public void schreibeMitarbeiterListe(List<Mitarbeiter> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

        for(Mitarbeiter mitarbeiter: liste)
            speichereMitarbeiter(mitarbeiter);

        writer.close();
    }





    private void speichereMitarbeiter(Mitarbeiter mitarbeiter) {
        schreibeZeile(mitarbeiter.getName());
        schreibeZeile(String.valueOf(mitarbeiter.getId()));
        schreibeZeile(String.valueOf(mitarbeiter.getPasswort()));
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
                    throw new EreignisExistierBereitsException (ereignis.getArtikel(),ereignis.getMenge());
                }

                ereignisMenge.add(ereignis);
            }
        } while (ereignis != null);

        return ereignisMenge;

    }

    @Override
    public void schreibeInEreignisList(List<Ereignis> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

        for (Ereignis ereignis : liste)
            speichereEreignis(ereignis);

        writer.close();
    }




    private Ereignis ladeEreignis() throws IOException {
            String bezeichnung = liesZeile();
            if (bezeichnung == null) {
                return null;
            }
            int artikelNummer;
            try {
                artikelNummer = Integer.parseInt((liesZeile()));
            } catch (NumberFormatException e) {
                return null;
            }

            int menge;
            try {
                menge = Integer.parseInt((liesZeile()));
            } catch
            (NumberFormatException e) {
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
                dateFormat = DATE_FORMAT.parse(liesZeile());
            } catch (ParseException e) {
                return null;
            }

            Artikel artikel= new Artikel(bezeichnung,artikelNummer,preis,menge) ;

            return new Ereignis(menge, artikel, dateFormat);
        }


        private void speichereEreignis(Ereignis ereignis) {
            schreibeZeile(ereignis.getArtikel().getBezeichnung());
            schreibeZeile(String.valueOf(ereignis.getArtikel().getArtikelNummer()));
            schreibeZeile(String.valueOf(ereignis.getMenge()));
            schreibeZeile(String.valueOf(ereignis.getArtikel().getPreis()));
        }


    }

