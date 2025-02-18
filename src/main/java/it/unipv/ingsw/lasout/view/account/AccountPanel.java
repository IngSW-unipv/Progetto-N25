package it.unipv.ingsw.lasout.view.account;

import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel {

    // Campi di testo
    private JTextField oldPasswordField;
    private JTextField newPasswordField;
    private JTextField repeatNewPasswordField;

    // Bottoni
    private JButton changePasswordButton;
    private JButton deleteAccountButton;
    private JButton logoutButton;

    public AccountPanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.pink); // Sfondo blu

        // Inizializziamo i textfield
        oldPasswordField = new JTextField(35);
        newPasswordField = new JTextField(35);
        repeatNewPasswordField = new JTextField(35);

        // Se vuoi sfondo rosso per i textfield
        oldPasswordField.setBackground(Color.WHITE);
        newPasswordField.setBackground(Color.WHITE);
        repeatNewPasswordField.setBackground(Color.WHITE);

        // Inizializziamo i bottoni
        changePasswordButton = new JButton("cambia password");
        deleteAccountButton = new JButton("elimina account");
        logoutButton = new JButton("logout");

        // Se vuoi sfondo scuro per i bottoni
        changePasswordButton.setBackground(Color.DARK_GRAY);
        deleteAccountButton.setBackground(Color.red);
        logoutButton.setBackground(Color.red);

        // E testo chiaro, altrimenti nero su grigio potrebbe non vedersi bene
        changePasswordButton.setForeground(Color.WHITE);
        deleteAccountButton.setForeground(Color.WHITE);
        logoutButton.setForeground(Color.WHITE);

        // Prepara i constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // Una sola colonna

        // Riga 0: Label “vecchia password”
        gbc.gridy = 0;
        add(new JLabel("vecchia password"), gbc);

        // Riga 1: TextField per la vecchia password
        gbc.gridy = 1;
        add(oldPasswordField, gbc);

        // Riga 2: Label “nuova password”
        gbc.gridy = 2;
        add(new JLabel("nuova password"), gbc);

        // Riga 3: TextField per la nuova password
        gbc.gridy = 3;
        add(newPasswordField, gbc);

        // Riga 4: Label “ripeti password”
        gbc.gridy = 4;
        add(new JLabel("ripeti password"), gbc);

        // Riga 5: TextField per ripetere la nuova password
        gbc.gridy = 5;
        add(repeatNewPasswordField, gbc);

        // Riga 6: Bottone "cambia password"
        gbc.gridy = 6;
        add(changePasswordButton, gbc);

        // Riga 7: pannello con i due bottoni "elimina account" e "logout"
        gbc.gridy = 7;
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        bottomPanel.setBackground(Color.pink);
        bottomPanel.add(deleteAccountButton);
        bottomPanel.add(logoutButton);
        add(bottomPanel, gbc);
    }

    // Getter per i campi di testo (se servono in un Controller)
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
}
