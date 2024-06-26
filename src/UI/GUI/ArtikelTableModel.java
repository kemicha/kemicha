package src.UI.GUI;


import src.domain.EshopVerwaltung;
import src.valueObjects.Artikel;
import src.valueObjects.Massengut;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class ArtikelTableModel extends AbstractTableModel {

    private List<Artikel> artikelList;
    private Frame frame;
    private EshopVerwaltung shop;
    private  String[] spaltenNames = {"artikelNummer", "bezeichnung", "Bestand", "Preis"};


    private void sortEreignisse(JTable ereignisTabelle) {
        Object[] options = { "Datum", "Artikelnummer", "Mitarbeiternummer", "Anzahl", "Kunde" };
        Object selectedOption = JOptionPane.showInputDialog(frame,
                "Nach welchen Kriterium soll sortiert werden?",
                "Sortieren", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (selectedOption != null) {
            int columnIndex = -1;
            if (selectedOption.equals("Datum")) {
                columnIndex = 0;
            } else if (selectedOption.equals("Artikelnummer")) {
                columnIndex = 1;
            } else if (selectedOption.equals("Mitarbeiternummer")) {
                columnIndex = 2;
            } else if (selectedOption.equals("Anzahl")) {
                columnIndex = 3;
            } else if (selectedOption.equals("Kunde")) {
                columnIndex = 4;
            }

            if (columnIndex != -1) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(ereignisTabelle.getModel());
                sorter.toggleSortOrder(columnIndex);
                ereignisTabelle.setRowSorter(sorter);
            }
        }
    }

    private void filterEreignisse(JTable ereignisTabelle) {
        Object[] options = { "Artikelnummer", "Mitarbeiternummer", "Kunde" };
        Object selectedOption = JOptionPane.showInputDialog(frame,
                "Nach welchem Kriterium soll gefiltert werden?",
                "Filtern", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (selectedOption != null) {
            String filterValue = JOptionPane.showInputDialog(frame,
                    "Geben Sie den Filterwert ein:",
                    "Filtern", JOptionPane.QUESTION_MESSAGE);

            if (filterValue != null) {
                int columnIndex = -1;
                if (selectedOption.equals("Artikelnummer")) {
                    columnIndex = 1;
                } else if (selectedOption.equals("Mitarbeiternummer")) {
                    columnIndex = 2;
                } else if (selectedOption.equals("Kunde")) {
                    columnIndex = 4;
                }

                if (columnIndex != -1) {
                    // Filtern
                    TableRowSorter<TableModel> sorter = new TableRowSorter<>(ereignisTabelle.getModel());
                    sorter.setRowFilter(RowFilter.regexFilter(filterValue, columnIndex));
                    ereignisTabelle.setRowSorter(sorter);
                }
            }
        }
    }


    private DefaultTableModel getArtikellisteTableModel() {
        List<Artikel> artikelListe = shop.gibAlleArtikel();
        String[] columnNames = {"Artikelnummer", "Bezeichnung", "Preis", "Bestand", "Packungsgroesse"};


        Object[][] data = new Object[artikelListe.size()][5];


        for (int i = 0; i < artikelListe.size(); i++) {
            Artikel artikel = artikelListe.get(i);
            data[i][0] = artikel.getArtikelNummer();
            data[i][1] = artikel.getBezeichnung();
            data[i][2] = artikel.getPreis();
            data[i][3] = artikel.getAnzahl();

            if (artikel instanceof Massengut) {
                data[i][4] = ((Massengut) artikel).getPackungsgroesse();
            } else {
                data[i][4] = "";
            }
        }


        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        return model;
    }























    public ArtikelTableModel(){
        List<Artikel> aktuelleArtikelList = null;
        this.artikelList = aktuelleArtikelList;
    }

    public void setArtikel(List<Artikel> aktuelleArtikelList){
        artikelList.clear();
        // fill model
        artikelList.addAll(aktuelleArtikelList);
        fireTableDataChanged();

    }

    public List<Artikel> getArtikelList() {
        return artikelList;
    }

    @Override
    public String getColumnName(int column) {
        return spaltenNames[column];
    }

    @Override
    public int getRowCount() {
        if (artikelList != null)
            return artikelList.size();
        else
            return 0;
    }

    @Override
    public int getColumnCount() {
        return spaltenNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Artikel gewaehlterArtikel = artikelList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return gewaehlterArtikel.getArtikelNummer();
            case 1:
                return gewaehlterArtikel.getBezeichnung();
            case 2:
                return gewaehlterArtikel.getAnzahl();
            case 3:
                return String.format("%.1f%n", gewaehlterArtikel.getPreis());
            default:
                return null;
        }
    }

    private int indexOfArtikel(Artikel artikel)
    {
        for (int i =0; i< artikelList.size(); i++)
        {
            if (artikelList.get(i).getBezeichnung().equals(artikel.getBezeichnung())) {
                return i;
            }
        }

        return -1;
    }
    public void aktualisiereArtikelInfos(Artikel newArtikel)
    {
        // check if artkel exist.
        int index = indexOfArtikel(newArtikel);
        if (index == -1 ) // Artikel existiert nicht
        {
            artikelList.add(newArtikel);
        }
        else{
            artikelList.get(index).setArtikel(newArtikel);
        }

        fireTableDataChanged();
    }
    public void reduziereArtikelBestand(Artikel newArtikel){
        int index = indexOfArtikel(newArtikel);
        if (index != -1 ) // Artikel existiert nicht
        {
            int aktuelleBestand = artikelList.get(index).getAnzahl();
            int neuerBestand = newArtikel.getAnzahl();
            if (neuerBestand > aktuelleBestand)
                artikelList.get(index).setAnzahl(0);
            else
                artikelList.get(index).setAnzahl(aktuelleBestand - neuerBestand);

            fireTableDataChanged();
        }
    }
    public void erhoeheArtikelBestand(Artikel newArtikel){
        int index = indexOfArtikel(newArtikel);
        if (index != -1 ) // Artikel existiert nicht
        {
            int aktuelleBestand = artikelList.get(index).getAnzahl();
            int neuerBestand = newArtikel.getAnzahl();
            artikelList.get(index).setAnzahl(aktuelleBestand + neuerBestand);

            fireTableDataChanged();
        }
    }
    public void refresTable(){
        fireTableDataChanged();

    }

    public Object getModel() {
        return this;
    }
}

