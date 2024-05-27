package src.persistence;

import src.valueObjects.Artikel;
import src.valueObjects.Kunde;
import src.valueObjects.Mitarbeiter;
import src.valueObjects.Warenkorb;

import java.io.IOException;
import java.util.List;

public interface PersistenceManager {

    List<Artikel> leseArtikelList(String datenquelle) throws IOException, ArtikelExistiertBereitsException;
    void schreibeArtikelList(List<Artikel> liste, String datei) throws IOException;

    List<Kunde> leseKunedeListe(String datenquelle) throws IOException, KundeExistiertBereitsException;
    void schreibeKundeListe(List<Kunde> liste, String datei) throws IOException;

    List<Mitarbeiter> leseMitarbeiterListe(String datenquelle) throws IOException, MitarbeiterExistiertBereitsException;
    void schreibeMitarbeiterListe(List<Mitarbeiter> liste, String datei) throws IOException;

    List<Warenkorb> leseWarenkorbList(String datenquelle) throws IOException,WarenkorbExistierBereitsException;

    void schreibeInWarenkorblList(List<Warenkorb> liste, String datei) throws IOException;
}
