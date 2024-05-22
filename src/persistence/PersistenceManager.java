package src.persistence;

import src.valueObjects.Artikel;
import src.valueObjects.Kunde;
import src.valueObjects.Mitarbeiter;

import java.io.IOException;
import java.util.List;

public interface PersistenceManager {

    List<Artikel> leseArtikelList(String datenquelle) throws IOException, ArtikelExistiertBereitsException;
    void schreibeArtikelList(List<Artikel> liste, String datei) throws IOException;

    List<Kunde> leseKunedeListe(String datenquelle) throws IOException;
    void schreibeKundeListe(List<Kunde> liste, String datei) throws IOException;

    List<Mitarbeiter> leseMitarbeiterListe(String datenquelle) throws IOException;
    void schreibeMitarbeiterListe(List<Mitarbeiter> liste, String datei) throws IOException;
}
