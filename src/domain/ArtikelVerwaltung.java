package src.domain;

import src.Exeptions.ArtikelExistiertBereitsException;
import src.persistence.FilePersistenceManager;
import src.persistence.PersistenceManager;
import src.Exeptions.EreignisExistierBereitsException;
import src.valueObjects.*;

import java.io.IOException;
import java.util.*;

public class ArtikelVerwaltung {

    private List<Artikel> artikelList = new ArrayList<>();
    private  PersistenceManager pm = new FilePersistenceManager();
    private List<Warenkorb> warenkorbList = new ArrayList<>();
    private List<Rechnung> rechnungList = new ArrayList<>();
    private List<Ereignis> ereignisList = new ArrayList<>();
    private List<Massengutartikel>massengutartikelListe= new ArrayList<>();

    public ArtikelVerwaltung() throws IOException {

    }

    public void liesDaten(String datei) throws IOException {
        try {
            artikelList = pm.leseArtikelList(datei);
        } catch (ArtikelExistiertBereitsException e) {
            System.err.println("Artikel existiert bereits: " + e.getMessage());
        }
    }

    public void schreibeDaten(String datei) throws IOException {
        pm.schreibeArtikelList(artikelList, datei);
    }

    public List<Artikel> getArtikelBestand() {
        return artikelList;
    }

    public List<Artikel> sucheArtikelNachName(String bezeichnung) {
        List<Artikel> suche = new ArrayList<>();
        for (Artikel artikel : artikelList) {
            if (artikel.getBezeichnung().equals(bezeichnung)) {
                suche.add(artikel);
            }
        }
        return suche;
    }

    public List<Artikel> artikelNachBezeichnung() {
        artikelList.sort(Comparator.comparing(Artikel::getBezeichnung));
        return artikelList;
    }

    public void artikelBestandErhoehen(String bezeichnung, int menge) {
        if (artikelList == null) {
            return;
        }
        Artikel artikel = artikelList.stream()
                .filter(a -> a.getBezeichnung().equals(bezeichnung))
                .findFirst()
                .orElse(null);

        if (artikel != null) {
            artikel.setAnzahl(artikel.getAnzahl() + menge);
        } else {
            System.out.println("Artikel unbekannt!");
        }
    }

    public List<Artikel> artikelNachArtikelnummer() {
        artikelList.sort(Comparator.comparingInt(Artikel::getArtikelNummer));
        return artikelList;
    }

    public void loeschen(int artikelNummer) {
        artikelList.removeIf(artikel -> artikel.getArtikelNummer() == artikelNummer);
    }

    // WarenkorbVerwaltung

    public List<Warenkorb> getWarenkorbList() {
        return warenkorbList;
    }

    public List<Ereignis> getEreignisListe() {
        return ereignisList;
    }

    public void liesEreignisDaten(String datei) throws IOException {
        try {
            ereignisList = pm.leseEreignisList(datei);
        } catch (EreignisExistierBereitsException e) {
            System.err.println("Ereignis existiert bereits: " + e.getMessage());
        }
    }

    public void schreibeDatenInEreignis(String datei) throws IOException {
        pm.schreibeInEreignisList(ereignisList, datei);
    }

    public List<Ereignis> getEreignisList() {
        return ereignisList;
    }

    public List<Warenkorb> getAlleArtikelMengeInwarenkorb() {
        return warenkorbList;
    }

    public void artikelInWarenkorbRein(Artikel artikel, int menge) {
        for (Warenkorb eintrag : warenkorbList) {
            if (eintrag.getArtikel().equals(artikel)) {
                eintrag.setMenge(eintrag.getMenge() + menge);
                ereignisList.add(new Ereignis(menge, artikel, new Date()));
                return;
            }
        }
        warenkorbList.add(new Warenkorb(artikel, menge));
        ereignisList.add(new Ereignis(menge, artikel, new Date()));
    }

    public void fuegeArtikelInWarenkorbEin(String bezeichnung, int menge) {
        Artikel artikel = null;
        for (Artikel a : artikelList) {
            if (a.getBezeichnung().equals(bezeichnung)) {
                artikel = a;
                break;
            }
        }
        if (artikel != null) {
            if (artikel.getAnzahl() >= menge) {
                artikelInWarenkorbRein(artikel, menge);
                artikel.setAnzahl(artikel.getAnzahl() - menge);
                System.out.println("Artikel " + bezeichnung + " wurde in den Warenkorb gelegt.");
            } else {
                System.out.println("Nicht genügend Bestand für den Artikel " + bezeichnung + ".");
            }
        } else {
            System.out.println("Artikel " + bezeichnung + " nicht gefunden.");
        }
    }

