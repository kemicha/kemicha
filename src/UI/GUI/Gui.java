package src.UI.GUI;

import src.UI.GUI.Panels.PanelArtikel;
import src.UI.GUI.Panels.PanelKunde;
import src.UI.GUI.Panels.PanelMitarbeiter;
import src.domain.EshopVerwaltung;
import src.valueObjects.Kunde;
import src.valueObjects.Mitarbeiter;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Gui extends Container {


        private EshopVerwaltung shop;
        private JFrame frame;
        private TableModelGui tmg;
        private PanelArtikel pa;
        private PanelMitarbeiter pm;
        private PanelKunde pk;
        private JTable artikelTable;

        private Mitarbeiter eingeloggterMitarbeiter;
        private Kunde eingelogenkunde;

        public Gui(){
            this.shop = shop;
            this.artikelTable = artikelTable;
            this.pa = pa;
            this.pm = pm;
            this.eingelogenkunde = eingelogenkunde;
            this.eingeloggterMitarbeiter = eingeloggterMitarbeiter;
            this.tmg = tmg;
            initialize();
        }

        private void initialize(){
            if (pm != null)
                pm.PanelEinloggen();}

        private void MitabeiterMenu() {
            frame = new JFrame("Shopping_Shop");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel jpanel = new JPanel(new GridLayout());
            GridBagConstraints grid= new GridBagConstraints();
            grid.fill = GridBagConstraints.HORIZONTAL;
            grid.insets = new Insets(5,5,5,5);

            JTable ArtikelTableModel = new JTable((TableModel) artikelTable);


            JScrollPane tableScrollpane = new JScrollPane(ArtikelTableModel);
            jpanel.add(tableScrollpane, BorderLayout.CENTER );


            JButton suchenButton = new JButton(  "Suchen");
            JButton sortierenButton = new JButton(  "Sortieren") ;
            JButton  kundenButton = new JButton(  "KundenLeitung") ;
            JButton mitarbeiterButton = new JButton(  "Mitarbeiterverwaltung");
            JButton mitarbeiterRegistrierenButton = new JButton(  "Mitarbeiter registrieren");
            JButton kundeRegistrierenDurchMitarbeiterButton = new JButton (  "Kunde registrieren");
            JButton logoutMitarbeiterButton = new JButton(  "Ausloggen") ;
            JButton artikelAnlegenButton = new JButton("Artikel anlegen");
            JButton artikelEinlagernButton = new JButton("Artikel einlagern");
            JButton artikelauslagerButton = new JButton("Artikel auslager");
            JButton artikelEntfernButton = new JButton("Artikel Entfern");


            JButton ereignisButton = new JButton("EreingnisListe");

            suchenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pa.ArtikelsucheKlick();

                }
            });

            sortierenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pa.SortierOptionKlick();
                }
            });

            kundenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onKundeKlick();
                }
            });

            mitarbeiterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onMitarbeiterKlick();

                }
            });

            mitarbeiterRegistrierenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pm.onMitarbeiterRegistrierenKlick();
                }
            });

            kundeRegistrierenDurchMitarbeiterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pm.onKundeRegistrierenDurchMitarbeiterKlick();
                }
            });

            logoutMitarbeiterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pm.MitarbeiterVerwaltungKlick();
                }
            });

            artikelAnlegenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pa.artikelAnlegenKlick();
                }
            });

            artikelEinlagernButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pa.artikelEinlagernKlick();
                }
            });

            artikelauslagerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pa.artikelauslagerKlick();
                }
            });

            artikelEntfernButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pa.entferneArtikelKlick();

                }
            });


            //sortierung Menu
            grid.gridx = 0;
            grid.gridy = 0;
            add (suchenButton, grid) ;
            grid.gridx = 0;
            grid.gridy = 1;
            add(sortierenButton,grid);
            grid.gridx = 0;
            grid.gridy = 2;
            add (kundenButton, grid);
            grid.gridx = 0;
            grid.gridy = 3;
            add (mitarbeiterButton, grid);
            grid.gridx = 0;
            grid.gridy = 4;
            add(artikelAnlegenButton,grid);
            grid.gridx = 0;
            grid.gridy = 5;
            add(artikelEinlagernButton,grid);
            grid.gridx = 0;
            grid.gridy = 6;
            add (logoutMitarbeiterButton, grid) ;
            grid.gridx = 0;
            grid.gridy = 7;
            add (artikelAnlegenButton, grid) ;
            grid.gridx = 0;
            grid.gridy = 8;
            add (artikelauslagerButton, grid) ;
            grid.gridx = 0;
            grid.gridy = 9;
            add ( artikelEntfernButton, grid) ;
            grid.gridx = 0;
            grid.gridy = 10;
            add (suchenButton, grid) ;


            jpanel.add(jpanel,BorderLayout.WEST);
            frame.add(jpanel,BorderLayout.CENTER);

            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);

        }

        private void onMitarbeiterKlick() {
            JTextField benutzerNameField = new JTextField();
            JPasswordField passwortField = new JPasswordField();
            JPanel panel = new JPanel(new GridLayout(3, 2));
            panel.add(new JLabel("Benutzername"));
            panel.add(benutzerNameField);
            panel.add(new JLabel("Passwort"));
            panel.add(passwortField);

            int result;
            boolean isValidCredentials = false;

            do {
                result = JOptionPane.showConfirmDialog(frame, panel, "Mitarbeiter einloggen", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    String name = benutzerNameField.getText();
                    char[] passwortChars = passwortField.getPassword();
                    String passwort = new String(passwortChars);

                    try {
                        boolean mitarbeiter = shop.login(name, passwort);
                        if (mitarbeiter) {
                            shop. login(name,passwort);
                            isValidCredentials = true;
                            showMessageDialog(frame, "Mitarbeiter erfolgreich eingeloggt.", "Mitarbeiter einloggen", JOptionPane.INFORMATION_MESSAGE);
                            MitabeiterMenu();
                        } else {
                            showMessageDialog(frame, "Ungültiger Benutzername oder Passwort.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception e) {
                        showMessageDialog(frame, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    logoutMitarbeiter();
                }

            } while (!isValidCredentials && result == JOptionPane.OK_OPTION);
        }

        private void onKundeKlick() {
            JTextField benutzerNameField = new JTextField();
            JPasswordField passwortField = new JPasswordField(); // Utilisez JPasswordField pour les mots de passe
            JPanel panel = new JPanel(new GridLayout(3, 2));
            panel.add(new JLabel("Benutzername")); // Utilisez "Benutzername" au lieu de "name" pour l'étiquette
            panel.add(benutzerNameField);
            panel.add(new JLabel("Passwort"));
            panel.add(passwortField);

            int result;
            boolean isValidCredentials = false;

            do {
                result = JOptionPane.showConfirmDialog(frame, panel, "Kunde einloggen", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    String name = benutzerNameField.getText();
                    char[] passwortChars = passwortField.getPassword();
                    String passwort = new String(passwortChars);

                    try {
                        boolean kunde = shop.login(name, passwort);
                        if (kunde) {
                            shop.login (name,passwort);
                            isValidCredentials = true;
                            showMessageDialog(frame, "Kunde erfolgreich eingeloggt.", "Kunde einloggen", JOptionPane.INFORMATION_MESSAGE);
                            MitabeiterMenu(); // Assurez-vous que la méthode showMenu() existe pour afficher le menu du client
                        } else {
                            showMessageDialog(frame, "Ungültiger Benutzername oder Passwort.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception e) {
                        showMessageDialog(frame, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    logoutKunde(); // Assurez-vous que la méthode logoutKunde() existe pour gérer la déconnexion du client
                }

            } while (!isValidCredentials && result == JOptionPane.OK_OPTION);
        }

        public void KundeMenu() {
            JFrame kundenmenuFrame = new JFrame("Kundenmenü");
            kundenmenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel mainPanel = new JPanel(new BorderLayout());

            JTable artikellisteTable = new JTable();
            artikellisteTable.setEnabled(false);
            JScrollPane tableScrollPane = new JScrollPane(artikellisteTable);
            mainPanel.add(tableScrollPane, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new GridLayout(4, 1));

            JButton suchenButton = new JButton("Suchen");
            JButton sortierenButton = new JButton("Sortieren");
            JButton warenkorbButton = new JButton("Warenkorb anzeigen");
            JButton rechnungButton = new JButton("Rechnungen");
            JButton logoutButton = new JButton("Ausloggen");
            JButton artikelHinzufuegenButton = new JButton("artikelZumWarenkorbHinzufuegen");

            buttonPanel.add(suchenButton);
            buttonPanel.add(sortierenButton);
            buttonPanel.add(warenkorbButton);
            buttonPanel.add(rechnungButton);
            buttonPanel.add(logoutButton);

            mainPanel.add(buttonPanel, BorderLayout.EAST);

            kundenmenuFrame.add(mainPanel);
            kundenmenuFrame.pack();
            kundenmenuFrame.setVisible(true);


            suchenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pa.ArtikelsucheKlick();
                }
            });
            sortierenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pa.SortierOptionKlick();
                }
            });

            warenkorbButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pa.WarenkorbKlick();
                }
            });

            rechnungButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pa.RechnunKlick();
                }
            });

            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    kundenmenuFrame.dispose();
                    logoutKunde();
                    pm.PanelEinloggen();
                }
            });

            artikelHinzufuegenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pa.artikelZumWarenkorbHinzufuegenKlick();
                }
            });

            buttonPanel.add (suchenButton) ;
            buttonPanel.add(sortierenButton);
            buttonPanel.add(artikelHinzufuegenButton);
            buttonPanel.add (warenkorbButton) ;
            buttonPanel.add (rechnungButton) ;
            buttonPanel.add(logoutButton);

            mainPanel. add (buttonPanel, BorderLayout.WEST) ;
            kundenmenuFrame. add(mainPanel);
            kundenmenuFrame.pack();
            kundenmenuFrame. setVisible(true);



        }

        private void showKundeRegistrierung() {
            JTextField benutzerNameField = new JTextField();
            JPasswordField passwortField = new JPasswordField(); // Utilisez JPasswordField pour les mots de passe
            JTextField nummerField = new JTextField();
            JTextField kundenAdresseField = new JTextField();

            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(new JLabel("BenutzerName:"));
            panel.add(benutzerNameField);
            panel.add(new JLabel("Passwort:"));
            panel.add(passwortField);
            panel.add(new JLabel("Nummer:"));
            panel.add(nummerField);
            panel.add(new JLabel("Kundenadresse:"));
            panel.add(kundenAdresseField);

            int result = showConfirmDialog(frame, panel, "Kunde registrieren", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String name = benutzerNameField.getText();
                char[] passwortChars = passwortField.getPassword();
                String passwort = new String(passwortChars);
                String id = nummerField.getText();
                String kundenAdresse = kundenAdresseField.getText();

                try {
                    Kunde kunde = shop.registierteKunde(Integer.valueOf(id),name, passwort,  kundenAdresse);
                    if (kunde != null) {
                        shop.speicherDaten();
                        Kunde eingelogterKunde = kunde;
                        KundeMenu();
                    } else {
                        showMessageDialog(frame, "Fehler bei der Registrierung", "Kunde registrieren", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    showMessageDialog(frame, "Fehler bei der Registrierung: " + e.getMessage(), "Kunde registrieren", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                showMessageDialog(frame, "Registrierung abgebrochen", "Kunde registrieren", JOptionPane.INFORMATION_MESSAGE);
            }
        }










   /* StringBuilder rechnungText = new StringBuilder();
    rechnungText.append("Rechnung: \n\n");
    double gesamtsumme = 0;
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    String kaufDatum = dateFormat.format(new Date());

      for (Artikel artikel) {
        rechnungText.append("Artikelnummer: ").append(artikel.getArtikelNummer()).append("\n");
        rechnungText.append("Bezeichnung: ").append(artikel.getBezeichnung()).append("\n");
        rechnungText.append("Preis pro Stück: ").append(artikel.getPreis()).append(" Euro\n");
        rechnungText.append("Stückzahl: ").append(artikel.getStueckzahl()).append("\n");

        double artikelSumme = artikel.getPreis() * artikel.getStueckzahl();
        rechnungText.append("Gesamtsumme: ").append(artikelSumme).append(" Euro\n\n");
        gesamtsumme += artikelSumme;

        if (artikel instanceof Artikel.Massengut) {
            Artikel.Massengut massengutartikel = (Artikel.Massengut) artikel;
            rechnungText.append("Packungsgröße: ").append(massengutartikel.getPackungsgroesse()).append("\n\n");
        }
    }

rechnungText.append("Gesamtsumme: ").append(gesamtsumme).append(" Euro\n");
rechnungText.append("Kaufdatum: ").append(kaufDatum).append("\n");

    JTextArea rechnungTextArea = new JTextArea(rechnungText.toString());
rechnungTextArea.setEditable(false);
    JScrollPane rechnungScrollPane = new JScrollPane(rechnungTextArea);
    showMessageDialog(frame, rechnungScrollPane, "Rechnung", JOptionPane.PLAIN_MESSAGE);

    private Kunde eingelogenkunde;
    // Créez et ajoutez la facture à votre système
    RechnungObjekt rechnung = new RechnungObjekt(eingelogenkunde, LocalDate.now(), gesamtsumme);
eshop.addRechnung(rechnung);*/






        private void logoutKunde() {
            eingelogenkunde = null;
            showMessageDialog(frame, "Sie haben sich erfolgreich ausgeloggt.", "Abmeldung erfolgreich", JOptionPane.INFORMATION_MESSAGE);
            pm.PanelEinloggen();
        }

        private void logoutMitarbeiter() {
            eingeloggterMitarbeiter = null;
            showMessageDialog(frame, "Sie haben sich erfolgreich ausgeloggt.", "Abmeldung erfolgreich", JOptionPane.INFORMATION_MESSAGE);
            pm.PanelEinloggen();
        }

        public static void main (String[]args) throws IOException {
            EshopVerwaltung shop = new EshopVerwaltung("SoSe24_Prog2_B_Gruppe13a");
            Gui gui = new Gui();


        }
    }




