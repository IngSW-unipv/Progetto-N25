package it.unipv.ingsw.lasout.view.account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignInView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton registerButton;
    private JButton loginButton;

    public SignInView() {
        setTitle("Registrazione");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 260);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Username:"), gbc);
        usernameField = new JTextField(15);
        gbc.gridx = 1;
        mainPanel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(15);
        gbc.gridx = 1;
        mainPanel.add(emailField, gbc);

        // Pannello dei bottoni
        JPanel buttonPanel = new JPanel();
        registerButton = new JButton("Sign in");
        // Imposta dimensioni più grandi
        registerButton.setPreferredSize(new Dimension(140, 40));
        // Se desideri anche un testo più grande
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton = new JButton("Login");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);

        //almeno premendo INVIO fa subito la registrazione senza cliccare il pulsante col mouse
        getRootPane().setDefaultButton(registerButton);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getEmail() {
        return emailField.getText();
    }

    public void addRegisterListener(ActionListener al) {
        registerButton.addActionListener(al);
    }

    public void addLoginListener(ActionListener al) {
        loginButton.addActionListener(al);
    }

    public void clearFields() {
        usernameField.setText("");
        emailField.setText("");
        passwordField.setText("");
    }
}
