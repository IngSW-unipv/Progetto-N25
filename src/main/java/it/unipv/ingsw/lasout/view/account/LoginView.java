package it.unipv.ingsw.lasout.view.account;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginView() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 220);
        setLocationRelativeTo(null);

        // Pannello principale con GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Username or email:"), gbc);

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

        // Pannello dei bottoni
        JPanel buttonPanel = new JPanel();
        loginButton = new JButton("Login");
        registerButton = new JButton("Sign in");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);

        //almeno premendo INVIO fa subito il login senza cliccarlo col mouse
        getRootPane().setDefaultButton(loginButton);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener al) {
        loginButton.addActionListener(al);
    }

    public void addRegisterListener(ActionListener al) {
        registerButton.addActionListener(al);
    }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public void preSetDada() {
        usernameField.setText("dada");
        passwordField.setText("ciao");
    }

}