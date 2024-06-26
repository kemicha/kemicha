package src.UI.GUI;

import src.domain.EshopVerwaltung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class FileMenu extends JMenu implements ActionListener {
        private EshopVerwaltung shop = null;

        public FileMenu (EshopVerwaltung shop){
            super("Datei");

            this.shop = shop;

            JMenuItem item = new JMenuItem("Speichern");
            item.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
            item.addActionListener(this);
            add(item);

            item = new JCheckBoxMenuItem("Checkbox-Beispiel");
            item.addActionListener(this);
            add(item);

            addSeparator();

            item = new JMenuItem("Beenden");
            item.addActionListener(this);
            add(item);
        }



        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch(command) {
                case "Speichern":
                    try {
                        shop.speicherDaten();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;

                case "Checkbox-Beispiel":
                    System.out.println("Checkbox-Beispiel geklickt!");
                    break;

                case "Beenden":
                    System.exit(0);
                    break;

                default:
                    throw new IllegalArgumentException("Unbekanntes MenuItem!");
            }
        }
    }

