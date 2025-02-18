package it.unipv.ingsw.lasout.view.account;

import it.unipv.ingsw.lasout.view.mainview.MainUIView;

import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel {

    // Campi di testo per le password
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField repeatNewPasswordField;

    // Bottoni
    private JButton changePasswordButton;
    private JButton deleteAccountButton;
    private JButton logoutButton;

    public AccountPanel(MainUIView mainUIView) {
        // Imposta il layout principale a BorderLayout
        setLayout(new BorderLayout());
        setBackground(Color.pink);

        // ----- Centro: cambio password -----
        // Creiamo un pannello centrale con GridBagLayout per centrare i componenti
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.pink);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // Una sola colonna

        // Inizializziamo i textfield
        oldPasswordField = new JPasswordField(35);
        newPasswordField = new JPasswordField(35);
        repeatNewPasswordField = new JPasswordField(35);

        // Imposta sfondo bianco per i textfield
        oldPasswordField.setBackground(Color.WHITE);
        newPasswordField.setBackground(Color.WHITE);
        repeatNewPasswordField.setBackground(Color.WHITE);

        // Inizializziamo il bottone per cambiare password
        changePasswordButton = new JButton("cambia password");
        changePasswordButton.setBackground(Color.DARK_GRAY);
        changePasswordButton.setForeground(Color.WHITE);

        // Aggiungiamo i componenti al centro
        gbc.gridy = 0;
        centerPanel.add(new JLabel("vecchia password"), gbc);

        gbc.gridy = 1;
        centerPanel.add(oldPasswordField, gbc);

        gbc.gridy = 2;
        centerPanel.add(new JLabel("nuova password"), gbc);

        gbc.gridy = 3;
        centerPanel.add(newPasswordField, gbc);

        gbc.gridy = 4;
        centerPanel.add(new JLabel("ripeti password"), gbc);

        gbc.gridy = 5;
        centerPanel.add(repeatNewPasswordField, gbc);

        gbc.gridy = 6;
        centerPanel.add(changePasswordButton, gbc);

        // Aggiungiamo il pannello centrale al centro dell'AccountPanel
        add(centerPanel, BorderLayout.CENTER);

        // ----- Basso: bottoni "elimina account" e "logout" -----
        // Creiamo un pannello per i bottoni in basso con FlowLayout
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(Color.pink);

        deleteAccountButton = new JButton("elimina account");
        logoutButton = new JButton("logout");

        // Impostazioni grafiche dei bottoni in basso
        deleteAccountButton.setBackground(Color.red);
        deleteAccountButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.red);
        logoutButton.setForeground(Color.WHITE);
        deleteAccountButton.setPreferredSize(new Dimension(150, 50));
        logoutButton.setPreferredSize(new Dimension(150, 50));

        bottomPanel.add(deleteAccountButton);
        bottomPanel.add(logoutButton);

        // Aggiungiamo il pannello dei bottoni in basso
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Getter per i campi di testo
    public String getOldPassword() {
        return oldPasswordField.getText();
    }

    public String getNewPassword() {
        return newPasswordField.getText();
    }

    public String getRepeatNewPassword() {
        return repeatNewPasswordField.getText();
    }

    // Metodi per registrare i listener ai bottoni
    public void addChangePasswordListener(java.awt.event.ActionListener al) {
        changePasswordButton.addActionListener(al);
    }

    public void addDeleteAccountListener(java.awt.event.ActionListener al) {
        deleteAccountButton.addActionListener(al);
    }

    public void addLogoutListener(java.awt.event.ActionListener al) {
        logoutButton.addActionListener(al);
    }

    public void clearFields() {
        oldPasswordField.setText("");
        newPasswordField.setText("");
        repeatNewPasswordField.setText("");
    }
}
