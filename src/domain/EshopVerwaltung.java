package src.domain;


import src.Exeptions.ArtikelExistiertBereitsException;
import src.Exeptions.BenutzerExistiertBereitsException;
import src.Exeptions.KundeExistiertBereitsException;
import src.Exeptions.MitarbeiterExistiertBereitsException;
import src.valueObjects.*;

import java.io.IOException;
import java.util.List;

public class EshopVerwaltung {

    private BenutzerVerwaltung bv;
    private String datei;
    private ArtikelVerwaltung av;
    private MitarbeiterVerwaltung mv;
    private KundeVerwaltung kv;




    public  EshopVerwaltung(String datei) throws IOException {
        this.datei = datei;
        av = new ArtikelVerwaltung();
        av.liesDaten(datei + "_ArtikelDB.txt");

        av.liesEreignisDaten(datei + "_Ereignis.txt");
        kv = new KundeVerwaltung();

        kv.liesDatenVonKunde(datei + "_KundeDB.txt ");
        mv = new MitarbeiterVerwaltung();

        mv.liesDatenVonMitarbeiter(datei + "_MitarbeiterDB.txt");
        bv = new BenutzerVerwaltung();

    }



    public void speicherDaten() throws IOException {
        av.schreibeDaten(datei + "_ArtikelDB.txt");
        av.schreibeDatenInEreignis(datei + "_Ereignis.txt");
        kv.schreibeDatenVonKunde(datei + "_KundeDB.txt");
        mv.schreibeDatenVonMitarbeiter(datei + "_MitarbeiterDB.txt");
    }

    // ArtikelVerwaltung

   /* public void speicherArtikel() throws IOException {
        av.schreibeDaten(datei + "_ArtikelDB.txt");
    }*/

    public List<Artikel> gibAlleArtikel() {
        return av.getArtikelBestand();
    }

    public Artikel fuegeArtikelEin(String bezeichnung, int artikelNummer, int bestand, double preis) throws ArtikelExistiertBereitsException {
        if (!av.sucheArtikelNachName(bezeichnung).isEmpty()) {
            throw new ArtikelExistiertBereitsException(artikelNummer, bezeichnung);
        }

        Artikel artikel = new Artikel(bezeichnung, artikelNummer, bestand, (int) preis);
        av.getArtikelBestand().add(artikel);
        return artikel;
    }

    public void loescheArtikel(int artikelNummer) {
        av.loeschen(artikelNummer);
    }

    public List<Artikel> sortiereArtikelNachNummer() {
        return av.artikelNachArtikelnummer();
    }

    public List<Artikel> sortiereArtikelNachName() {
        return av.artikelNachBezeichnung();

    }

    public void ehöhenArtikel(String bezeichnung, int menge) {
        av.artikelBestandErhoehen(bezeichnung, menge);
    }


    // KundeVerwaltung

    public List<Warenkorb> getWarenkorbList() {
        return av.getWarenkorbList();

    }

    public List<Kunde> gibAlleKunden() {
        return kv.getKundeList();
    }

    public void NeueKunde(String name, int id, String passwort, String adresse) throws KundeExistiertBereitsException {
        if (!kv.sucheKunde(name, id, passwort, adresse).isEmpty()) {
            throw new KundeExistiertBereitsException(name,passwort, id,adresse);
        }
        Kunde kunde = new Kunde(name, id, passwort, adresse);
        kv.getKundeList().add(kunde);

    }

    /*public void speicherKunden() throws IOException {
        kv.schreibeDatenVonKunde(datei + "_KundeDB.txt");
    }
*/
    public void loescheKunde(int id) {
        kv.kundeLoeschen(id);
    }


    // MitarbeiterVerwaltung
    public List<Mitarbeiter> gibAlleMitarbeiter() {
        return mv.getMitarbeiterList();
    }

    public void NeueMitarbeiter(String name, int id, String passwort) throws MitarbeiterExistiertBereitsException {
        if (!mv.sucheMitarbeiter(name, id, passwort).isEmpty()) {
            throw new MitarbeiterExistiertBereitsException(name, passwort,id);
        }
        Mitarbeiter mitarbeiter = new Mitarbeiter(name, id, passwort);
        mv.getMitarbeiterList().add(mitarbeiter);

    }

