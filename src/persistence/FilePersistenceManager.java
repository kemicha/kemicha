package src.persistence;

import src.valueObjects.Artikel;
import src.valueObjects.Kunde;
import src.valueObjects.Mitarbeiter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePersistenceManager implements PersistenceManager {

    private BufferedReader reader = null;
    private PrintWriter writer = null;

    public FilePersistenceManager() throws IOException {
    }

    @Override
    public List<Artikel> leseArtikelList(String daten) throws IOException, ArtikelExistiertBereitsException {
        reader= new BufferedReader(new FileReader(daten));
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
        int artikelNummer = Integer.parseInt(liesZeile());
        int bestand = Integer.parseInt(liesZeile());
        double preis = Integer.parseInt(liesZeile());


        // neues Artikel anlegen und zurückgeben
        return new Artikel(bezeichnung, artikelNummer, preis, bestand);
    }


    @Override
    public void schreibeArtikelList(List<Artikel> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

        for(Artikel artikel: liste)
            speichereArtikel(artikel);

        writer.close();
    }

    private boolean speichereArtikel(Artikel artikel) {
        schreibeZeile(artikel.getBezeichnung());
        schreibeZeile(String.valueOf(artikel.getArtikelNummer()));
        schreibeZeile(String.valueOf(artikel.getPreis()));
        if (artikel.getBestand()!= 0)
            schreibeZeile("t");
        else
            schreibeZeile("f");

        return true;
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






    @Override
    public List<Kunde> leseKunedeListe(String datenquelle) throws IOException {
        return null;
    }

    @Override
    public void schreibeKundeListe(List<Kunde> liste, String datei) throws IOException {

    }

    @Override
    public List<Mitarbeiter> leseMitarbeiterListe(String datenquelle) throws IOException {
        return null;
    }

    @Override
    public void schreibeMitarbeiterListe(List<Mitarbeiter> liste, String datei) throws IOException {

    }
}
