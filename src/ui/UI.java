package src.ui;

import src.domain.EshopVerwaltung;
import src.persistence.ArtikelExistiertBereitsException;
import src.valueObjects.Artikel;

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
        System.out.println("\nWillkommen im E-shop: Wählen sie ein Option\n");
        System.out.println("1. KundeVerwaltung!");
        System.out.println("2. MitarbeiterVerwaltung");
        System.out.println("3. Artikelliste ausgeben");
        System.out.println("Beenden: E");
        System.out.print("> Auswahl: ");
        System.out.flush();
    }

    private String liesEingabe() throws IOException {
        return br.readLine();
    }
    private void start(String input) throws IOException {
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
            List<Artikel> liste ;
            switch (input) {
                case "1":
                   liste=shop.gibAlleArtikel();
                    gibArtikellisteAus(liste);
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

    private void artikelNachNummerSortieren() {
    }

    private void artikelNachNameSortieren() {

    }

    private void gibArtikellisteAus(List<Artikel> liste) {
        for (Artikel artikel : liste) {
            System.out.println(artikel);
        }
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
                System.out.println("6. Artikelliste speichern");
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
                        shop.speicherArtikel();
                    case "7":
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

    private void artikelEntfernen() {
    }

    private void artikelBeststandErhoehen() {

    }

    private void neueArtikelAnlegen() throws IOException {
        System.out.println(" Bezeichnung:");
        String bezeichnung = liesEingabe();
        System.out.println(" Artikel Nummer:");
        int artikelNummer = Integer.parseInt(liesEingabe());
        System.out.println(" Artikel Bestand:");
        int bestand= Integer.parseInt(liesEingabe());
        System.out.println(" Artikel Preis:");
        double preis= Double.parseDouble(liesEingabe());
        try {
            shop.fuegeArtikelEin( bezeichnung,artikelNummer,bestand,preis);
            System.out.println(" Einfuegen ist ok!");
        }catch (ArtikelExistiertBereitsException e){
            System.out.println(" Fehler beim Einfuegen");
            e.printStackTrace();
        }


    }

    private void benutzerEinlogge() {

    }

    private void mitarbeiterRegistrieren() {
        }

        private void ausloggen() {

        }




    private void kundeEingabe() {

    }



    public void run() {
        String input = "";

        do {
            gibMenueAus();
            try {
                input = liesEingabe();
                start(input);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } while (!input.equals("q"));
    }
    public static void main(String[] args) {
        UI ui;
        try {
            ui = new UI("Eshop");
            ui.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
