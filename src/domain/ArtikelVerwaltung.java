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
    private List<Massengut>massengutartikelListe= new ArrayList<>();

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
            ereignisList.add(new Ereignis("Mitarbeiter",0, artikel,menge,new Date(),"bestand erhöhen"));
        } else {
            System.out.println("Artikel unbekannt!");
            return;
        }
    }



    public List<Artikel> artikelNachArtikelnummer() {
        artikelList.sort(Comparator.comparingInt(Artikel::getArtikelNummer));
        return artikelList;
    }

    public boolean loeschen(int artikelNummer) {
        return artikelList.removeIf(artikel -> artikel.getArtikelNummer() == artikelNummer);
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
                return;
            }
        }
        warenkorbList.add(new Warenkorb(artikel, menge));

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

    public boolean aendereMengeImWarenkorb(String bezeichnung, int neueMenge) {
        boolean artikelGefunden = false;

        for (Warenkorb warenkorb : warenkorbList) {
            if (warenkorb.getArtikel().getBezeichnung().equals(bezeichnung)) {
                artikelGefunden = true;

                if (neueMenge > 0) {
                    int differenz = neueMenge - warenkorb.getMenge();

                    if (warenkorb.getArtikel().getAnzahl() >= differenz) {
                        warenkorb.getArtikel().setAnzahl(warenkorb.getArtikel().getAnzahl() - differenz);
                        warenkorb.setMenge(neueMenge);

                        System.out.println("Die Menge des Artikels " + bezeichnung + " wurde auf " + neueMenge + " geändert.");
                    } else {
                        System.out.println("Nicht genügend Bestand für den Artikel " + bezeichnung + ".");
                    }
                } else {
                    warenkorb.getArtikel().setAnzahl(warenkorb.getArtikel().getAnzahl() + warenkorb.getMenge());

                    warenkorbList.remove(warenkorb);
                    System.out.println("Der Artikel " + bezeichnung + " wurde aus dem Warenkorb entfernt.");
                }
                break;
            }
        }

        if (!artikelGefunden) {
            System.out.println("Artikel " + bezeichnung + " nicht im Warenkorb gefunden.");
        }
        return artikelGefunden;
    }

    public void kaufeWarenkorb(Benutzer benutzer) {
        if (warenkorbList.isEmpty()) {
            return;
        }
        Rechnung rechnung =null;

        double gesamtPreis = 0.0;
        for (Warenkorb warenkorb : warenkorbList) {
            Artikel artikel = warenkorb.getArtikel();
            int menge = warenkorb.getMenge();
            gesamtPreis += artikel.getPreis() * menge;
            rechnung = new Rechnung(benutzer, new Date(),  gesamtPreis,menge, artikel.getBezeichnung());

        }

        System.out.println(rechnung);
        warenkorbList.clear();
    }

    public void warenkorbLeeren() {
        warenkorbList.clear();
    }

// Rechnung

    public List<Rechnung> getRechnungList(){
        return rechnungList;
    }



    // Massengut
    public void addMassengutartikel(Massengut artikel) {
        massengutartikelListe.add(artikel);

    }

    public void removeMassengutartikel(int artikelNummer) {
        massengutartikelListe.removeIf(artikel -> artikel.getArtikelNummer() == artikelNummer);
        System.out.println("Artikel entfernt mit Artikelnummer: " + artikelNummer);
    }

    public Massengut findMassengutartikel(int artikelNummer) {
        for (Massengut artikel : massengutartikelListe) {
            if (artikel.getArtikelNummer() == artikelNummer) {
                return artikel;
            }
        }
        return null;
    }

    public void einlagern(Artikel artikelNummer, int menge) {
        Massengut artikel = findMassengutartikel(artikelNummer.getArtikelNummer());
        if (artikel != null) {
            artikel.einlagern(artikel);
            System.out.println("Einlagerung durchgeführt für Artikelnummer: " + artikelNummer + " mit Menge: " + menge);
        } else {
            System.out.println("Artikel nicht gefunden mit Artikelnummer: " + artikelNummer);
        }
    }

    public void auslagern(int artikelNummer, int menge) {
        Massengut artikel = findMassengutartikel(artikelNummer);
        if (artikel != null) {
            artikel.auslagern(artikel);
            System.out.println("Auslagerung durchgeführt für Artikelnummer: " + artikelNummer + " mit Menge: " + menge);
        } else {
            System.out.println("Artikel nicht gefunden mit Artikelnummer: " + artikelNummer);
        }
    }

    public void printEreignisse(int artikelNummer) {
        Massengut artikel = findMassengutartikel(artikelNummer);
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



