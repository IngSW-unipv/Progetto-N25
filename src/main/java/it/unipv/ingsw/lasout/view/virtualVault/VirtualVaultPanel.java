package it.unipv.ingsw.lasout.view.virtualVault;

import it.unipv.ingsw.lasout.view.mainview.MainUIView;

import javax.swing.*;
import java.awt.*;

public class VirtualVaultPanel extends JPanel {

    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel saldoLabel;
    private JTextField saldoField;
    private JButton createVaultButton;

    public VirtualVaultPanel(MainUIView mainUIView) {

        // Imposta il layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Inizializza i componenti
        nameLabel = new JLabel("Inserisci nome per il Virtual Vault");
        nameField = new JTextField(20);  // Puoi modificare la larghezza desiderata
        nameField.setMaximumSize(new Dimension(200, 25));

        saldoLabel = new JLabel("inserisci saldo");
        saldoField = new JTextField(20); // Anche qui puoi variare la larghezza
        saldoField.setMaximumSize(new Dimension(200, 25));

        createVaultButton = new JButton("crea Virtual Vault");
        createVaultButton.setPreferredSize(new Dimension(200, 50));
        createVaultButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Allinea orizzontalmente i componenti al centro
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        saldoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        saldoField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Aggiungi uno spazio iniziale (opzionale)
        add(Box.createVerticalStrut(30));

        // Aggiungi i componenti in ordine verticale
        add(nameLabel);
        add(Box.createVerticalStrut(5));
        add(nameField);

        add(Box.createVerticalStrut(15));
        add(saldoLabel);
        add(Box.createVerticalStrut(5));
        add(saldoField);

        add(Box.createVerticalStrut(20));
        add(createVaultButton);

        // Completa con uno "spazio elastico" in basso
        add(Box.createVerticalGlue());
    }


    // Getter per accedere ai campi e al bottone dal Controller
    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getSaldoField() {
        return saldoField;
    }

    public JButton getCreateVaultButton() {
        return createVaultButton;
    }
}