   /* public void speicherMitarbeiter() throws IOException {
        mv.schreibeDatenVonMitarbeiter(datei + "_MitarbeiterDB.txt");
    }*/

    public void loescheMitabeiter(int id) {
        mv.mitarbeiterLoeschen(id);
    }



//Benutzer
public boolean login(String username, String password) {
    try {
        boolean success = bv.einloggen(username, password);
        if (success) {
            System.out.println("Erfolreich Eingelogt: " + bv.getAngemeldeteBenutzer().getName());
        } else {

        }
    } catch (BenutzerExistiertBereitsException e) {
        System.err.println("Fehler Beim Einloggen : " + e.getMessage());
    }
    return false;
}

    public void logout() {
        bv.ausloggen();
        System.out.println("Benutzer Ausgeloggt.");
    }

    public boolean isLoggedIn() {
        return bv.istEingeloggt();
    }


// WarenkorbVerwaltung

    public void speicherEreignis() throws IOException {
        av.schreibeDatenInEreignis(datei + "_Ereignis.txt");
    }

    public List<Warenkorb> gibAlleArtikelInWarenkorb() {
        return av.getAlleArtikelMengeInwarenkorb();
    }

    public void FuegeArtikelInWarenkorb(String bezeichnung, int menge) {
        av.fuegeArtikelInWarenkorbEin(bezeichnung, menge);
    }

    public boolean aenderungImWarenkorb(String bezeichnung, int neueMenge) {
        av.aendereMengeImWarenkorb(bezeichnung, neueMenge);
        return false;
    }
      /*  {
            throw new WarenkorbExistierBereitsException(artikel, menge);
        }
        Warenkorb warenkorb = new Warenkorb(bezeichnung, menge);
        av.getAlleArtikelMengeInwarenkorb().add(warenkorb);
    }*/

    public Artikel getArtikelByName(String bezeichnung) {
        for (Artikel artikel : av.getArtikelBestand()) {
            if (artikel.getBezeichnung().equals(bezeichnung)) {
                return artikel;


            }
        }
        return null;
    }

    public Artikel getArtikelByNummer(int artikelNummer) {
        for (Artikel artikel : av.getArtikelBestand()) {
            if (artikel.getArtikelNummer() == artikelNummer) {
                return artikel;
            }
        }
        return null;
    }


   /* public boolean kaufen(Benutzer benutzer) {
        av.gekauftArtikel(benutzer);
        return false;
    }*/

    public Kunde findKundeByName(String kundenName) {
        return (Kunde) kv.getKundeList();
    }

    public void warenkorbLeeren() {
        av.warenkorbLeeren();
    }

    public void kaufenArtikel(Benutzer benutzer) {
        av.kaufeWarenkorb(benutzer);
    }

    public Benutzer getBenutzerByName(String benutzerName) {
        return (Benutzer) kv.getKundeList();
    }


    public Kunde registierteKunde(int id, String name, String passwort, String adresse) {
        return kv.kundeRegistrierung(id, name, passwort, adresse);
    }



    // Massengut

    public void addMassengut(Massengut atikel){
        av.addMassengutartikel(atikel);
    }

    public void removeInMassengut(int artikelNummer ){
        av.removeMassengutartikel(artikelNummer);
    }

    public void einlargern(Artikel artikelNummer, int menge){
        av.einlagern(artikelNummer, menge);
    }
    public void auslagern (int artikelNummer, int menge){
        av.auslagern(artikelNummer,menge);
    }

    public void printEreignis(int artikelNummer){
        av.printEreignisse(artikelNummer);
    }


    public List<Rechnung> getRechnungenListe() {
        return getRechnungenListe();
    }

    public List<Ereignis> getEreignisLite(){
        return getEreignisLite();
    }
    public void addEreignis(Ereignis ereignis) {
        av.getEreignisListe();

    }
}





   /* public boolean fuegeMitarbeiterHinzu(int id, String name, String passwort, String adresse){
        return mv.fuegeMitarbeiterHinzu(id, name, passwort,adresse);
    }

 */



