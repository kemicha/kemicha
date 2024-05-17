package src.domain;


import src.valueObjects.Artikel;
import src.valueObjects.Kunde;
import src.valueObjects.Mitarbeiter;

import java.util.List;

public class EshopVerwaltung {
    private KundeVerwaltung kv;
    private MitarbeiterVerwaltung mv;
    private ArtikelVerwaltung av;
    private BenutzerVerwaltung bv;
    private Warenkorb wk;
    private Object ArtikelBezeichnung;


    public EshopVerwaltung() {
        this.bv = new BenutzerVerwaltung();
        this.kv = new KundeVerwaltung();
        this.mv = new MitarbeiterVerwaltung();
        this.av = new ArtikelVerwaltung();
    }

    public boolean eingelogt(String name, String passwort) {
        return bv.login(name, passwort);

    }

    public Kunde registierteKunde (int id, String name, String passwort, String adresse) {
        return kv. kundeRegistrierung(id, name, passwort, adresse);
    }


    public boolean fuegeMitarbeiterHinzu(int id, String name, String passwort, String adresse){
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
  /*  public void artikelZumWarenkorbHinzufuegen(Artikel artikel, int menge){
        av.artikelHinzufuegen(artikel, menge);
    }*/
    public boolean artikelVomWarenkorbEntfernen(Artikel artikel){
        return wk.artikelEntfernen(artikel);
    }
    public void warenkorbLeeren(){
        wk.warenkorbLeeren();
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
    }
}







