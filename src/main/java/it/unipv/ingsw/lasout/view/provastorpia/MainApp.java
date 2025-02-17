package it.unipv.ingsw.lasout.view.provastorpia;
import javax.swing.*;
import java.awt.*;

/**
 * Classe principale che avvia l'applicazione.
 * Mostra prima la schermata di Login.
 */
public class MainApp {
    public static void main(String[] args) {
        // Imposta il look and feel di sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}

/**
 * Finestra di Login con campi per username e password e due pulsanti: Login e Registrati.
 */
class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
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

        // Etichetta e campo per Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Username:"), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        mainPanel.add(usernameField, gbc);

        // Etichetta e campo per Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        // Pannello per i bottoni
        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton registratiButton = new JButton("Registrati");
        buttonPanel.add(loginButton);
        buttonPanel.add(registratiButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        // Azione per il pulsante "Login"
        loginButton.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            // Controllo semplificato: username=admin e password=1234
            if ("admin".equalsIgnoreCase(user) && "1234".equals(pass)) {
                new MainUI();
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Credenziali errate! Riprova.",
                        "Errore di Login",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Azione per il pulsante "Registrati"
        registratiButton.addActionListener(e -> {
            // Nasconde la schermata di login e apre la finestra di registrazione
            this.setVisible(false);
            new RegisterFrame(this);
        });

        getContentPane().add(mainPanel);
        setVisible(true);
    }
}

/**
 * Finestra di Registrazione con campi per username, password ed email.
 * Sono presenti due bottoni: Registrati e Login.
 * - Registrati: simula la registrazione e riporta al Login.
 * - Login: torna semplicemente al Login senza registrarsi.
 */
class RegisterFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private LoginFrame loginFrame; // riferimento alla schermata di login

    public RegisterFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;

        setTitle("Registrazione");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 260);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etichetta e campo per Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Username:"), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        mainPanel.add(usernameField, gbc);

        // Etichetta e campo per Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        // Etichetta e campo per Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Email:"), gbc);

        emailField = new JTextField(15);
        gbc.gridx = 1;
        mainPanel.add(emailField, gbc);

        // Pannello per i bottoni
        JPanel buttonPanel = new JPanel();
        JButton registratiButton = new JButton("Registrati");
        JButton loginButton = new JButton("Login");
        buttonPanel.add(registratiButton);
        buttonPanel.add(loginButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        // Azione per il pulsante "Registrati"
        registratiButton.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            String email = emailField.getText();

            if (user.isEmpty() || pass.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Tutti i campi sono obbligatori!",
                        "Errore di Registrazione",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                // Simulazione di registrazione (in una app reale andrà salvato su DB o altro)
                JOptionPane.showMessageDialog(
                        this,
                        "Registrazione completata! Ora puoi effettuare il login.",
                        "Registrazione Completata",
                        JOptionPane.INFORMATION_MESSAGE
                );
                // Ritorna alla schermata di Login
                loginFrame.setVisible(true);
                dispose();
            }
        });

        // Azione per il pulsante "Login": torna al Login senza registrarsi
        loginButton.addActionListener(e -> {
            loginFrame.setVisible(true);
            dispose();
        });

        getContentPane().add(mainPanel);
        setVisible(true);
    }
}

/**
 * Finestra Principale con 8 pulsanti a sinistra e un pannello variabile a destra.
 * Il pannello di destra utilizza un CardLayout e ora ha un bordo bianco più spesso.
 */
class MainUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    public MainUI() {
        setTitle("Finestra Principale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(880, 660); // Ingrandita del 10%
        setLocationRelativeTo(null);

        // 1. Pannello sinistro con 8 pulsanti e maggiore spazio tra loro
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(8, 1, 10, 10));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttonLabels = {
                "vault", "virtualvau", "Group", "cashbook",
                "notifies", "neame", "friends", "account"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setFocusPainted(false);
            button.setMargin(new Insets(10, 20, 10, 20));
            leftPanel.add(button);
        }

        // 2. Pannello destro con CardLayout e bordo bianco più spesso
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 12));

        contentPanel.add(creaPannello("Contenuto: Vault", new Color(139, 69, 19)), "vault");
        contentPanel.add(creaPannello("Contenuto: Virtualvau", new Color(160, 82, 45)), "virtualvau");
        contentPanel.add(creaPannello("Contenuto: Group", new Color(205, 133, 63)), "Group");
        contentPanel.add(creaPannello("Contenuto: Cashbook", new Color(210, 105, 30)), "cashbook");
        contentPanel.add(creaPannello("Contenuto: Notifies", new Color(150, 75, 0)), "notifies");
        contentPanel.add(creaPannello("Contenuto: Neame", new Color(184, 134, 11)), "neame");
        contentPanel.add(creaPannello("Contenuto: Friends", new Color(205, 92, 92)), "friends");
        contentPanel.add(creaPannello("Contenuto: Account", new Color(188, 143, 143)), "account");

        // Azione per i pulsanti che cambiano il pannello visualizzato
        Component[] components = leftPanel.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JButton) {
                JButton btn = (JButton) components[i];
                String cardName = buttonLabels[i];
                btn.addActionListener(e -> cardLayout.show(contentPanel, cardName));
            }
        }

        // Layout del frame principale
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(leftPanel, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Metodo di comodo per creare un pannello con un'etichetta e sfondo colorato.
     */
    private JPanel creaPannello(String testo, Color coloreSfondo) {
        JPanel panel = new JPanel();
        panel.setBackground(coloreSfondo);
        panel.add(new JLabel(testo));
        return panel;
    }
}
