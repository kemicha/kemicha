package src.UI.GUI;

import src.UI.GUI.Panels.PanelArtikel;
import src.UI.GUI.Panels.PanelKunde;
import src.UI.GUI.Panels.PanelMitarbeiter;
import src.UI.GUI.Panels.PanelRegistrieren;
import src.domain.EshopVerwaltung;
import src.valueObjects.Kunde;
import src.valueObjects.Mitarbeiter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Gui extends JFrame {

    private EshopVerwaltung shop;
    private JFrame frame;

    private TableModelGui tmg;
    private JTable artikelTable;

    private PanelArtikel pa;
    private PanelMitarbeiter pm;
    private PanelKunde pk;
    private PanelRegistrieren pr;

    private Mitarbeiter eingeloggterMitarbeiter;
    private Kunde eingelogenkunde;

    public Gui(EshopVerwaltung shop) {
        this.shop = shop;
        this.artikelTable = new JTable();
        this.pa = new PanelArtikel(shop);
        this.pm = new PanelMitarbeiter(shop);
        this.pk = new PanelKunde(shop);
        this.eingeloggterMitarbeiter = null;
        this.eingelogenkunde = null;
        this.tmg = new TableModelGui(shop.gibAlleArtikel());
        this.pr= new PanelRegistrieren(shop);
        initialize();
    }

    public JPanel PanelEinloggen() {
        JFrame frame = new JFrame("Einloggen");
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.setLayout((new BorderLayout()));
   /*     frame.setSize(400, 300);
        frame.setLocation(0, 500);
        //frame.setLocationRelativeTo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);*/
        JPanel panel = new JPanel(new GridLayout(1,3));


        JButton kundeBtn = new JButton("Kunde");
        JButton mitabeiterBtn = new JButton("Mitarbeiter");
        JButton registierenBtn = new JButton("Registrieren");

        kundeBtn.setSize(400, 300);


        mitabeiterBtn.addActionListener(e -> {
            onMitarbeiterKlick();
            frame.dispose();
        });

        registierenBtn.addActionListener(e -> {
            pm. onMitarbeiterRegistrierenKlick();
            frame.dispose();

        });

        kundeBtn.addActionListener(e -> {
            onKundeKlick();
            frame.dispose();


        });

        panel.add(mitabeiterBtn);
        panel.add(kundeBtn);
        panel.add(registierenBtn);

        return panel;
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Eiloggen ");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        PanelMitarbeiter panelMitarbeiter = new PanelMitarbeiter(shop);
        JPanel loginPanel = PanelEinloggen();

        frame.add(loginPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }



    private void mitarbeiterMenu() {
        frame = new JFrame("Mitarbeiter Menü");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        JPanel jpanel = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.insets = new Insets(5, 5, 5, 5);

        artikelTable = new JTable(tmg);

        JScrollPane tableScrollPane = new JScrollPane(artikelTable);
        grid.gridx = 0;
        grid.gridy = 0;
        grid.gridwidth = 2;
        grid.weightx = 1.0;
        grid.weighty = 1.0;
        grid.fill = GridBagConstraints.BOTH;
        jpanel.add(tableScrollPane, grid);


        JButton suchenButton = new JButton("Suchen");
        JButton sortierenButton = new JButton("Sortieren");
        JButton kundenButton = new JButton("KundenLeitung");
        JButton mitarbeiterButton = new JButton("Mitarbeiterverwaltung");
        JButton mitarbeiterRegistrierenButton = new JButton("Mitarbeiter registrieren");
        JButton kundeRegistrierenDurchMitarbeiterButton = new JButton("Kunde registrieren");
        JButton logoutMitarbeiterButton = new JButton("Ausloggen");
        JButton artikelAnlegenButton = new JButton("Artikel anlegen");
        JButton artikelEinlagernButton = new JButton("Artikel einlagern");
        JButton artikelauslagerButton = new JButton("Artikel auslagern");
        JButton artikelEntfernButton = new JButton("Artikel entfernen");
        JButton ereignisButton = new JButton("EreignisListe");


        suchenButton.addActionListener(e -> pa.ArtikelsucheKlick());
        sortierenButton.addActionListener(e -> pa.SortierOptionKlick());
        kundenButton.addActionListener(e -> onKundeKlick());
        mitarbeiterButton.addActionListener(e -> onMitarbeiterKlick());
        mitarbeiterRegistrierenButton.addActionListener(e -> pm.onMitarbeiterRegistrierenKlick());
        kundeRegistrierenDurchMitarbeiterButton.addActionListener(e -> pm.onKundeRegistrierenDurchMitarbeiterKlick());
        logoutMitarbeiterButton.addActionListener(e -> logoutMitarbeiter());
        artikelAnlegenButton.addActionListener(e -> pa.artikelAnlegenKlick());
        artikelEinlagernButton.addActionListener(e -> pa.artikelEinlagernKlick());
        artikelauslagerButton.addActionListener(e -> pa.artikelauslagerKlick());
        artikelEntfernButton.addActionListener(e -> pa.entferneArtikelKlick());


        grid.gridwidth = 1;
        grid.weightx = 0;
        grid.weighty = 0;
        grid.fill = GridBagConstraints.HORIZONTAL;

        grid.gridy = 1;
        jpanel.add(suchenButton, grid);
        grid.gridy = 2;
        jpanel.add(sortierenButton, grid);
        grid.gridy = 3;
        jpanel.add(kundenButton, grid);
        grid.gridy = 4;
        jpanel.add(mitarbeiterButton, grid);
        grid.gridy = 5;
        jpanel.add(artikelAnlegenButton, grid);
        grid.gridy = 6;
        jpanel.add(artikelEinlagernButton, grid);
        grid.gridy = 7;
        jpanel.add(artikelauslagerButton, grid);
        grid.gridy = 8;
        jpanel.add(artikelEntfernButton, grid);
        grid.gridy = 9;
        jpanel.add(mitarbeiterRegistrierenButton, grid);
        grid.gridy = 10;
        jpanel.add(kundeRegistrierenDurchMitarbeiterButton, grid);
        grid.gridy = 11;
        jpanel.add(logoutMitarbeiterButton, grid);
        grid.gridy = 12;
        jpanel.add(ereignisButton, grid);


        frame.add(jpanel, BorderLayout.CENTER);
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

        boolean isValidCredentials = false;

        do {
            int result = JOptionPane.showConfirmDialog(frame, panel, "Mitarbeiter einloggen", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String name = benutzerNameField.getText();
                String passwort = new String(passwortField.getPassword());

                try {
                    if (shop.loginMitarbeiter(name, passwort)) {
                        isValidCredentials = true;
                        showMessageDialog(frame, "Mitarbeiter erfolgreich eingeloggt.", "Mitarbeiter einloggen", JOptionPane.INFORMATION_MESSAGE);
                        mitarbeiterMenu();
                    } else {
                        showMessageDialog(frame, "Ungültiger Benutzername oder Passwort.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    showMessageDialog(frame, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                logoutMitarbeiter();
            }
        } while (!isValidCredentials);
    }

    private void onKundeKlick() {
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
            result = JOptionPane.showConfirmDialog(frame, panel, "Kunde einloggen", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String name = benutzerNameField.getText();
                char[] passwortChars = passwortField.getPassword();
                String passwort = new String(passwortChars);

                try {
                    boolean kunde = shop.loginKunde(name, passwort);
                    if (kunde) {
                        isValidCredentials = true;
                        showMessageDialog(frame, "Kunde erfolgreich eingeloggt.", "Kunde einloggen", JOptionPane.INFORMATION_MESSAGE);
                        KundeMenu();
                    } else {
                        showMessageDialog(frame, "Ungültiger Benutzername oder Passwort.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    showMessageDialog(frame, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                logoutKunde();
            }

        } while (!isValidCredentials && result == JOptionPane.OK_OPTION);
    }

    public void KundeMenu() {
        JFrame kundenmenuFrame = new JFrame("Kundenmenü");
        kundenmenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());


        artikelTable = new JTable(tmg);

        artikelTable.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(artikelTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        JButton suchenButton = new JButton("Artikel suchen");
        JButton sortierenButton = new JButton("Artikel sortieren");
        JButton warenkorbButton = new JButton("Warenkorb");
        JButton rechnungButton = new JButton("Rechnung");
        JButton logoutButton = new JButton("Logout");
        JButton artikelHinzufuegenButton = new JButton("Artikel zum Warenkorb hinzufügen");

        buttonPanel.add(suchenButton);
        buttonPanel.add(sortierenButton);
        buttonPanel.add(warenkorbButton);
        buttonPanel.add(rechnungButton);
        buttonPanel.add(artikelHinzufuegenButton);
        buttonPanel.add(logoutButton);

        mainPanel.add(buttonPanel, BorderLayout.EAST);
        kundenmenuFrame.add(mainPanel);

        kundenmenuFrame.setSize(800, 600);
        kundenmenuFrame.setVisible(true);

        suchenButton.addActionListener(e -> pa.ArtikelsucheKlick());
        sortierenButton.addActionListener(e -> pa.SortierOptionKlick());
        warenkorbButton.addActionListener(e -> pa.WarenkorbKlick());
        rechnungButton.addActionListener(e -> pa.RechnunKlick());
        logoutButton.addActionListener(e -> {
            kundenmenuFrame.dispose();
            logoutKunde();
            PanelEinloggen();
        });
        artikelHinzufuegenButton.addActionListener(e -> pa.artikelZumWarenkorbHinzufuegenKlick());
    }

    private void showKundeRegistrierung() {
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
                Kunde kunde = shop.registierteKunde(Integer.valueOf(id), name, passwort, kundenAdresse);
                if (kunde != null) {
                    shop.speicherDaten();
                    eingelogenkunde = kunde;
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

    private void logoutKunde() {
        eingelogenkunde = null;
        showMessageDialog(frame, "Sie haben sich erfolgreich ausgeloggt.", "Abmeldung erfolgreich", JOptionPane.INFORMATION_MESSAGE);
        PanelEinloggen();
    }

    private void logoutMitarbeiter() {
        eingeloggterMitarbeiter = null;
        showMessageDialog(frame, "Sie haben sich erfolgreich ausgeloggt.", "Abmeldung erfolgreich", JOptionPane.INFORMATION_MESSAGE);
        PanelEinloggen();
    }

    private void initializeMenuBar() {
        JMenuBar mb = new JMenuBar();

        mb.add(new FileMenu(shop));


        setJMenuBar(mb);
    }
    public static void main(String[] args) throws IOException {
        EshopVerwaltung shop = new EshopVerwaltung("Eshop");
        new Gui(shop);
    }
}
