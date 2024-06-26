/*package src.UI.GUI.Panels;

import src.domain.EshopVerwaltung;
import src.valueObjects.Artikel;
import src.valueObjects.Kunde;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelHinzufuege extends JPanel {

    public interface HinzufuegenListener {
        void inArtikelEinfuegen(Artikel artikel);
    }

    private EshopVerwaltung shop = null;
    private HinzufuegenListener listener = null;

    private JTextField textfieldNummer = new JTextField();
    private JTextField textfieldTitel = new JTextField();
    private JButton hinzufuegenButton = new JButton("Hinzufügen");

    public PanelHinzufuege(EshopVerwaltung shop, HinzufuegenListener listener) {

        super(new GridBagLayout());
        this.shop = shop;
        this.listener = listener;

        JButton addArtikelButton = new JButton("Fuege Artikel hinzu");
        addArtikelButton.setBounds(10, 110, 150, 25);
        //panel.add(addArtikelButton);

        addArtikelButton.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                JFrame addArticleFrame = new JFrame("fuege Artikel hinzu");
                addArticleFrame.setSize(300, 200);
                JPanel addArticlePanel = new JPanel();
                addArticlePanel.setLayout(new GridLayout(5, 2));

                addArticlePanel.add(new JLabel("Bezeichnung:"));
                JTextField nameField = new JTextField();
                addArticlePanel.add(nameField);

                addArticlePanel.add(new JLabel("ArtikelNummer:"));
                JTextField numField = new JTextField();
                addArticlePanel.add(numField);

                addArticlePanel.add(new JLabel("Preis:"));
                JTextField priceField = new JTextField();
                addArticlePanel.add(priceField);

                addArticlePanel.add(new JLabel("Menge:"));
                JTextField qtyField = new JTextField();
                addArticlePanel.add(qtyField);

                JButton saveButton = new JButton("Speicher");
                addArticlePanel.add(saveButton);

                addArticleFrame.add(addArticlePanel);
                addArticleFrame.setVisible(true);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        addArticleFrame.dispose();
                    }
                });




    JButton addKundeButton = new JButton("Fuege neue Kunde hinzu");
            addKundeButton.setBounds(10, 110, 150, 25);
            //panel.add(addKundeButton);

    JButton viewKundenButton = new JButton("Kunde Liste");
            viewKundenButton.setBounds(170, 110, 150, 25);
           // panel.add(viewKundenButton);

            addKundeButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    JFrame addKundeFrame = new JFrame("Fuege Kunde Hinzu");
                    addKundeFrame.setSize(300, 200);
                    JPanel addKundePanel = new JPanel();
                    addKundePanel.setLayout(new GridLayout(3, 2));

                    addKundePanel.add(new JLabel("Name:"));
                    JTextField kundeNameField = new JTextField();
                    addKundePanel.add(kundeNameField);

                    addKundePanel.add(new JLabel("ID:"));
                    JTextField kundeIdField = new JTextField();
                    addKundePanel.add(kundeIdField);

                    addKundePanel.add(new JLabel("Passwort:"));
                    JTextField kundePasswortField = new JTextField();
                    addKundePanel.add(kundePasswortField);

                    addKundePanel.add(new JLabel("Adresse:"));
                    JTextField kundeAdresseField = new JTextField();
                    addKundePanel.add(kundeAdresseField);


                    JButton saveButton = new JButton("Einloggen");
                    addKundePanel.add(saveButton);

                    addKundeFrame.add(addKundePanel);
                    addKundeFrame.setVisible(true);

                    saveButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name = kundeNameField.getText();
                            int id = Integer.parseInt(kundeIdField.getText());
                            String passwort = kundePasswortField.getText();
                            String adresse = kundeAdresseField.getText();

                            Kunde kunde = new Kunde(name, id, passwort, adresse);
                            shop.NeueKunde(kunde);
                            addKundeFrame.dispose();
                        }
                    });


                    viewKundenButton.addActionListener(new ActionListener() {


                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame viewKundenFrame = new JFrame("Kunde Liste");
                            viewKundenFrame.setSize(400, 300);
                            JPanel viewKundenPanel = new JPanel();
                            viewKundenPanel.setLayout(new BorderLayout());

                            String[] columnNames = {"Name", " id", "passwort", "Adresse"};
                            List<Kunde> kundenListe = shop.gibAlleKunden();
                            String[][] data = new String[kundenListe.size()][3];
                            for (int i = 0; i < kundenListe.size(); i++) {
                                data[i][0] = kundenListe.get(i).getName();
                                data[i][1] = kundenListe.get(i).getId();
                                data[i][2] = kundenListe.get(i).getPasswort();
                                data[i][3] = kundenListe.get(i).getAdresse();

                            }

                            JTable kundenTable = new JTable(data, columnNames);
                            JScrollPane scrollPane = new JScrollPane(kundenTable);
                            viewKundenPanel.add(scrollPane, BorderLayout.CENTER);

                            viewKundenFrame.add(viewKundenPanel);
                            viewKundenFrame.setVisible(true);
                        }
                    });


                }*/
