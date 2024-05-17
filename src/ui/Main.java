package src.ui;

import src.domain.EshopVerwaltung;
import src.valueObjects.Artikel;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private final BufferedReader br;
    EshopVerwaltung shop;

    public Main() {
        this.shop = new EshopVerwaltung();
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    private void gibMenueAus() {
        System.out.println("\nWillkommen im E-shop: Wählen sie ein Option\n");
        System.out.println("1. KundeVerwaltung!");
        System.out.println("2. MitarbeiterVerwaltung");
        System.out.println("3. Artikelliste ausgeben");
        System.out.println("Beenden: E");
        System.out.print("> Auswahl: ");
        System.out.flush();
    }

    private void start() throws IOException {
        String input;
        do {
            gibMenueAus();
            input = br.readLine();
            switch (input) {
                case "1":
                     kundeEingabe();
                    break;
                case "2":
                     mitarbeiterEingabe();
                    break;
                case "3":
                    artikelEingabe();
                    break;
                case "E":
                    System.out.println("Auf Wiedersehen!");
                    break;
                default:
                    System.out.println("Ungültige Eingabe. Bitte versuchen Sie es erneut.");
            }
        } while (!input.equals("E"));

    }


    private void artikelEingabe() throws IOException {
        String input;
        do {
            System.out.println("\n Unsere artikel \n");
            System.out.println("1. Artike anzeige!");
            System.out.println("2. Artikel nach Name sortieren!");
            System.out.println("3. Artike nach Nummer sortieren!");
            System.out.println("4. Züruck Zur HautpMenu!");
            System.out.println("> Ihre Auswahl: ");
            System.out.flush();

            input = br.readLine();
            switch (input) {
                case "1":
                    artikelAnzeigen();
                    break;
                case "2":
                    artikelNachNameSortieren();
                    break;
                case "3":
                    artikelNachNummerSortieren();
                    break;
                case "4":
                    System.out.println("Zurück zum Hauptmenu ");
                    break;
                default:
                    System.out.println(" Ungültige Eingabe.Bitte versuchen Sie es erneut");
                             }

        } while (!input.equals("E"));
    }

    private String liesEingabe() throws IOException {
        return br.readLine();
    }

    public void mitarbeiterEingabe() throws IOException {
        String input;
        do {

            System.out.println("\n  Mitarbeiter Menu: wählen Sie eine Option ");
            System.out.println("1. Benutzer einloggen!");
            System.out.println("2. mitarbeiter registrieren!");
            System.out.println("3. Neue Artikel legen!");
            System.out.println("4. Artike Bestand erhöhen!");
            System.out.println("5. Artikel entfernen!");
            System.out.println("> Ihre Auswahl: ");
            System.out.flush();

            input = br.readLine();
            switch (input) {
                case "1":
                    benutzerEinlogge();
                    break;
                case "2":
                    mitarbeiterRegistrieren();
                    break;
                case "3":
                    neueArtikelAnlegen();
                    break;
                case "4":
                    artikelBeststandErhoehen();
                    break;
                case "5":
                    artikelEntfernen();
                    break;
                case "6":
                    ausloggen();
                    break;
                case "8":
                    System.out.println("Zurück zum Hauptmenu ");
                    break;
                default:
                    System.out.println(" Ungültige Eingabe.Bitte versuchen Sie es erneut");

            }
        } while (!input.equals("E"));
    }

    private void mitarbeiterRegistrieren() {
    }

    private void ausloggen() {

    }

    private void artikelNachNummerSortieren() throws IOException {
        if (!benutzerEinlogge()) {
        }
        System.out.println("Bitte zuerst einloggen!");
        return;

    }

    private void artikelNachNameSortieren() throws IOException {
        if (!benutzerEinlogge()) {
        }
        System.out.println("Bitte zuerst einloggen!");
        return;
    }

    private void artikelEntfernen() throws IOException {
        if (!benutzerEinlogge()) {
        }
        System.out.println("Bitte zuerst einloggen!");
        return;
    }

    private void artikelBeststandErhoehen() throws IOException {
        if (!benutzerEinlogge()) {
        }
        System.out.println("Bitte zuerst einloggen!");
        return;
    }

    // todo: die richtige passende aktion ausfuehren (switch)


    boolean benutzerEinlogge() throws IOException {
        System.out.println(" Geben Sie Ihren Benutzername ein:");
        String name = liesEingabe();
        System.out.println(" Geben Sie Ihr Passwort ein:");
        String passwort = liesEingabe();
        System.out.println(shop.eingelogt(name, passwort));
        boolean loginErfolgreich = shop.eingelogt(name, passwort);
        if (loginErfolgreich) {
            System.out.println("Erfolgreich eingeloggt!");
        } else {
            System.out.println("Fehler beim Einloggen. Benutzername oder Passwort falsch.");
        }
        // todo 1: nutzer namen einlesen
        // todo 2: passwort einlesen
        // todo 3: name und passwort der methode uebergeben
        // todo 4: rueckgabewert auswerten (true false???)


        return loginErfolgreich;
    }

    void kundeRegistrieren() throws IOException {
        System.out.println("Geben Sie Ihre ID-Nummer an:");
        int id = Integer.parseInt(liesEingabe());
        System.out.println(" Geben Sie Ihren benutzername ein:");
        String name = liesEingabe();
        if (name.isEmpty()) {
            System.out.println("Ungültiger Benutzername. Bitte geben Sie einen Benutzernamen ein. ");
            return;
        }
        System.out.println(" Geben Sie Ihr Passwort ein:");
        String passwort = liesEingabe();
        if (passwort.isEmpty()) {
            System.out.println("Ungültiges Passwort. Bitte geben Sie ein Passwort ein.");
            return;
        }
        System.out.println("Geben Sie Ihre Adresse ein:");
        String adresse = liesEingabe();
        System.out.println(" Kunde " + name + "ist registriert");
        System.out.println(shop.registierteKunde(Integer.parseInt(String.valueOf(id)), name, passwort, adresse));

    }

    public void kundeEingabe() throws IOException {
        String input;
        do {
            System.out.println("\n KundeMenu: wählen Sie eine Option\n ");
            System.out.println("1. Benutzer einloggen!");
            System.out.println("2. Kunde  registrieren!");
            System.out.println("3. Artikel in Warenkorb hinzufühgen! ");
            System.out.println("4. Artikel in Warenkorb anzeigen!");
            System.out.println("5. Artikel in warenkorb ändern!");
            System.out.println("6. Artikel in Warenkorb leeren!");
            System.out.println("7. Artikel in Warenkorb kaufen und Rechnung anzeiegen!");
            System.out.println("8. Züruck zur hauptMenu");
            System.out.println("> Ihre Auswahl: ");
            System.out.flush();

            input = br.readLine();
            switch (input) {
                case "1":
                    benutzerEinlogge();
                    break;
                case "2":
                    kundeRegistrieren();
                    break;
                case "3":
                    artikelInWarenkorbHinzufuegen();
                    break;
                case "4":
                    warenkorbZeigen();
                    break;
                case "5":
                    artikelInWarenkorbÄndern();
                    break;
                case "6":
                     warenkorbLeer();
                     break;
                case "7":
                    kaufen();
                    rechnungErzeugen();
                    break;
                case "E":
                    System.out.println("Zurück zum Hauptmenü");
                    break;
                default:
                    System.out.println("Ungültige Eingabe. Bitte versuchen Sie erneut!");
            }
        } while (!input.equals("E"));
    }


    private void rechnungErzeugen() throws IOException {

    }

    private void warenkorbZeigen(){
        shop.zeigtWarenkorb();
    }

    public void artikelInWarenkorbHinzufuegen() throws IOException {
        System.out.println("Geben Sie den Namen ein:");
        String bezeichnung = liesEingabe();
        Artikel artikel = shop.findeArtikelnachBezeichnung(bezeichnung);

        if (artikel != null) {
            System.out.println("Vor dem Hinzufügen des Artikels zum Warenkorb:");
            return;
        }
        System.out.println("Geben Sie die Menge ein:");
        int menge = Integer.parseInt(liesEingabe());

        if (menge <= 0 || menge > artikel.getBestand()) {
            System.out.println("Ungültige Menge oder nicht genügend Bestand für den Artikel.");
            return;
        } shop.artikelZumWarenkorbHinzufuegen(artikel, menge);
            System.out.println("Nach dem Hinzufügen des Artikels zum Warenkorb:");
            warenkorbZeigen();
    }


    public void neueArtikelAnlegen() throws IOException {
        System.out.println("Geben Sie der Artikelname ein:");
        String bezeichnung = liesEingabe();
        System.out.println("Geben Sie die Menge ein:");
        int bestand = Integer.parseInt(liesEingabe());
        if (bestand <= 0) {
            System.out.println("Ungültige Menge.");
            return;
        }
        System.out.println("Geben Sie den Preis ein:");
        double preis = Double.parseDouble(liesEingabe());
        System.out.println("Geben Sie die Artikelnummer ein:");
        int artikelnummer = Integer.parseInt(liesEingabe());
        Artikel artikel = new Artikel(bezeichnung, bestand, preis, artikelnummer);
        if (shop.existierteArtikel(artikel)) {
            System.out.println("Der Artikel existiert bereits.");
            return;
        }
//            shop.addArtikelInWarenkorb(artikel,menge);
        System.out.println("Der Artikel wurde erfolgreich angelegt.");
    }


    public void artikelAnzeigen() throws IOException {

    }

    public void artikelInWarenkorbÄndern() {



    }

    public void warenkorbLeer() throws IOException {
        System.out.println(" Warenkorb ist leer ");
        System.out.println(" Fühgen neue Artikel hin!");


    }

    void warenkorbAnzeigen() throws IOException {


    }

    public void kaufen() throws IOException {

    }

    public void ereignis() throws IOException {

    }

    private void legeArtikelAn() throws IOException {

    }


    public static void main(String[] args) {
        Main main;
        try {
            main = new Main();
            main.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /*List<Artikel> artikel =shop.ArtikelAusgeben();*/
    /*gibArtikelAus(artikel);*/
    /*break;*/
    /*case "4":*/
    /*System.out.println("Geben Sie die Bezeichnung des Artikels ein:");*/
    /*String bezeichnung = liesEingabe();*/
    /*System.out.println("Geben Sie den Preis des Artikels ein:");*/
    /*double preis = Double.parseDouble(liesEingabe());*/
    /*System.out.println("Geben Sie die Artikelnummer des Artikels ein:");*/
    /*int artikelnummer = Integer.parseInt(liesEingabe());*/
    /*break;*/
    /* case"5":*/
    /*System.out.println("Geben Sie Ihren Namen ein:");*/
    /*name = liesEingabe();*/
    /*if (!shop.fuegeMitarbeiterHinzu(0, name, null, null)) {*/
    /*    System.out.println("Einen Mitarbeiter mit diesem Namen gibt es bereits");*/
    /*}*/
    /*for (Mitarbeiter mitarbeiter : shop.gibAlleMitarbeiter()) {*/
    /*    System.out.println(mitarbeiter);*/
    /*}*/
    /*break;*/
    /*case "6":*/
    /*System.out.println("Geben Sie den Artikelnamen ein:");*/
    /*String artikelBezeichnung = liesEingabe();*/
    /*Artikel artikel1 = (Artikel) shop.nachArtikelBezeichnungSuchen(artikelBezeichnung);*/
    /*if (artikel1 != null) {*/
    /*    System.out.println("Geben Sie die Anzahl ein, um den Bestand zu erhöhen:");*/
    /*    int anzahl = Integer.parseInt(liesEingabe());*/
    /*    System.out.println("Bestand von " + artikelBezeichnung + " um " + anzahl + " erhöht.");*/
    /*}else {*/
    /*    System.out.println("Artikel nicht gefunden.");*/


}