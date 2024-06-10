package src.domain;

import src.persistence.ArtikelExistiertBereitsException;
import src.persistence.FilePersistenceManager;
import src.persistence.PersistenceManager;
import src.persistence.WarenkorbExistierBereitsException;
import src.valueObjects.*;
import src.valueObjects.Warenkorb;

import java.io.IOException;
import java.util.*;

public class ArtikelVerwaltung {


    private List <Artikel> artikelList = new ArrayList<>();
    private PersistenceManager pm = new FilePersistenceManager();
    private List<Warenkorb> warenkorbList = new ArrayList<>();
    private List<Rechnung> rechnung = new ArrayList<>();
    private List<Ereignis> ereignisList = new ArrayList<>();


    public ArtikelVerwaltung() throws IOException {
        this.warenkorbList = warenkorbList;
        this.rechnung = rechnung;
        this.ereignisList = ereignisList;

    }


    public void liesDaten(String datei) throws IOException {
        try {
            artikelList = pm.leseArtikelList(datei);
        } catch (ArtikelExistiertBereitsException e) {
        }
    }

    public void schreibeDaten(String datei) throws IOException {
        pm.schreibeArtikelList(artikelList, datei);
    }

    public List<Artikel> getArtikelBestand() {
        return artikelList;
    }

    public List<Artikel> artikelList() {
        return artikelList;
    }


    public List<Artikel> sucheArtikelNachName(String bezeichnung) {
        List<Artikel> suche = new ArrayList<>();
        Iterator it = getArtikelBestand().iterator();
        while (it.hasNext()) {
            Artikel artikel = (Artikel) it.next();
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
            artikel.setBestand(artikel.getBestand() + menge);

        } else {
            System.out.println("Artikel unbekannt!");
            return;
        }
    }

    public List<Artikel> artikelNachArtikelnummer() {
        artikelList.sort(Comparator.comparingInt(Artikel::getArtikelNummer));
        return artikelList;
    }

/*    public List<Artikel> sucheArtikelNachNummer(String artikelNummer) {
        List<Artikel> suche = new ArrayList<>();
        Iterator it = getBestand().iterator();
        while (it.hasNext()) {
            Artikel artikel = (Artikel) it.next();
            if (artikel.getArtikelNummer().equals(artikelNummer)) {
                suche.add(artikel);
            }
        }
        return suche;
    }*/


    public void loeschen(int artikelNummer) {
        artikelList.removeIf(Artikel -> Artikel.getArtikelNummer() == artikelNummer);
    }


// WarenkorbVerwaltung


    public List<Warenkorb> getWarenkorbList() {
        return warenkorbList;
    }

    public List<Ereignis> getEreignisListe() {
        return ereignisList;
    }

    public void liesWarenkorbDaten(String datei) throws IOException {
        try {
            warenkorbList = pm.leseWarenkorbList(datei);
        } catch (WarenkorbExistierBereitsException e) {
        }
    }

    public void schreibeDatenInWarenkorb(String datei) throws IOException {
        pm.schreibeInWarenkorblList(warenkorbList, datei);
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
            if (artikel.getBestand() >= menge) {
                artikelInWarenkorbRein(artikel, menge);
                artikel.setBestand(artikel.getBestand() - menge);
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

                    if (warenkorb.getArtikel().getBestand() >= differenz) {
                        warenkorb.getArtikel().setBestand(warenkorb.getArtikel().getBestand() - differenz);
                        warenkorb.setMenge(neueMenge);
                        ereignisList.add(new Ereignis(neueMenge, warenkorb.getArtikel(), new Date()));
                        System.out.println("Die Menge des Artikels " + bezeichnung + " wurde auf " + neueMenge + " geändert.");
                    } else {
                        System.out.println("Nicht genügend Bestand für den Artikel " + bezeichnung + ".");
                    }
                } else {
                    warenkorb.getArtikel().setBestand(warenkorb.getArtikel().getBestand() + warenkorb.getMenge());
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
            System.out.println("Der Warenkorb ist leer.");
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

    }


