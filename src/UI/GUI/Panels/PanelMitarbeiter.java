package src.UI.GUI.Panels;

import src.Exeptions.MitarbeiterExistiertBereitsException;
import src.domain.EshopVerwaltung;
import src.valueObjects.Kunde;
import src.valueObjects.Mitarbeiter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class PanelMitarbeiter extends JPanel {

        private Mitarbeiter mitarbeiter;
        private JFrame frame;
        private EshopVerwaltung shop;

        public void PanelEinloggen() {
            JFrame frame = new JFrame("Authentifizierung");
            frame.setSize(400, 300);
            frame.setLocation(0, 500);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            JPanel loginGui = new JPanel();
            frame.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JButton kundeBtn = new JButton("Kunde");
            JButton mitabeiterBtn = new JButton("Mitarbeiter");
            JButton registierenBtn = new JButton("Registrieren");


            mitabeiterBtn.addActionListener(e -> {
                PanelEinloggen();
                frame.dispose();
            });

            registierenBtn.addActionListener(e -> {
                onMitarbeiterRegistrierenKlick();
                frame.dispose();
            });

            kundeBtn.addActionListener(e -> {
                PanelEinloggen();
                frame.dispose();
            });

            loginGui.add(mitabeiterBtn);
            loginGui.add(kundeBtn);
            loginGui.add(registierenBtn);

            frame.add(loginGui, BoxLayout.Y_AXIS);
            frame.setSize(350, 300);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

        public void MitarbeiterVerwaltungKlick() {
            List<Mitarbeiter> mitarbeiterList = shop.gibAlleMitarbeiter();
            if (mitarbeiterList.isEmpty()) {
                showMessageDialog(frame, "Keine Mitarbeiter vorhanden!", "Mitarbeiterverwaltung", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JPanel panel = new JPanel(new GridLayout(mitarbeiterList.size(), 2));

                for (Mitarbeiter mitarbeiter : mitarbeiterList) {
                    JLabel benutzerNameLabel = new JLabel("BenutzerName: " + mitarbeiter.getName());
                    JLabel passwortLabel = new JLabel("Passwort: " + mitarbeiter.getPasswort());
                    JLabel NummerLabel = new JLabel("MitarbeiterNr: " + mitarbeiter.getId());
                    JButton bearbeitenButton = new JButton("Bearbeiten");
                    bearbeitenButton.addActionListener(e -> {
                        try {
                            BearbeiteMitabeiter(mitarbeiter);
                        } catch (MitarbeiterExistiertBereitsException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                    panel.add(benutzerNameLabel);
                    panel.add(bearbeitenButton);
                    panel.add(passwortLabel);
                    panel.add(new JLabel());
                    panel.add(NummerLabel);
                    panel.add(new JLabel());
                    panel.add(new JSeparator(SwingConstants.HORIZONTAL));
                    panel.add(new JSeparator(SwingConstants.HORIZONTAL));
                }

                JScrollPane scrollPane = new JScrollPane(panel);
                showMessageDialog(frame, scrollPane, "Mitarbeiterverwaltung", JOptionPane.PLAIN_MESSAGE);
            }
        }

        private void BearbeiteMitabeiter(Mitarbeiter mitarbeiter) throws MitarbeiterExistiertBereitsException {
            JTextField benutzerNameField = new JTextField(mitarbeiter.getName());
            JTextField passwortField = new JTextField(mitarbeiter.getPasswort());
            JTextField NummerField = new JTextField(mitarbeiter.getId());

            JPanel panel = new JPanel(new GridLayout(4, 2));
            panel.add(new JLabel("Name: "));
            panel.add(benutzerNameField);
            panel.add(new JLabel("Passwort:"));
            panel.add(passwortField);
            panel.add(new JLabel("Nummer:"));
            panel.add(NummerField);

            int result = showConfirmDialog(frame, panel, "Mitarbeiter bearbeitung", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String newBenutzerName = benutzerNameField.getText();
                String newPasswort = passwortField.getText();
                String newNummer = NummerField.getText();

                mitarbeiter.setName(newBenutzerName);
                mitarbeiter.setPasswort(newPasswort);
                mitarbeiter.setId(Integer.parseInt(newNummer));

                shop.NeueMitarbeiter(newBenutzerName,Integer.parseInt(newNummer),newPasswort);
               // String name, int id, String passwort
                showMessageDialog(frame, "Mitarbeiter erfolgreich aktualisiert.", "Mitarbeiterverwaltung", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        public void onMitarbeiterRegistrierenKlick() {
            JTextField benutzerNameField = new JTextField();
            JPasswordField passwortField = new JPasswordField();
            JTextField nummerField = new JTextField();

            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(new JLabel("BenutzerName:"));
            panel.add(benutzerNameField);
            panel.add(new JLabel("Passwort:"));
            panel.add(passwortField);
            panel.add(new JLabel("Nummer:"));
            panel.add(nummerField);

            int result = showConfirmDialog(frame, panel, "Mitarbeiter Registrierung", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String benutzerName = benutzerNameField.getText();
                char[] passwortChars = passwortField.getPassword();
                String passwort = new String(passwortChars);
                String id = nummerField.getText();

                try {
                    //Mitarbeiter mitarbeiter = null;
                    shop.NeueMitarbeiter(benutzerName, Integer.parseInt(id),passwort);
                    shop.speicherDaten();
                    showMessageDialog(frame, "Mitarbeiter erfolgreich registriert", "Mitarbeiter registrieren", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    showMessageDialog(frame, "Fehler bei der Registrierung: " + e.getMessage(), "Mitarbeiter registrieren", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        public void onKundeRegistrierenDurchMitarbeiterKlick() {
            JTextField benutzerNameField = new JTextField();
            JPasswordField passwortField = new JPasswordField();
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
                    Kunde Kunde = null;
                    shop.NeueKunde(name, Integer.parseInt(id),passwort,kundenAdresse);
                    shop.speicherDaten();
                    showMessageDialog(frame, "Kunde erfolgreich registriert", "Kunde registrieren", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    showMessageDialog(frame, "Fehler bei der Registrierung: " + e.getMessage(), "Kunde registrieren", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }



