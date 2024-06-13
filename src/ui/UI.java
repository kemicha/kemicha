package src.ui;

import src.domain.EshopVerwaltung;
import src.Exeptions.ArtikelExistiertBereitsException;
import src.Exeptions.KundeExistiertBereitsException;
import src.Exeptions.MitarbeiterExistiertBereitsException;
import src.valueObjects.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class UI {
    private BufferedReader br;
    private EshopVerwaltung shop;

    public UI(String datei) throws IOException {
        shop = new EshopVerwaltung(datei);
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    private void gibMenueAus() {
        System.out.println("\nWillkommen im E-shop: Waehlen sie ein Option\n");
        System.out.println("1. Artikelliste ausgeben");
        System.out.println("2. KundeVerwaltung!");
        System.out.println("3. MitarbeiterVerwaltung");
        System.out.println("Beenden: E");
        System.out.print("Ihre Auswahl:> ");
        System.out.flush();
    }

    private String liesEingabe() throws IOException {
        return br.readLine();
    }

    private void start() throws IOException {
        String input;
        do {
            gibMenueAus();
            input = liesEingabe();
            switch (input) {
                case "1":
                    artikelEingabe();
                    break;
                case "2":
                    kundeEingabe();
                    break;
                case "3":
                    mitarbeiterEingabe();
                    break;
                case "e":
                    System.out.println("Auf Wiedersehen!");
                    break;
                default:
                    System.out.println("Ungueltige Eingabe. Bitte versuchen Sie es erneut.");
            }
        } while (!input.equals("e"));
    }

    private void artikelEingabe() throws IOException {
        String input;
        do {
            System.out.println("\nUnsere Artikel\n");
            System.out.println("1. Artikel anzeigen!");
            System.out.println("2. Artikel nach Name sortieren!");
            System.out.println("3. Artikel nach Nummer sortieren!");
            System.out.println("e. Zurueck zum Hauptmenue!");
            System.out.print("> Ihre Auswahl: ");
            System.out.flush();

            input = liesEingabe();
            List<Artikel> liste;
            switch (input) {
                case "1":
                    System.out.println(" Alle unsere Artikel sind : ");
                    liste = shop.gibAlleArtikel();
                    gibArtikellisteAus(liste);
                    break;
                case "2":
                    System.out.println(" Artikel erfolreich nach Name sortiert!");
                    liste= shop.sortiereArtikelNachName();
                    gibArtikellisteAus(liste);
                    break;
                case "3":
                   System.out.println(" Artikel erfolreich nach Nummer sortiert!");
                   liste= shop.sortiereArtikelNachNummer();
                   gibArtikellisteAus(liste);
                    break;
                case "e":
                    System.out.println("Zurueck zum Hauptmenue");
                    break;
                default:
                    System.out.println("Ungueltige Eingabe. Bitte versuchen Sie es erneut.");
            }
        } while (!input.equals("e"));
    }

    private void gibArtikellisteAus(List<Artikel> liste) {
        for (Artikel artikel : liste) {
            System.out.println(artikel);
        }
    }

    public void mitarbeiterEingabe() throws IOException {
        String input;
        do {
            System.out.println("\nMitarbeiter Menue: waehlen Sie eine Option");
            System.out.println("1. Benutzer einloggen!");
            System.out.println("2. Mitarbeiter registrieren!");
            System.out.println("3. Neue Artikel anlegen!");
            System.out.println("4. Artikelbestand erhoehen!");
            System.out.println("5. Artikel entfernen!");
            System.out.println("6. Artikelliste speichern & zeigen");
            System.out.println("7. Mitarbeiterliste anzeigen");
            System.out.println("8. Mitarbeiter speichern");
            System.out.println("9. Mitarbeiter loeschen & Ausloggen!");
            System.out.println("e. Ausloggen & Zurueck zum Hauptmenue");
            System.out.print("> Ihre Auswahl: ");
            System.out.flush();

            input = liesEingabe();
            List<Mitarbeiter> liste ;
            List<Artikel>artikelListe;
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
                    artikelBestandErhoehen();
                    break;
                case "5":
                    artikelEntfernen();
                    break;
                case "6":
                    artikelListe = shop.gibAlleArtikel();
                    gibArtikellisteAus(artikelListe);
                    break;
                case "7":
                    liste= shop.gibAlleMitarbeiter();
                    gibMitarbeiterliste(liste);
                    break;
                case "8":
                    shop.speicherMitarbeiter();
                    break;
                case "9":
                    shop.logout();
                    entferntMitarbeiter();
                    break;
                case "e":
                    shop.speicherDaten();
                    shop.logout();
                    System.out.println("Zurueck zum Hauptmenue");
                    break;
                default:
                    System.out.println("Ungueltige Eingabe. Bitte versuchen Sie es erneut.");
            }
        } while (!input.equals("e"));
    }

    private void entferntMitarbeiter() {
        System.out.println("Bitte geben Sie die ID des zu löschenden Mitarbeiter ein: ");
        try {
            int id = Integer.parseInt(liesEingabe());
            shop.loescheMitabeiter(id);
            System.out.println("Mitarbeiter erfolgreich geloescht.");
        } catch (NumberFormatException | IOException e) {
            System.out.println("Ungueltige ID.");
        }
    }

    private void gibMitarbeiterliste(List<Mitarbeiter> liste) {
        for (Mitarbeiter mitarbeiter : liste) {
            System.out.println(mitarbeiter);
        }
    }
    private void artikelEntfernen() throws IOException {
        System.out.println("Bitte geben Sie die Artikelnummer ein: ");
        try {
            int artikelNummer = Integer.parseInt(liesEingabe());
            shop.loescheArtikel(artikelNummer);
            System.out.println("Artikel erfolgreich entfernt.");
        } catch (NumberFormatException e) {
            System.out.println("Ungueltige Artikelnummer.");
        }
    }

    private void artikelBestandErhoehen() throws IOException {
        System.out.println(" geben Sie der Artikel Name:");
        String bezeichnung = liesEingabe();
        System.out.println("geben Sie die menge: ");
        int menge = Integer.parseInt(liesEingabe());
        shop.ehöhenArtikel(bezeichnung, menge) ;
        System.out.println(" Artikel erfolreich erhoehen!");

    }

    private void neueArtikelAnlegen() throws IOException {
        System.out.println("Bezeichnung:");
        String bezeichnung = liesEingabe();
        System.out.println("Artikel Nummer:");
        int artikelNummer = Integer.parseInt(liesEingabe());
        System.out.println("Artikel Bestand:");
        int bestand = Integer.parseInt(liesEingabe());
        System.out.println("Artikel Preis:");
        double preis = Double.parseDouble(liesEingabe());
        try {
            shop.fuegeArtikelEin(bezeichnung, artikelNummer, bestand, preis);
            System.out.println("Neue Mitarbeiter erfolgreich eingefuegt!");
        } catch (ArtikelExistiertBereitsException e) {
            System.out.println("Fehler beim Einfuegen. Artikel existiert bereits.");
            e.printStackTrace();
        }
    }

    private void benutzerEinlogge() throws IOException {
        System.out.println("Geben Sie Ihren Benutzernamen ein:");
        String name = liesEingabe();
        System.out.println("Geben Sie Ihr Passwort ein:");
        String passwort = liesEingabe();

        boolean loginErfolgreich = shop.login(name, passwort);
        System.err.println(loginErfolgreich);
        if (loginErfolgreich) {
            System.out.println("Erfolgreich eingeloggt!");
        }
    }


    private void mitarbeiterRegistrieren() throws IOException {
        System.out.println("Bitte geben Sie Ihren Namen ein: ");
        String name = liesEingabe();
        System.out.println("Bitte geben Sie eine ID ein: ");
        int id = Integer.parseInt(liesEingabe());
        System.out.println("Bitte geben Sie ein Passwort ein: ");
        String passwort = liesEingabe();
        try {
            shop.NeueMitarbeiter(name, id, passwort);
            System.out.println("\nMitarbeiter erfolgreich registriert.");
        } catch (MitarbeiterExistiertBereitsException e) {
            System.out.println("\nFehler bei der Registrierung: Mitarbeiter existiert bereits.");
        }
    }

    public void kundeEingabe() throws IOException {
        String input;
        do {
            System.out.println("\nKunde Menue: waehlen Sie eine Option\n");
            System.out.println("1. Benutzer einloggen!");
            System.out.println("2. Kunde registrieren!");
            System.out.println("3. Artikel in Warenkorb hinzufuegen!");
            System.out.println("4. Artikel im Warenkorb anzeigen!");
            System.out.println("5. Artikel im Warenkorb aendern!");
            System.out.println("6. Artikel im Warenkorb leeren!");
            System.out.println("7. Artikel im Warenkorb kaufen und Rechnung anzeigen!");
            System.out.println("8. Kundenliste anzeigen");
            System.out.println("9. Kunde speichern");
            System.out.println("10. Kunde loeschen ");
            System.out.println("e. Ausloggen & Zurueck zum Hauptmenue");
            System.out.print("> Ihre Auswahl: ");
            System.out.flush();

            input = liesEingabe();
            List<Kunde> list;
            List<Warenkorb>liste = null;
            switch (input) {
                case "1":
                     benutzerEinlogge();
                    break;
                case "2":
                    kundeRegistrieren();
                    break;
                case "3":
                     artikelInWarenkorbHinzufuegen();
                    shop.speicherEreignis();
                    break;
                case "4":
                    liste = shop.gibAlleArtikelInWarenkorb();
                    warenkorbZeigen(liste);
                    break;
                case "5":
                    artikelInWarenkorbÄndern();
                    break;
                case "6":
                    shop.warenkorbLeeren();
                    break;
                case "7":
                    kaufen();
                    rechnungErzeugen();
                    break;
                case "8":
                    list= shop.gibAlleKunden();
                    gibKundeliste(list);
                    break;
                case "9":

                    System.out.println("Neue Kunden erfolgreich gespeichert!");
                    break;
                case "10":
                    shop.logout();
                    kundeLoeschen();
                    break;
                case "e":
                    shop.speicherDaten();
                    shop.logout();
                    System.out.println("Zurueck zum Hauptmenue");
                    break;
                default:
                    System.out.println("Ungueltige Eingabe. Bitte versuchen Sie es erneut.");
            }
        } while (!input.equals("e"));
    }

    private void kundeRegistrieren() throws IOException {
        System.out.println("Bitte geben Sie Ihren Namen ein: ");
        String name = br.readLine();
        System.out.println("Bitte geben Sie Ihre ID ein: ");
        int id = Integer.parseInt(br.readLine());
        System.out.println("Bitte geben Sie Ihr Passwort ein: ");
        String passwort = br.readLine();
        System.out.println("Bitte geben Sie Ihre Adresse ein: ");
        String adresse = br.readLine();
        try {
            shop.NeueKunde(name, id, passwort, adresse);
            System.out.println("\nRegistrierung erfolgreich. Sie koennen sich jetzt einloggen.");
        } catch (KundeExistiertBereitsException e) {
            System.out.println("\nEin Kunde mit diesen Anmeldeinformationen existiert bereits.");
        }
    }

    private void gibKundeliste(List<Kunde> liste) {
        for (Kunde kunde : liste) {
            System.out.println(kunde);
        }
    }
    private void kundeLoeschen() throws IOException {
        System.out.println("Bitte geben Sie die ID des zu löschenden Kunden ein: ");
        try {
            int id = Integer.parseInt(liesEingabe());
            shop.loescheKunde(id);
            System.out.println("\nKunde erfolgreich geloescht.");
        } catch (NumberFormatException e) {
            System.out.println("\nUngueltige ID.");
        }
    }

    private void artikelInWarenkorbHinzufuegen() throws IOException {
        System.out.println("Bezeichnung:");
        String bezeichnung = liesEingabe();
        Artikel artikel = shop.getArtikelByName(bezeichnung);

        if (artikel == null) {
            System.out.println("Der Artikel existiert nicht.");
            return;
        }

        System.out.print("Geben Sie die Menge ein: ");
        int menge = 0;

        try {
            menge = Integer.parseInt(liesEingabe());
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Zahleneingabe.");
            return;
        }

        if (menge <= 0 || menge > artikel.getAnzahl()) {
            System.out.println("Ungültige Menge oder nicht genügend Bestand für den Artikel.");
            return;
        }

        try {
            shop.FuegeArtikelInWarenkorb(bezeichnung, menge);
            System.out.println("Artikel in den Warenkorb eingefügt!");
        } catch (Exception e) {
            System.out.println("Fehler beim Einfügen des Artikels in den Warenkorb.");
            e.printStackTrace();
        }
    }


    private void warenkorbZeigen(List<Warenkorb> liste) {
            for (Warenkorb warenkorb : liste) {
                System.out.println(warenkorb);
            }
        }

    private void artikelInWarenkorbÄndern() throws IOException {
        System.out.println("Geben Sie den Artikel Namen:");
        String bezeichnung = liesEingabe();

        System.out.println("Geben Sie die neue Menge ein:");
        int neueMenge;
        try {
            neueMenge = Integer.parseInt(liesEingabe());
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Zahleneingabe.");
            return;
        }

        if (neueMenge < 0) {
            System.out.println("Die Menge darf nicht negativ sein.");
            return;
        }

        try {
            shop.aenderungImWarenkorb(bezeichnung, neueMenge);
            System.out.println("Artikelmenge im Warenkorb erfolgreich geändert!");
        } catch (Exception e) {
            System.out.println("Fehler bei der Änderung der Artikelmenge im Warenkorb.");
            e.printStackTrace();
        }
    }


    private void warenkorLeer()  {
        System.out.println(" Warenkorb erfolrechich geloescht!");

        shop.warenkorbLeeren();
    }




       private void kaufen() throws IOException {
          shop.kaufenArtikel(new Benutzer()) ;
           if (shop.getWarenkorbList().isEmpty()) {
               System.out.println("Der Warenkorb ist jetzt leer.");
           } else {
               System.out.println("Es gibt noch Artikel im Warenkorb.");
           }
       }




    private void rechnungErzeugen() {

    }

    public void run() {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            UI ui = new UI("Eshop");
            ui.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
