package src.UI.GUI.Panels;

import src.domain.EshopVerwaltung;
import src.valueObjects.Artikel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelSuchschleife extends JPanel {


        public interface SucheListener {
            void onGesucht(List<Artikel> suchErgebnisListe);
        }

        private EshopVerwaltung shop = null;
        private SucheListener listener = null;

        private JTextField suchTextFeld = new JTextField();
        private JButton suchenButton = new JButton("Suche");

        public PanelSuchschleife (EshopVerwaltung shop, SucheListener listener) {
            super(new FlowLayout());
            this.shop = shop;
            this.listener = listener;

            add(new JLabel("Suchbegriff:"));
            suchTextFeld.setPreferredSize(new Dimension(200, 30)); // Wichtig, wird sonst zusammengedrückt
            add(suchTextFeld);
            add(suchenButton);


            suchenButton.addActionListener(e -> {
                String bezeichnung = suchTextFeld.getText();
                java.util.List<Artikel> ergebnisListe = shop.gibAlleArtikel();

                if(ergebnisListe.isEmpty())
                    ergebnisListe = shop.gibAlleArtikel();

                listener.onGesucht(ergebnisListe);
            });

            setBorder(BorderFactory.createTitledBorder("Suchen Artikel"));
        }
    }


