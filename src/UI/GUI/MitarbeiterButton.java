package src.UI.GUI;

import src.valueObjects.Artikel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterButton extends JPanel {

    private JTable artikelTable;
    private TableModelGui tm;
    private JPanel taskPanel;
    private List<Artikel> artikelList;

    public LogginGui login;
    public TableModelGui artikelGui;
    public JButton erhoehenButton = new JButton("Erhöhen");
    public JButton loeschenButton = new JButton("Löschen");

    public MitarbeiterButton(List<Artikel> artikels) {
        super(new BorderLayout());

        artikelList = new ArrayList<>();
        artikelList.addAll(artikels);

        taskPanel = new JPanel(new GridLayout(15, 2, 5, 10));
        Dimension d = new Dimension(500, 600);
        taskPanel.setMinimumSize(d);

        // Assuming login is properly initialized somewhere else
        taskPanel.add(login.loginNameLabel);
        taskPanel.add(login.loginNameText);
        taskPanel.add(login.loginPasswortLabel);
        taskPanel.add(login.loginPasswortText);
        taskPanel.add(login.einauslogenButton);
        taskPanel.add(login.registrierenButton);

        // Artikel
        artikelGui = new TableModelGui(); //
        taskPanel.add(artikelGui.bezeichnungLabel);
        taskPanel.add(artikelGui.bezeichnungTextField);
        taskPanel.add(artikelGui.artikelNummerLabel);
        taskPanel.add(artikelGui.artikelNummerSpinner);
        taskPanel.add(artikelGui.preisLabel);
        taskPanel.add(artikelGui.preisTextField);
        taskPanel.add(artikelGui.bestandLabel);
        taskPanel.add(artikelGui.bestandSpinner);
        taskPanel.add(erhoehenButton);
        taskPanel.add(artikelGui.reduzierenButton);
        taskPanel.add(artikelGui.hinzufuegenButton);
        taskPanel.add(loeschenButton);

        add(taskPanel, BorderLayout.WEST);

        // Initialize the JTable with the TableModel
        artikelTable = new JTable(artikelGui);

        add(new JScrollPane(artikelTable), BorderLayout.CENTER);

        setVisible(true);
    }

    public boolean updateTableModel(Artikel artikel) {
        if (artikel == null)
            return false;
        // Update or add artikel in your TableModelGui
        tm.aktualisiereArtikelInfos(artikel);
        return true;
    }

    public void refreshTableModel() {
        tm.refresTable();
    }
}
