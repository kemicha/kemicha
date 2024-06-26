package src.UI.GUI.Panels;

import src.domain.EshopVerwaltung;
import src.valueObjects.Artikel;
import src.valueObjects.Kunde;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;


public class PanelKunde extends JPanel {


        public interface KundenserviceListener {



            void onKundenservice(java.util.List<Artikel> liste, int a);


        }

        private JFrame frame;
        private EshopVerwaltung shop ;
        private KundenserviceListener listener = null;
        private void onKundeVerwaltungKlick() {
            List<Kunde> kundeListe = shop.gibAlleKunden();
            if (kundeListe.isEmpty()) {
                showMessageDialog(frame, "Keine Kunden vorhanden!", "Kundenverwaltung", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JPanel panel = new JPanel(new GridLayout(kundeListe.size(), 2));
                for (Kunde kunde : kundeListe) {
                    JLabel benutzerNameLabel = new JLabel("BenutzerName: " + kunde.getName());
                    JLabel passwortLabel = new JLabel("Passwort: " + kunde.getPasswort());
                    JLabel nummerLabel = new JLabel("KundeNr: " + kunde.getId());
                    JLabel adresseLabel = new JLabel("Adresse: " + kunde.getAdresse());
                    JButton bearbeitenButton = new JButton("Bearbeiten");
                    bearbeitenButton.addActionListener(e -> {
                        try {
                            BearbeiteKunde(kunde);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                    panel.add(benutzerNameLabel);
                    panel.add(bearbeitenButton);
                    panel.add(passwortLabel);
                    panel.add(new JLabel());
                    panel.add(nummerLabel);
                    panel.add(new JLabel());
                    panel.add(adresseLabel);
                    panel.add(new JLabel());
                    panel.add(new JSeparator(SwingConstants.HORIZONTAL));
                    panel.add(new JSeparator(SwingConstants.HORIZONTAL));
                }
                JScrollPane scrollPane = new JScrollPane(panel);
                showMessageDialog(frame, scrollPane, "Kundenverwaltung", JOptionPane.PLAIN_MESSAGE);
            }
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
                String benutzerName = benutzerNameField.getText();
                char[] passwortChars = passwortField.getPassword();
                String passwort = new String(passwortChars);
                String id = nummerField.getText();
                String kundenAdresse = kundenAdresseField.getText();

                try {
                    Kunde kunde = shop.registierteKunde(Integer.valueOf(id),benutzerName, passwort,  kundenAdresse);
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

        private void KundeMenu() {
        }


        private void BearbeiteKunde(Kunde kunde) throws IOException {
            JTextField benutzerNameField = new JTextField(kunde.getName());
            JTextField passwortField = new JTextField(kunde.getPasswort());
            JTextField nummerField = new JTextField(kunde.getId());
            JTextField kundenAdresseField = new JTextField(kunde.getAdresse());

            JPanel panel = new JPanel(new GridLayout(4, 2));
            panel.add(new JLabel("Name: "));
            panel.add(benutzerNameField);
            panel.add(new JLabel("Passwort:"));
            panel.add(passwortField);
            panel.add(new JLabel("Nummer:"));
            panel.add(nummerField);
            panel.add(new JLabel("Adresse: "));
            panel.add(kundenAdresseField);

            int result = showConfirmDialog(frame, panel, "Kunde bearbeiten", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String newBenutzerName = benutzerNameField.getText();
                String newPasswort = passwortField.getText();
                String newNummer = nummerField.getText();
                String newKundenAdresse = kundenAdresseField.getText();

                kunde.setName(newBenutzerName);
                kunde.setPasswort(newPasswort);
                kunde.setId(Integer.parseInt(newNummer));
                kunde.setAdresse(newKundenAdresse);

                showMessageDialog(frame, "Kunde erfolgreich aktualisiert", "Kundenverwaltung", JOptionPane.INFORMATION_MESSAGE);

                shop.speicherDaten();
            }
        }





        private JButton kaufenButton = new JButton("Kaufen");
        private JButton warenkorbButton = new JButton("Warenkorb");
        private JButton infoBenutzerButton = new JButton("Info Benutzer");
        private JButton auslistenButton = new JButton("Artikel auslisten (A-Z)");

        public void PanelKundenservice(EshopVerwaltung shop, KundenserviceListener listener) {
         //   super (new FlowLayout());
            this.shop = shop;
            this.listener = listener;





            add(auslistenButton); //1
            add(warenkorbButton); //2
            add(kaufenButton); //3
            add(infoBenutzerButton); //4

            auslistenButton.addActionListener (e -> {
                java.util.List<Artikel> ergebnisListe = shop.gibAlleArtikel();

                listener.onKundenservice(ergebnisListe, 1);
            });

            warenkorbButton.addActionListener (e -> {
                listener.onKundenservice((java.util.List<Artikel>) null, 2);
            });

            kaufenButton.addActionListener (e -> {
                listener.onKundenservice((java.util.List<Artikel>) null, 3);
            });

            infoBenutzerButton.addActionListener (e -> {
                listener.onKundenservice((java.util.List<Artikel>) null, 4);
            });


            setBorder(BorderFactory.createTitledBorder("Kundenservice"));
        }

    }


