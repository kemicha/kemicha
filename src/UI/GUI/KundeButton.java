package src.UI.GUI;

import src.domain.EshopVerwaltung;
import src.valueObjects.Artikel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KundeButton extends JPanel {

    private JTable artikelTable;
    private TableModelGui artikelGui;
    private JPanel taskPanel;
    private LogginGui loginGui;
    private EshopVerwaltung shop;


    public KundeButton(List<Artikel> artikels, EshopVerwaltung shop) {
        super(new BorderLayout());
        this.shop= shop;

        taskPanel = new JPanel(new GridLayout(14,2,6,14));
        Dimension d = new Dimension(1000,1200);
        taskPanel.setMinimumSize(d);
        loginGui = new LogginGui();

        taskPanel.add(loginGui.loginNameLabel);
        taskPanel.add(loginGui.loginNameText);
        taskPanel.add(loginGui.loginPasswortLabel);
        taskPanel.add(loginGui.loginPasswortText);
        taskPanel.add(loginGui.einauslogenButton);


        // Artikel
        artikelGui = new TableModelGui(shop.gibAlleArtikel());
        taskPanel.add(artikelGui.bezeichnungLabel);
        taskPanel.add(artikelGui.bezeichnungTextField);
        taskPanel.add(artikelGui.artikelNummerLabel);
        taskPanel.add(artikelGui.artikelNummerSpinner);
        taskPanel. add(artikelGui.preisLabel);
        taskPanel.add(artikelGui.preisTextField);
        taskPanel. add(artikelGui.bestandLabel);
        taskPanel. add(artikelGui.bestandSpinner);
        taskPanel. add(artikelGui.hinzufuegenButton);
        taskPanel. add(artikelGui.reduzierenButton);

        add(taskPanel, BorderLayout.WEST);
        String spaltenNamen[]= {"Id","Bezeichnung","Bestand","Preis"};
        ArtikelTableModel model = new ArtikelTableModel();
        JTable KundeButton = new JTable(model);
        add(KundeButton, BorderLayout.CENTER);
        setVisible(true);
    }




}
