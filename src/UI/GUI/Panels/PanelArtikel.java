package src.UI.GUI.Panels;

import src.UI.GUI.ArtikelTableModel;
import src.domain.BenutzerVerwaltung;
import src.domain.EshopVerwaltung;
import src.valueObjects.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class PanelArtikel extends JPanel {


    private EshopVerwaltung eshop;
    private Frame frame;
    //private HinzufuegenListener listener = null;
    private JTable artikelTable;
    private ArtikelTableModel artikellisteTable;
    private BenutzerVerwaltung eingeloggterBenutzer;

    public PanelArtikel(EshopVerwaltung eshop, ArtikelTableModel artikellisteTable, Kunde eingeloggterKunde, Frame frame) {
        this.eshop = eshop;
        this.artikellisteTable = artikellisteTable;
        this.eingeloggterBenutzer = eingeloggterBenutzer;
        this.frame = frame;
    }

    public void artikelAnlegenKlick() {
        JTextField bezeichnungField = new JTextField();
        JTextField artikelNummerField = new JTextField();
        JTextField bestandField = new JTextField();
        JTextField preisField = new JTextField();
        JCheckBox massengutartikelCheckbox = new JCheckBox("Massengutartikel:");

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Bezeichnung:"));
        panel.add(bezeichnungField);
        panel.add(new JLabel("Artikelnummer:"));
        panel.add(artikelNummerField);
        panel.add(new JLabel("Bestand:"));
        panel.add(bestandField);
        panel.add(new JLabel("Preis:"));
        panel.add(preisField);
        panel.add(massengutartikelCheckbox);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Artikel anlegen", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String bezeichnung = bezeichnungField.getText();
                int artikelnummer = Integer.parseInt(artikelNummerField.getText());
                int bestand = Integer.parseInt(bestandField.getText());
                double preis = Double.parseDouble(preisField.getText());
                boolean massengutartikel = massengutartikelCheckbox.isSelected();
                int packungsgrosse = 1;

                if (massengutartikel) {
                    String input = JOptionPane.showInputDialog(frame, "Packungsgröße:", "Artikel anlegen", JOptionPane.INFORMATION_MESSAGE);
                    if (input != null && !input.isEmpty()) {
                        packungsgrosse = Integer.parseInt(input);
                    } else {
                        showMessageDialog(frame, "Bitte eine gültige Packungsgröße eingeben.", "Artikel anlegen", JOptionPane.ERROR_MESSAGE);
                        return; // Quit the method
                    }
                }

                /*Artikel artikel;
                if (massengutartikel) {
                    artikel = new Artikel.Massengut(bezeichnung, artikelnummer, bestand, preis, packungsgrosse, eshop);
                } else {
                    artikel = new Artikel(bezeichnung, artikelnummer, bestand, preis, eshop);
                }*/


            } catch (NumberFormatException e) {
                showMessageDialog(frame, "Bitte geben Sie gültige Zahlenwerte ein.", "Artikel anlegen", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                showMessageDialog(frame, "Fehler beim Anlegen des Artikels: " + e.getMessage(), "Artikel anlegen", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void entferneArtikelKlick() {
        java.util.List<Artikel> artikelList = eshop.gibAlleArtikel();

        if (artikelList.isEmpty()) {
            showMessageDialog(frame, "Keine Artikel vorhanden.", "Artikel entfernen", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String auswahl = (String) JOptionPane.showInputDialog(
                frame,
                "Wählen Sie einen Artikel zum Entfernen:",
                "Artikel entfernen",
                JOptionPane.QUESTION_MESSAGE,
                null,
                artikelList.toArray(),
                artikelList.get(0)
        );
        if (auswahl != null) {
            try {
                Artikel artikel = eshop.getArtikelByName(auswahl);
                boolean artikelEntfernt = eshop.aenderungImWarenkorb(artikel.getBezeichnung(), 1);

                if (artikelEntfernt) {
                    showMessageDialog(frame, "Artikel erfolgreich entfernt.", "Artikel entfernt", JOptionPane.INFORMATION_MESSAGE);
                    // Mettez à jour la liste des articles dans l'interface utilisateur si nécessaire.
                } else {
                    showMessageDialog(frame, "Artikel konnte nicht entfernt werden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                showMessageDialog(frame, "Ungültige Artikelnummer", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void artikelEinlagernKlick() {
        List<Artikel> artikelList = eshop.gibAlleArtikel();
        if (artikelList.isEmpty()) {
            showMessageDialog(frame, "Keine Artikel vorhanden.", "Artikel einlagern", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String[] artikelArray = new String[artikelList.size()];
            for (int i = 0; i < artikelList.size(); i++) {
                Artikel artikel = artikelList.get(i);
                artikelArray[i] = artikel.getBezeichnung() + " (" + artikel.getArtikelNummer() + ")";
            }

            JComboBox<String> artikelComboBox = new JComboBox<>(artikelArray);

            JPanel panel = new JPanel(new GridLayout(3, 2));
            panel.add(new JLabel("Artikel auswählen:"));
            panel.add(artikelComboBox);
            panel.add(new JLabel("Menge eingeben:"));
            JTextField mengeField = new JTextField();
            panel.add(mengeField);

            int result = showConfirmDialog(frame, panel, "Artikel einlagern", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String ausgewählterArtikel = (String) artikelComboBox.getSelectedItem();
                    int öffnen = ausgewählterArtikel.indexOf("(");
                    int schließen = ausgewählterArtikel.indexOf(")");
                    if (öffnen != -1 && schließen != -1) {
                        String artikelnummerStr = ausgewählterArtikel.substring(öffnen + 1, schließen);
                        int artikelnummer = Integer.parseInt(artikelnummerStr);
                        int menge = Integer.parseInt(mengeField.getText());

                        Artikel artikel = eshop.getArtikelByNummer(artikelnummer);
                        if (artikel != null) {
                            if (artikel instanceof Massengut) {
                                Massengut massengutartikel = (Massengut) artikel;
                                if (menge % massengutartikel.getPackungsgroesse() != 0) {
                                    showMessageDialog(frame, "Einlagerungen für Massengutartikel müssen in Vielfachen der Packungsgröße erfolgen.", "Fehler", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                            }

                            Mitarbeiter mitarbeiter = (Mitarbeiter) eingeloggterBenutzer.getAngemeldeteBenutzer();
                            Ereignis ereignis = new Ereignis("Mitarbeiter",0, artikel,menge,new Date(),"besstand erhöhen");
                            eshop.addEreignis(ereignis);
                            eshop.speicherDaten();
                            showMessageDialog(frame, "Artikel erfolgreich eingelagert.", "Artikel einlagern", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            showMessageDialog(frame, "Artikel nicht gefunden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NumberFormatException e) {
                    showMessageDialog(frame, "Ungültige Eingabe.", "Fehler", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    showMessageDialog(frame, "Ein Fehler ist aufgetreten.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void artikelauslagerKlick() {
        JTextField artikelnummerField = new JTextField();
        JTextField mengeField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Artikelnummer:"));
        panel.add(artikelnummerField);
        panel.add(new JLabel("Menge:"));
        panel.add(mengeField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Artikel auslagern", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int artikelnummer = Integer.parseInt(artikelnummerField.getText());
                int menge = Integer.parseInt(mengeField.getText());

                Artikel artikel = eshop.getArtikelByNummer(artikelnummer);

                if (artikel != null) {
                    if (artikel instanceof Massengut) {
                        Massengut massengutartikel = (Massengut) artikel;
                        if (menge % massengutartikel.getPackungsgroesse() != 0) {
                            showMessageDialog(frame, "Auslagerungen für Massengutartikel müssen in Vielfachen der Packungsgröße erfolgen.", "Fehler", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    Mitarbeiter mitarbeiter = (Mitarbeiter) eingeloggterBenutzer.getAngemeldeteBenutzer();
                    Ereignis ereignis = new Ereignis("Mitarbeiter",0, artikel,menge,new Date(),"besstand erhöhen");
                    eshop.addEreignis(ereignis);
                    eshop.speicherDaten();
                    showMessageDialog(frame, "Artikel erfolgreich ausgelagert.", "Artikel ausgelagert", JOptionPane.INFORMATION_MESSAGE);
                    updateArtikellisteTable(eshop.gibAlleArtikel());
                } else {
                    showMessageDialog(frame, "Artikel nicht gefunden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                showMessageDialog(frame, "Ungültige Eingabe.", "Fehler", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void changeStueckzahl() {
        String artikelnummerInput = JOptionPane.showInputDialog(frame, "Geben Sie die Artikelnummer ein, um die Stückzahl zu ändern:");

        if (artikelnummerInput != null && !artikelnummerInput.isEmpty()) {
            try {
                int artikelnummer = Integer.parseInt(artikelnummerInput);
                Artikel artikel = eshop.getArtikelByNummer(artikelnummer);

                if (artikel != null && eshop.getWarenkorbList().contains(artikel)) {
                    String neueStueckzahlInput = JOptionPane.showInputDialog(frame, "Geben Sie die neue Stückzahl ein:");

                    if (neueStueckzahlInput != null && !neueStueckzahlInput.isEmpty()) {
                        try {
                            int neueStueckzahl = Integer.parseInt(neueStueckzahlInput);

                            if (neueStueckzahl <= artikel.getAnzahl()) {
                                artikel.setAnzahl(neueStueckzahl);
                                JOptionPane.showMessageDialog(frame, "Stückzahl erfolgreich geändert.", "Stückzahl ändern", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Die angegebene Stückzahl übersteigt den Bestand des Artikels.", "Fehler", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Ungültige Eingabe für die Stückzahl.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Artikel mit der angegebenen Artikelnummer wurde nicht gefunden oder ist nicht im Warenkorb.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Ungültige Eingabe für die Artikelnummer.", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removeArtikel() {
        String artikelnummerInput = JOptionPane.showInputDialog(frame, "Geben Sie die Artikelnummer ein, um den Artikel zu entfernen:");

        if (artikelnummerInput != null && !artikelnummerInput.isEmpty()) {
            try {
                int artikelnummer = Integer.parseInt(artikelnummerInput);
                Artikel artikel = eshop.getArtikelByNummer(artikelnummer);

                if (artikel != null && eshop.getWarenkorbList().contains(artikel)) {
                    eshop.getWarenkorbList().remove(artikel);
                    JOptionPane.showMessageDialog(frame, "Artikel erfolgreich aus dem Warenkorb entfernt.", "Artikel entfernen", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Artikel mit der angegebenen Artikelnummer wurde nicht gefunden oder ist nicht im Warenkorb.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Ungültige Eingabe für die Artikelnummer.", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void WarenkorbKlick() {
        List<Warenkorb> warenkorb = eshop.getWarenkorbList();
        if (warenkorb.isEmpty()) {
            showMessageDialog(frame, "Warenkorb ist leer.", "Warenkorb anzeigen", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder();
            for (Warenkorb artikel : warenkorb) {
                sb.append("Artikelnummer: ").append(artikel.getArtikel()).append("\n");
                sb.append("Preis: ").append(artikel.getGesamtePreis()).append(" Euro\n");
                sb.append("Stückzahl: ").append(artikel.getMenge()).append("\n");

                sb.append("\n");
            }
            showMessageDialog(frame, sb.toString(), "Warenkorb anzeigen", JOptionPane.INFORMATION_MESSAGE);



    JButton kaufenButton = new JButton("Kaufen");
            kaufenButton.addActionListener(e -> kaufeArtikel(warenkorb));

            String[] cartOptions = {"Stückzahl ändern", "Artikel entfernen", "Warenkorb leeren", "Kaufen", "Schließen"};
            int cartResult = JOptionPane.showOptionDialog(frame, sb.toString(), "Warenkorb",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, cartOptions, cartOptions[0]);

            if (cartResult == 0) {
                changeStueckzahl();
            } else if (cartResult == 1) {
                removeArtikel();
            } else if (cartResult == 2) {
                clearWarenkorb();
            } else if (cartResult == 3) {
                kaufeArtikel(warenkorb);
            }
        }
    }

    public void artikelZumWarenkorbHinzufuegenKlick() {
        String artikelnummerInput = JOptionPane.showInputDialog(frame, "Geben Sie die Artikelnummer ein:");

        if (artikelnummerInput != null && !artikelnummerInput.isEmpty()) {
            try {
                int artikelnummer = Integer.parseInt(artikelnummerInput);
                Artikel artikel = eshop.getArtikelByNummer(artikelnummer);

                if (artikel != null) {
                    String stueckzahlInput = JOptionPane.showInputDialog(frame, "Geben Sie die Stückzahl ein:");

                    if (stueckzahlInput != null && !stueckzahlInput.isEmpty()) {
                        try {
                            int stueckzahl = Integer.parseInt(stueckzahlInput);

                            if (stueckzahl <= artikel.getAnzahl()) {
                                artikel.setAnzahl(stueckzahl);
                                int menge = 0;
                                eshop.getWarenkorbList().add(new Warenkorb(artikel,menge));
                                JOptionPane.showMessageDialog(frame, "Artikel erfolgreich zum Warenkorb hinzugefügt.");
                            } else {
                                JOptionPane.showMessageDialog(frame, "Die angegebene Stückzahl übersteigt den Bestand des Artikels.", "Fehler", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Ungültige Eingabe für die Stückzahl.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Artikel mit der angegebenen Artikelnummer wurde nicht gefunden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Ungültige Eingabe für die Artikelnummer.", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearWarenkorb() {
        int confirmResult = JOptionPane.showConfirmDialog(frame, "Möchten Sie den Warenkorb wirklich leeren?", "Warenkorb Leeren", JOptionPane.YES_NO_OPTION);
        if (confirmResult == JOptionPane.YES_OPTION) {
            eshop.warenkorbLeeren();
            JOptionPane.showMessageDialog(frame, "Warenkorb erfolgreich geleert.", "Warenkorb Leeren", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private double calculateGesamtpreis(List<Artikel> warenkorb) {
        double gesamtpreis = 0;
        for (Artikel artikel : warenkorb) {
            gesamtpreis += artikel.getPreis() * artikel.getAnzahl();
        }
        return gesamtpreis;
    }

    private void kaufeArtikel(List<Warenkorb> warenkorb) {
        if (warenkorb.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Warenkorb ist leer.", "Warenkorb kaufen", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (Warenkorb artikel : warenkorb) {
        }


    }

    private void updateArtikellisteTable(List<Artikel> artikelliste) {

        if (artikellisteTable != null) {
            DefaultTableModel model = (DefaultTableModel) artikellisteTable.getModel();
            model.setRowCount(0); // Efface toutes les lignes de la table

            for (Artikel artikel : artikelliste) {
                Object[] rowData = {
                        artikel.getArtikelNummer(),
                        artikel.getBezeichnung(),
                        artikel.getPreis(),
                        artikel.getAnzahl(),
                        ""
                };

                if (artikel instanceof Massengut) {
                    int packungsgroesse = ((Massengut) artikel).getPackungsgroesse();
                    rowData = Arrays.copyOf(rowData, rowData.length + 1);
                    rowData[rowData.length - 1] = packungsgroesse;
                }

                model.addRow(rowData);
            }
        }
    }

    public void RechnunKlick() {
        boolean aktuellerKunde = eingeloggterBenutzer.istEingeloggt();
        List<Rechnung> rechnungenListe = eshop.getRechnungenListe();

        if (rechnungenListe.isEmpty()) {
            showMessageDialog(frame, "Keine Rechnungen bis jetzt.", "Rechnung erstellen", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder rechnungText = new StringBuilder();
        rechnungText.append("Rechnungen: \n\n");

        for (Rechnung rechnung : rechnungenListe) {
            if (rechnung.getKunde().equals(aktuellerKunde)) {
                rechnungText.append("Kunde: ").append(rechnung.getKunde().getName()).append("\n");
                rechnungText.append("Kaufdatum: ").append(rechnung.getDatum()).append("\n");

                List<Rechnung.Rechnungsposition> positionen = rechnung.getPositionen();
                double gesamtsumme = 0;


                for (Rechnung.Rechnungsposition position : positionen) {
                    Artikel artikel = position.getArtikel();
                    rechnungText.append("Artikelnummer: ").append(artikel.getArtikelNummer()).append("\n");
                    rechnungText.append("Bezeichnung: ").append(artikel.getBezeichnung()).append("\n");
                    rechnungText.append("Preis pro Stück: ").append(artikel.getPreis()).append(" Euro\n");
                    rechnungText.append("Stückzahl: ").append(artikel.getAnzahl()).append("\n");

                    double artikelSumme = artikel.getPreis() * position.getStueckzahl();
                    rechnungText.append("Gesamtsumme: ").append(artikelSumme).append(" Euro\n\n");
                    gesamtsumme += artikelSumme;

                    if (artikel instanceof Massengut) {
                        Massengut massengutartikel = (Massengut) artikel;
                        rechnungText.append("Packungsgröße: ").append(massengutartikel.getPackungsgroesse()).append("\n\n");
                    }
                }

                rechnungText.append("Gesamtsumme: ").append(gesamtsumme).append(" Euro\n");
                rechnungText.append("Kaufdatum: ").append(rechnung.getDatum()).append("\n");
            }
        }

        JTextArea rechnungTextArea = new JTextArea(rechnungText.toString());
        rechnungTextArea.setEditable(false);
        JScrollPane rechnungScrollPane = new JScrollPane(rechnungTextArea);
        showMessageDialog(frame, rechnungScrollPane, "Rechnungen", JOptionPane.PLAIN_MESSAGE);
    }

    public void SortierOptionKlick() {
        List<Artikel> artikelListe = eshop.gibAlleArtikel();
        if (artikelListe.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Keine Artikel vorhanden,", "Artikelliste", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Object[] sortOptions = {"Nach Artikelname", "Nach Bestand", "Nach Artikelnummer"};
            int selectedSortOption = JOptionPane.showOptionDialog(frame,
                    "Nach welchem Kriterium möchten Sie die Artikelliste sortieren?","Sortieren",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,
                    null,
                    sortOptions,
                    sortOptions[0]);

            switch (selectedSortOption) {
                case 0:
                    Collections.sort(artikelListe, Comparator.comparing(Artikel::getBezeichnung));
                    break;
                case 1:
                    Collections.sort(artikelListe, Comparator.comparingInt(Artikel::getAnzahl));
                    break;
                case 2:
                    Collections.sort(artikelListe, Comparator.comparingInt(Artikel::getArtikelNummer));
                    break;
            }

            updateArtikellisteTable(artikelListe);
        }
    }


    public void ArtikelsucheKlick() {
        String bezeichnung = JOptionPane.showInputDialog(frame, "Geben Sie den Suchbegriff ein:");

        if (bezeichnung != null && !bezeichnung.isEmpty()) {
            List<Artikel> suchergebnisse = (List<Artikel>) eshop.getArtikelByName(bezeichnung);

            if (suchergebnisse.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Keine Artikel gefunden.", "Suchergebnisse", JOptionPane.INFORMATION_MESSAGE);
            } else {
                DefaultTableModel searchResultModel = getArtikellisteTableModel();
                searchResultModel.setRowCount(0); // Supprimer toutes les lignes existantes

                for (Artikel artikel : suchergebnisse) {
                    Object[] rowData = {
                            artikel.getArtikelNummer(),
                            artikel.getBezeichnung(),
                            artikel.getPreis(),
                            artikel.getAnzahl(),
                            (artikel instanceof Massengut) ? ((Massengut) artikel).getPackungsgroesse() : ""
                    };
                    searchResultModel.addRow(rowData);
                }

                // Erstellung einen neuen JFrame, um die Suchergebnisse anzuzeigen.
                JFrame searchResultFrame = new JFrame("Suchergebnisse");
                JTable searchResultTable = new JTable(searchResultModel);
                searchResultTable.setEnabled(false);
                JScrollPane searchResultScrollPane = new JScrollPane(searchResultTable);

                searchResultFrame.add(searchResultScrollPane);
                searchResultFrame.pack();
                searchResultFrame.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Keine Suchbegriff eingegeben.", "Suchergebnisse", JOptionPane.INFORMATION_MESSAGE);
        }
    }




    private void showEreignisliste() {
        List<Ereignis> ereignisListe = eshop.getEreignisLite();

        if (ereignisListe.isEmpty()) {
            showMessageDialog(frame, "Keine Ereignisse vorhanden.", "Ereignisliste", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Ereignisse in ein zweidimensionales Array für die Tabelle konvertieren
            Object[][] data = new Object[ereignisListe.size()][5];
            for (int i = 0; i < ereignisListe.size(); i++) {
                Ereignis ereignis = ereignisListe.get(i);
                Integer mitarbeiternummer = (ereignis.getMitarbeiterId() != -1) ? ereignis.getMitarbeiterId() : null;
                data[i] = new Object[] { ereignis.getDateFormat(), ereignis.getArtikel().getArtikelNummer(), mitarbeiternummer, ereignis.getMenge(), ereignis.getBenutzer() };
            }

            String[] columnNames = { "Datum", "Artikelnummer", "Mitarbeiternummer", "Anzahl", "Kunde" };

            JTable ereignisTabelle = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(ereignisTabelle);

            JPanel optionsPanel = new JPanel(new FlowLayout());
            JButton sortButton = new JButton("Sortieren");
            //sortButton.addActionListener(e -> sortEreignisse(ereignisTabelle));
            JButton filterButton = new JButton("Filtern");
            //filterButton.addActionListener(e -> filterEreignisse(ereignisTabelle));
            optionsPanel.add(sortButton);
            optionsPanel.add(filterButton);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(optionsPanel, BorderLayout.SOUTH);

            showMessageDialog(frame, panel, "Ereignisliste", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private DefaultTableModel getArtikellisteTableModel() {
        return null;
    }




    }





