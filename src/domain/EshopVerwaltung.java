package src.domain;


import src.persistence.ArtikelExistiertBereitsException;
import src.persistence.KundeExistiertBereitsException;
import src.persistence.MitarbeiterExistiertBereitsException;
import src.persistence.WarenkorbExistierBereitsException;
import src.valueObjects.*;

import java.io.IOException;
import java.util.List;

public class EshopVerwaltung {

    private BenutzerVerwaltung bv;
    private String datei;
    private ArtikelVerwaltung av;
    private MitarbeiterVerwaltung mv;
    private KundeVerwaltung kv;


    public EshopVerwaltung(String datei) throws IOException {
        this.datei = datei;
        av = new ArtikelVerwaltung();
        av.liesDaten(datei + "_ArtikelDB.txt");
        av.liesWarenkorbDaten(datei + "_Warenkorb.txt");
        kv = new KundeVerwaltung();
        kv.liesDatenVonKunde(datei + "_KundeDB.txt ");
        mv = new MitarbeiterVerwaltung();
        mv.liesDatenVonMitarbeiter(datei + "_MitarbeiterDB.txt");
        bv = new BenutzerVerwaltung();


    }

    // ArtikelVerwaltung

    public void speicherArtikel() throws IOException {
        av.schreibeDaten(datei + "_ArtikelDB.txt");
    }

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

    public List<Kunde> gibAlleKunden() {
        return kv.getKundeList();
    }

    public void NeueKunde(String name, int id, String passwort, String adresse) throws KundeExistiertBereitsException {
        if (!kv.sucheKunde(name, id, passwort, adresse).isEmpty()) {
            throw new KundeExistiertBereitsException(name, id);
        }
        Kunde kunde = new Kunde(name, id, passwort, adresse);
        kv.getKundeList().add(kunde);

    }

    public void speicherKunden() throws IOException {
        kv.schreibeDatenVonKunde(datei + "_KundeDB.txt");
    }

    public void loescheKunde(int id) {
        kv.kundeLoeschen(id);
    }


    // MitarbeiterVerwaltung
    public List<Mitarbeiter> gibAlleMitarbeiter() {
        return mv.getMitarbeiterList();
    }

    public void NeueMitarbeiter(String name, int id, String passwort) throws MitarbeiterExistiertBereitsException {
        if (!mv.sucheMitarbeiter(name, id, passwort).isEmpty()) {
            throw new MitarbeiterExistiertBereitsException(name, id);
        }
        Mitarbeiter mitarbeiter = new Mitarbeiter(name, id, passwort);
        mv.getMitarbeiterList().add(mitarbeiter);

    }

    public void speicherMitarbeiter() throws IOException {
        mv.schreibeDatenVonMitarbeiter(datei + "_MitarbeiterDB.txt");
    }

    public void loescheMitabeiter(int id) {
        mv.mitarbeiterLoeschen(id);
    }


    public boolean eingelogt(String name, String passwort) {
        return bv.login(name, passwort);

    }


// WarenkorbVerwaltung

    public void speicherWarenkorb() throws IOException {
        av.schreibeDatenInWarenkorb(datei + "_Warenkorb.txt");
    }

    public List<Warenkorb> gibAlleArtikelInWarenkorb() {
        return av.getAlleArtikelMengeInwarenkorb();
    }

    public void FuegeArtikelInWarenkorb(String bezeichnung, int menge) throws WarenkorbExistierBereitsException {
        av.fuegeArtikelInWarenkorbEin(bezeichnung, menge);
    }
      /*  {
            throw new WarenkorbExistierBereitsException(artikel, menge);
        }
        Warenkorb warenkorb = new Warenkorb(bezeichnung, menge);
        av.getAlleArtikelMengeInwarenkorb().add(warenkorb);
    }*/

    public Artikel getArtikelByName(String bezeichnung) {
        for (Artikel artikel : av.artikelList()) {
            if (artikel.getBezeichnung().equals(bezeichnung)) {
                return artikel;


            }
        }
        return null;
    }
    public Artikel getArtikelByNummer(int artikelNummer) {
        for (Artikel artikel : av.artikelList()) {
            if (artikel.getArtikelNummer() == artikelNummer) {
                return artikel;
            }
        }
        return null;
    }


    public void loescheArtikelInWarenkorb(int artikelNummer) {
        av.loeschenArtikelInWarenkorb(artikelNummer);
    }

    public Artikel ArtikelInWarenkorbÄndern(String bezeichnung, int menge) {
        av.ändernWarenkorb(bezeichnung, menge);
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
}
















   /* public Kunde registierteKunde (int id, String name, String passwort, String adresse) {
        return kv. kundeRegistrierung(id, name, passwort, adresse);
    }
*/

   /* public boolean fuegeMitarbeiterHinzu(int id, String name, String passwort, String adresse){
        return mv.fuegeMitarbeiterHinzu(id, name, passwort,adresse);
    }
    public void legeArtikelAn(String bezeichnung,int bestand, double preis, int artikelnummer) {
        av.artikelHinzufuegen(bezeichnung,bestand,  preis, artikelnummer);

    }

    public void erhoeheArtikelBestand(String bezeichnung, int menge) {
        av.erhoeheBestand(bezeichnung,menge);
    }

    public List<Artikel> ArtikelAusgeben() {
        return av.artikelAusgeben() ;
    }
    public List<Mitarbeiter> gibAlleMitarbeiter(){
        return mv.gibAlleMitarbeiter();
    }

    public void artikelreduktieren(Artikel artikel){
        av.artikelEntfernen(artikel);
        return ;
    }
  *//*  public void artikelZumWarenkorbHinzufuegen(Artikel artikel, int menge){
        av.artikelHinzufuegen(artikel, menge);
    }*//*
    public boolean artikelVomWarenkorbEntfernen(Artikel artikel){
        return wk.artikelEntfernen(artikel);
    }


    public void artikelMengeAendern(Warenkorb warenkorb, int artikelnummer, int neueMenge) {
       warenkorb.artikelMengeAendern(av.artikelSuchen(artikelnummer), neueMenge);
    }
   public void addArtikelInWarenkorb(Artikel  artikel,int menge){
        kv.artikelInWarenkorbHinzufuegen(artikel, menge);

   }
    public void warenkorbLöschen(){
        kv.warenkorbLeeren();
    }

    public String zeigtWarenkorb(){
        return kv.warenkorbInhaltAnzeigen();
    }
    public boolean existierteArtikel(Artikel artikel){
         av.artikelExistiert(artikel);
        return false;
    }
    public Artikel  findeArtikelnachBezeichnung(String bezeichnung) {
       return av.artikelNachBezeichnung(bezeichnung);
    }

    public Artikel findeArtikelNachNummer(int artikelNummer) {
        return av.artikelNachNummer(artikelNummer);
    }

    public void anderungInWarenkorb(Artikel artikel,int neueMenge){
        kv.artikelMengeAendern(artikel, neueMenge);
    }

    public void artikelZumWarenkorbHinzufuegen(Artikel artikel, int menge) {
        av.artikelImWarenkorbAnzeigen();
    }*/



