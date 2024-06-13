package src.persistence;

import src.Exeptions.ArtikelExistiertBereitsException;
import src.Exeptions.EreignisExistierBereitsException;
import src.Exeptions.KundeExistiertBereitsException;
import src.Exeptions.MitarbeiterExistiertBereitsException;
import src.valueObjects.*;

import java.io.IOException;
import java.util.List;

public interface PersistenceManager {

    List<Artikel> leseArtikelList(String datenquelle) throws IOException, ArtikelExistiertBereitsException;
    void schreibeArtikelList(List<Artikel> liste, String datei) throws IOException;

    List<Kunde> leseKundeListe(String datenquelle) throws IOException, KundeExistiertBereitsException;
    void schreibeKundeListe(List<Kunde> liste, String datei) throws IOException;

    List<Mitarbeiter> leseMitarbeiterListe(String datenquelle) throws IOException, MitarbeiterExistiertBereitsException;
    void schreibeMitarbeiterListe(List<Mitarbeiter> liste, String datei) throws IOException;

    List<Ereignis> leseEreignisList(String datenquelle) throws IOException, EreignisExistierBereitsException;


    void schreibeInEreignisList(List<Ereignis> liste, String datei) throws IOException;


    List<Benutzer> leseAlleBenutzer() throws IOException;

}
