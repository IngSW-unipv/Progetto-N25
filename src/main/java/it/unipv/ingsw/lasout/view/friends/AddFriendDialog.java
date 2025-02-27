package it.unipv.ingsw.lasout.view.friends;

import it.unipv.ingsw.lasout.view.LaColor;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddFriendDialog extends JDialog {
    private JTextField usernameField;
    private JButton aggiungiButton;

    public AddFriendDialog(Frame parent) {
        super(parent, "Aggiungi Amico", true);
        initComponents();
    }

    private void initComponents() {
        // Imposta il layout del dialog
        setLayout(new BorderLayout(10, 10));
        setBackground(LaColor.SFONDO);

        // Crea l'etichetta
        JLabel label = new JLabel("Username Amico:");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(LaColor.FONT);

        // Crea il campo di testo
        usernameField = new JTextField(20);

        // Crea il bottone "Aggiungi"
        aggiungiButton = new JButton("Aggiungi");
        aggiungiButton.setBackground(LaColor.BTN_SFONDO);
        aggiungiButton.setForeground(LaColor.FONT);

        // Crea un pannello per l'input (etichetta + campo di testo)
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBackground(LaColor.SFONDO);
        inputPanel.add(label, BorderLayout.NORTH);
        inputPanel.add(usernameField, BorderLayout.CENTER);
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(inputPanel, BorderLayout.CENTER);
        add(aggiungiButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getParent());
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JButton getAggiungiButton() {
        return aggiungiButton;
    }

    public String getUsername() {
        return usernameField.getText();
    }
}
