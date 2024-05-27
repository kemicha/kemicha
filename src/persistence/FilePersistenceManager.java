package src.persistence;

import src.valueObjects.Artikel;
import src.valueObjects.Kunde;
import src.valueObjects.Mitarbeiter;
import src.valueObjects.Warenkorb;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePersistenceManager implements PersistenceManager {

    private BufferedReader reader = null;
    private PrintWriter writer = null;

    public FilePersistenceManager() throws IOException {
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

    private boolean speichereArtikel(Artikel artikel) {
        schreibeZeile(artikel.getBezeichnung());
        schreibeZeile(String.valueOf(artikel.getArtikelNummer()));
        schreibeZeile(String.valueOf(artikel.getBestand()));
        schreibeZeile(String.valueOf(artikel.getPreis()));
        if (artikel.getBestand() != 0)

            return true;
        return false;
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
    public List<Kunde> leseKunedeListe(String datenquelle) throws IOException,KundeExistiertBereitsException  {
        reader= new BufferedReader(new FileReader(datenquelle));
        List<Kunde> kundelist = new ArrayList<>();
        Kunde kunde;
        do {
            kunde = ladeKunde();
            if (kunde != null) {
                if (kundelist.contains(kunde)) {
                    throw new KundeExistiertBereitsException(kunde.getName(), kunde.getId());
                }
                kundelist.add(kunde);
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

    private boolean speicherKunde(Kunde kunde) {
	  schreibeZeile(kunde.getName());
      schreibeZeile(String.valueOf(kunde.getId()));
      schreibeZeile(String.valueOf(kunde.getPasswort()));
      schreibeZeile(String.valueOf(kunde.getAdresse()));
                  return true;
    }





      // MitarbeiterVerwaltung
    @Override
    public List<Mitarbeiter> leseMitarbeiterListe(String datenquelle) throws IOException, MitarbeiterExistiertBereitsException {
        reader= new BufferedReader(new FileReader(datenquelle));
        List<Mitarbeiter> mitarbeiterList = new ArrayList<>();
        Mitarbeiter mitarbeiter;
        do {
            mitarbeiter= ladeMitarbeiter();
            if (mitarbeiter != null) {
                if (mitarbeiterList.contains(mitarbeiter)) {
                    throw new MitarbeiterExistiertBereitsException(mitarbeiter.getName(), mitarbeiter.getId());
                }
                mitarbeiterList.add(mitarbeiter);
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

    private boolean speichereMitarbeiter(Mitarbeiter mitarbeiter) throws IOException{
        schreibeZeile(mitarbeiter.getName());
        schreibeZeile(String.valueOf(mitarbeiter.getId()));
        schreibeZeile(String.valueOf(mitarbeiter.getPasswort()));
        return true;
    }







    // Warenkorb

    @Override
    public List<Warenkorb> leseWarenkorbList(String datenquelle) throws IOException, WarenkorbExistierBereitsException {
        reader = new BufferedReader(new FileReader(datenquelle));
        List<Warenkorb> warenkorbMenge = new ArrayList<>();
        Warenkorb warenkorb;
        do {

            warenkorb = ladeWarenkorb();
            if (warenkorb != null) {
                if (warenkorbMenge.contains(warenkorb)) {
                    throw new WarenkorbExistierBereitsException (warenkorb.getArtikel().getArtikelNummer(),warenkorb. getArtikel().getBezeichnung());
                }

                warenkorbMenge.add(warenkorb);
            }
        } while (warenkorb != null);

        return warenkorbMenge;

    }

    @Override
    public void schreibeInWarenkorblList(List<Warenkorb> liste, String datei) throws IOException {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

            for (Warenkorb warenkorb : liste)
                speichereWarenkorb(warenkorb);

            writer.close();
        }


        private Warenkorb ladeWarenkorb() throws IOException {
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

            Artikel artikel= new Artikel(bezeichnung,artikelNummer,preis,bestand);

            return new Warenkorb(artikel,bestand);
        }


        private boolean speichereWarenkorb(Warenkorb warenkorb) {
            schreibeZeile(warenkorb.getArtikel().getBezeichnung());
            schreibeZeile(String.valueOf(warenkorb.getArtikel().getArtikelNummer()));
            schreibeZeile(String.valueOf(warenkorb.getMenge()));
            schreibeZeile(String.valueOf(warenkorb.getArtikel().getPreis()));
            if (warenkorb.getMenge() != 0)

                return true;
            return false;
        }














    }

