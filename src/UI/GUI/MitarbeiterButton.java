package src.UI.GUI;

import src.domain.EshopVerwaltung;
import src.valueObjects.Artikel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterButton extends JPanel {

    private JTable artikelTable;
    private TableModelGui artikelGui;
    private JPanel taskPanel;
    private List<Artikel> artikelList;
    private EshopVerwaltung eshop;

    public LogginGui login;
    public JButton erhoehenButton = new JButton("Erhöhen");
    public JButton loeschenButton = new JButton("Löschen");

    public MitarbeiterButton(List<Artikel> artikels, EshopVerwaltung eshop) {
        super(new BorderLayout());
        this.eshop=eshop;

        artikelList = new ArrayList<>(artikels);

        taskPanel = new JPanel(new GridLayout(15, 2, 5, 10));
        Dimension d = new Dimension(500, 600);
        taskPanel.setMinimumSize(d);

        // Supposant que login est initialisé correctement ailleurs
        taskPanel.add(login.loginNameLabel);
        taskPanel.add(login.loginNameText);
        taskPanel.add(login.loginPasswortLabel);
        taskPanel.add(login.loginPasswortText);
        taskPanel.add(login.einauslogenButton);
        taskPanel.add(login.registrierenButton);

        // Initialisation de TableModelGui et JTable
        artikelGui = new TableModelGui(eshop.gibAlleArtikel());
        artikelGui.setArtikels(artikelList);
        taskPanel.add(new JLabel("Bezeichnung"));
        taskPanel.add(new JTextField());
        taskPanel.add(new JLabel("Artikel Nummer"));
        taskPanel.add(new JSpinner());
        taskPanel.add(new JLabel("Preis"));
        taskPanel.add(new JTextField());
        taskPanel.add(new JLabel("Bestand"));
        taskPanel.add(new JSpinner());
        taskPanel.add(erhoehenButton);
        taskPanel.add(artikelGui.reduzierenButton);
        taskPanel.add(artikelGui.hinzufuegenButton);
        taskPanel.add(loeschenButton);

        add(taskPanel, BorderLayout.WEST);

        // Initialisation de la JTable avec TableModelGui
        artikelTable = new JTable(artikelGui);

        add(new JScrollPane(artikelTable), BorderLayout.CENTER);

        setVisible(true);
    }

    public boolean updateTableModel(Artikel artikel) {
        if (artikel == null)
            return false;
        // Mettre à jour ou ajouter un article dans votre TableModelGui
        artikelGui.aktualisiereArtikelInfos(artikel);
        return true;
    }

    public void refreshTableModel() {
        artikelGui.refresTable();
    }
}
