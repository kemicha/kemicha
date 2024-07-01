package src.UI.GUI;

import src.valueObjects.Artikel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModelGui extends AbstractTableModel {

    private List<Artikel> artikelList;
    private String[] spaltenNamen = {"ArtikelNummer", "Bezeichnung", "Preis", "Menge"};

    public JLabel bezeichnungLabel = new JLabel("Bezeichnung:");
    public JLabel artikelNummerLabel = new JLabel("Artikel Nummner:");
    public JLabel preisLabel = new JLabel("Preis:");
    public JLabel bestandLabel = new JLabel("Bestand/Menge:");
    public JLabel verfuegbarLabel = new JLabel("Verfugbar:");
    public JTextField bezeichnungTextField = new JTextField();
    public JSpinner artikelNummerSpinner = new JSpinner();
    public JTextField preisTextField = new JTextField();
    public JSpinner bestandSpinner = new JSpinner();
    public JTextField TextField = new JTextField();
    public JButton hinzufuegenButton = new JButton("Hinzufügen");
    public JButton reduzierenButton = new JButton("Reduzieren");

    public TableModelGui(List<Artikel> artikele) {
        this.artikelList = new ArrayList<>();
        artikelList.addAll(artikele);
    }

    public void setArtikels(List<Artikel> aktuelleArtikel) {
        artikelList.clear();
        artikelList.addAll(aktuelleArtikel);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return artikelList.size();
    }

    @Override
    public int getColumnCount() {
        return spaltenNamen.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Assuming you have a list of Artikel objects
        Artikel gewaehlteArtikel = artikelList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return gewaehlteArtikel.getArtikelNummer();
            case 1:
                return gewaehlteArtikel.getBezeichnung();
            case 2:
                return gewaehlteArtikel.getPreis();
            case 3:
                return gewaehlteArtikel.getBestand();
            default:
                return null;
        }
    }


    @Override
    public String getColumnName(int column) {
        return spaltenNamen[column];

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
            int aktuelleBestand = artikelList.get(index).getBestand();
            int neuerBestand = newArtikel.getBestand();
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
            int aktuelleBestand = artikelList.get(index).getBestand();
            int neuerBestand = newArtikel.getBestand();
            artikelList.get(index).setAnzahl(aktuelleBestand + neuerBestand);

            fireTableDataChanged();
        }
    }
    public void refresTable(){
        fireTableDataChanged();

    }

}
