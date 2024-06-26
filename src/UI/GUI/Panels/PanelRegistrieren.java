package src.UI.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class PanelRegistrieren {


    public class panelRegistrieren extends JPanel {
        public JButton registrierenButton = new JButton("Registrieren");
        public JButton abbruchButton = new JButton("Abbrechen");
        // Button einloggen / ausloggen
        public JTextField registrierenNameText = new JTextField();
        public JLabel registrierenNameLabel = new JLabel("Benutzername:");
        public JTextField registrierenPasswortText = new JTextField();
        public JLabel registrierenPasswortLabel = new JLabel("Passwort:");
        public JTextField registrierenNummerText = new JTextField();
        public JLabel registrierenNummerLabel = new JLabel("Nummer:");
        public JTextField registrierenAdresseText = new JTextField();
        public JLabel registrierenAdresseLabel = new JLabel("Adresse:");

        public enum UserTyp{
            Mitarbeiter,
            Kunde
        }
        private UserTyp benutzer;

        public void resetData()
        {
            registrierenNameText.setText("");
            registrierenPasswortText.setText("");
            registrierenNummerText.setText("");
            registrierenAdresseText.setText("");
        }
        public panelRegistrieren(UserTyp user) {
            super(new GridLayout(10,2,5,5));
            Dimension d = new Dimension(1000,1200);
            setMinimumSize(d);
            benutzer = user;



            add(registrierenNameLabel);
            add(registrierenNameText);
            add(registrierenPasswortLabel);
            add(registrierenPasswortText);
            add(registrierenNummerLabel);
            add(registrierenNummerText);
            add(registrierenAdresseLabel);
            add(registrierenAdresseText);
            add(registrierenButton);
            add(abbruchButton);
        }

        ;

    }

}
