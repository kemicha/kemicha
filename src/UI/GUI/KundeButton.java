package src.UI.GUI;

import src.valueObjects.Artikel;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class KundeButton extends JPanel {
    private JTable kundeArtikelTable;
    JPanel kundeTaskPanel;
    public LogginGui kundeLoginGui;

    //public LoginGui.RegistrierungGui kundeRegistrierenGui;
    public TableModelGui artikelGui;

    public KundeButton(List<Artikel> artikels) {
        super(new BorderLayout());
        initializePanel(artikels);
    }

    public void initializePanel(List<Artikel> artikels) {
        kundeTaskPanel = new JPanel(new GridLayout(14, 2, 6, 14));
        Dimension d = new Dimension(1000, 1200);
        kundeTaskPanel.setMinimumSize(d);
        kundeLoginGui = new LogginGui();

        // Login GUI elements
        kundeTaskPanel.add(kundeLoginGui.loginNameLabel);
        kundeTaskPanel.add(kundeLoginGui.loginNameText);
        kundeTaskPanel.add(kundeLoginGui.loginPasswortLabel);
        kundeTaskPanel.add(kundeLoginGui.loginPasswortText);
        kundeTaskPanel.add(kundeLoginGui.einauslogenButton);




        // Artikel GUI elements
        artikelGui = new TableModelGui((Artikel) artikels);
        kundeTaskPanel.add(artikelGui.bezeichnungLabel);
        kundeTaskPanel.add(artikelGui.bezeichnungTextField);
        kundeTaskPanel.add(artikelGui.artikelNummerLabel);
        kundeTaskPanel.add(artikelGui.artikelNummerSpinner);
        kundeTaskPanel.add(artikelGui.preisLabel);
        kundeTaskPanel.add(artikelGui.preisTextField);
        kundeTaskPanel.add(artikelGui.bestandLabel);
        kundeTaskPanel.add(artikelGui.bestandSpinner);
        kundeTaskPanel.add(artikelGui.hinzufuegenButton);
        kundeTaskPanel.add(artikelGui.reduzierenButton);

        add(kundeTaskPanel, BorderLayout.WEST);

        // Create and populate the TableModel
        String[] spaltenNamen = {"artikelNummer", "Bezeichnung", "Bestand", "Preis"};
        TableModel model = new TableModel() {
            @Override
            public int getRowCount() {
                return artikels.size();
            }

            @Override
            public int getColumnCount() {
                return spaltenNamen.length;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return spaltenNamen[columnIndex];
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Integer.class;
                    case 3:
                        return Double.class;
                    default:
                        return Object.class;
                }
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Artikel artikel = artikels.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return artikel.getArtikelNummer();
                    case 1:
                        return artikel.getBezeichnung();
                    case 2:
                        return artikel.getAnzahl();
                    case 3:
                        return artikel.getPreis();
                    default:
                        return null;
                }
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                // Not needed since cells are not editable
            }

            @Override
            public void addTableModelListener(TableModelListener l) {
                // Not implemented
            }

            @Override
            public void removeTableModelListener(TableModelListener l) {
                // Not implemented
            }
        };

        kundeArtikelTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(kundeArtikelTable);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