    public void aendereMengeImWarenkorb(String bezeichnung, int neueMenge) {
        boolean artikelGefunden = false;

        for (Warenkorb warenkorb : warenkorbList) {
            if (warenkorb.getArtikel().getBezeichnung().equals(bezeichnung)) {
                artikelGefunden = true;

                if (neueMenge > 0) {
                    int differenz = neueMenge - warenkorb.getMenge();

                    if (warenkorb.getArtikel().getAnzahl() >= differenz) {
                        warenkorb.getArtikel().setAnzahl(warenkorb.getArtikel().getAnzahl() - differenz);
                        warenkorb.setMenge(neueMenge);
                        ereignisList.add(new Ereignis(neueMenge, warenkorb.getArtikel(), new Date()));
                        System.out.println("Die Menge des Artikels " + bezeichnung + " wurde auf " + neueMenge + " geändert.");
                    } else {
                        System.out.println("Nicht genügend Bestand für den Artikel " + bezeichnung + ".");
                    }
                } else {
                    warenkorb.getArtikel().setAnzahl(warenkorb.getArtikel().getAnzahl() + warenkorb.getMenge());
                    ereignisList.add(new Ereignis(-warenkorb.getMenge(), warenkorb.getArtikel(), new Date()));
                    warenkorbList.remove(warenkorb);
                    System.out.println("Der Artikel " + bezeichnung + " wurde aus dem Warenkorb entfernt.");
                }
                break;
            }
        }

        if (!artikelGefunden) {
            System.out.println("Artikel " + bezeichnung + " nicht im Warenkorb gefunden.");
        }
    }

    public void kaufeWarenkorb(Benutzer benutzer) {
        if (warenkorbList.isEmpty()) {
            return;
        }

        double gesamtPreis = 0.0;
        for (Warenkorb warenkorb : warenkorbList) {
            Artikel artikel = warenkorb.getArtikel();
            int menge = warenkorb.getMenge();
            gesamtPreis += artikel.getPreis() * menge;
        }
        Rechnung rechnung = new Rechnung(benutzer, new Date(), new ArrayList<>(warenkorbList), gesamtPreis);
        System.out.println(rechnung);
        warenkorbList.clear();
    }

    public void warenkorbLeeren() {
        warenkorbList.clear();
    }





    // Massengut
    public void addMassengutartikel(Massengutartikel artikel) {
        massengutartikelListe.add(artikel);
        System.out.println("Artikel hinzugefügt: " + artikel.getBezeichnung());
    }

    public void removeMassengutartikel(int artikelNummer) {
        massengutartikelListe.removeIf(artikel -> artikel.getArtikelNummer() == artikelNummer);
        System.out.println("Artikel entfernt mit Artikelnummer: " + artikelNummer);
    }

    public Massengutartikel findMassengutartikel(int artikelNummer) {
        for (Massengutartikel artikel : massengutartikelListe) {
            if (artikel.getArtikelNummer() == artikelNummer) {
                return artikel;
            }
        }
        return null;
    }

    public void einlagern(int artikelNummer, int menge) {
        Massengutartikel artikel = findMassengutartikel(artikelNummer);
        if (artikel != null) {
            artikel.einlagern(menge);
            System.out.println("Einlagerung durchgeführt für Artikelnummer: " + artikelNummer + " mit Menge: " + menge);
        } else {
            System.out.println("Artikel nicht gefunden mit Artikelnummer: " + artikelNummer);
        }
    }

    public void auslagern(int artikelNummer, int menge) {
        Massengutartikel artikel = findMassengutartikel(artikelNummer);
        if (artikel != null) {
            artikel.auslagern(menge);
            System.out.println("Auslagerung durchgeführt für Artikelnummer: " + artikelNummer + " mit Menge: " + menge);
        } else {
            System.out.println("Artikel nicht gefunden mit Artikelnummer: " + artikelNummer);
        }
    }

    public void printEreignisse(int artikelNummer) {
        Massengutartikel artikel = findMassengutartikel(artikelNummer);
        if (artikel != null) {
            List<Ereignis> ereignisse = artikel.getEreignisseSortedByDate();
            for (Ereignis ereignis : ereignisse) {
                System.out.println(ereignis);
            }
        } else {
            System.out.println("Artikel nicht gefunden mit Artikelnummer: " + artikelNummer);
        }
    }
}



