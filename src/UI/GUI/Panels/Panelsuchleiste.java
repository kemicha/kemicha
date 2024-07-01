package src.UI.GUI.Panels;

import src.domain.EshopVerwaltung;
import src.valueObjects.Artikel;

import javax.swing.*;
import java.awt.*;

public class Panelsuchleiste extends JPanel {

    public interface SucheListener {
        void onGesucht(java.util.List<Artikel> suchErgebnisListe);
    }

    private EshopVerwaltung eshopVerwaltung;
    private  SucheListener listener;
    private JTextField suchTextFeld = new JTextField();
    private JButton suchenButton = new JButton("Suche");

    public Panelsuchleiste(EshopVerwaltung eshopVerwaltung, SucheListener listener){
        super(new FlowLayout());
        this.eshopVerwaltung = eshopVerwaltung;
        this.listener= listener;
        add(new JLabel("Suchbegriff:"));
        suchTextFeld.setPreferredSize(new Dimension(200, 30)); // Wichtig, wird sonst zusammengedrückt
        add(suchTextFeld);
        add(suchenButton);

        // v6: Jetzt mit Lambda, anstatt mit Mitgliedsklasse
        suchenButton.addActionListener(e -> {
            String titel = suchTextFeld.getText();
            java.util.List<Artikel> ergebnisListe = eshopVerwaltung.sucheArtikelNachName(titel);

            if(ergebnisListe.isEmpty())
                ergebnisListe = eshopVerwaltung.gibAlleArtikel();

            listener.onGesucht(ergebnisListe);
        });

        // Rahmen definieren
        setBorder(BorderFactory.createTitledBorder("Suchen"));

    }

}
