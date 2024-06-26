package src.UI.GUI;

import src.domain.EshopVerwaltung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ShopGui extends Container {

        private EshopVerwaltung shop;
        private JFrame frame;
        private JTextField nameField;
        private JPasswordField passwordField;
        private JLabel statusLabel;

        public ShopGui() throws IOException {
            this.shop= shop;
            this.frame = new JFrame();
            this.nameField = new JTextField();
            this.passwordField = new JPasswordField();
            this.statusLabel = new JLabel();
            initialize();

        }

        public void initialize() {
            frame = new JFrame("EShop");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new BorderLayout());

            JPanel panel = new JPanel();
            frame.add(panel, BorderLayout.CENTER);
            ;

            statusLabel = new JLabel("Einloggen");
            frame.add(statusLabel, BorderLayout.SOUTH);

            frame.setVisible(true);
        }

        public void placeComponents(JPanel panel) {
            panel.setLayout(null);

            JLabel nameLabel = new JLabel("Name:");
            nameLabel.setBounds(10, 20, 80, 25);
            panel.add(nameLabel);

            nameField = new JTextField(20);
            nameField.setBounds(100, 20, 160, 25);
            panel.add(nameField);

            JLabel passwordLabel = new JLabel("Passwort:");
            passwordLabel.setBounds(10, 50, 80, 25);
            panel.add(passwordLabel);

            passwordField = new JPasswordField(20);
            passwordField.setBounds(100, 50, 160, 25);
            panel.add(passwordField);

            JButton loginButton = new JButton("Einloggen");
            loginButton.setBounds(10, 80, 150, 25);
            panel.add(loginButton);

            JButton logoutButton = new JButton("ausloggen");
            logoutButton.setBounds(170, 80, 150, 25);
            panel.add(logoutButton);

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String password = new String(passwordField.getPassword());
                    if (shop.login(name, password)) {
                        statusLabel.setText("Online : " + name);
                    } else {
                        statusLabel.setText("loggin nicht möglich.");
                    }
                }
            });

            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    shop.logout();
                    statusLabel.setText("Logout.");
                }
            });
        }

        public void main(String[] args) throws IOException {
            new ShopGui();
        }
    }


