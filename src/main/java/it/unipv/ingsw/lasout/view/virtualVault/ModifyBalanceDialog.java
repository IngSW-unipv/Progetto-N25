package it.unipv.ingsw.lasout.view.virtualVault;

import it.unipv.ingsw.lasout.view.LaColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ModifyBalanceDialog extends JDialog {

    private JLabel infoLabel;
    private JTextField amountField;
    private JButton confirmButton;
    private JButton cancelButton;

    public ModifyBalanceDialog(JFrame parent, String title) {
        super(parent, title, true);
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        getContentPane().setBackground(LaColor.SFONDO);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        infoLabel = new JLabel("Inserisci l'importo:");
        infoLabel.setForeground(LaColor.FONT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(infoLabel, gbc);

        amountField = new JTextField(10);
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(amountField, gbc);

        confirmButton = new JButton("Conferma");
        confirmButton.setBackground(LaColor.BTN_SFONDO);
        confirmButton.setForeground(LaColor.FONT);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(confirmButton, gbc);

        cancelButton = new JButton("Annulla");
        cancelButton.setBackground(LaColor.BTN_SFONDO);
        cancelButton.setForeground(LaColor.FONT);
        gbc.gridx = 1;
        add(cancelButton, gbc);

        pack();
        setLocationRelativeTo(getParent());

        // Chiudi la finestra al click su "Annulla"
        cancelButton.addActionListener(e -> dispose());
    }

    public void addConfirmListener(ActionListener listener) {
        confirmButton.addActionListener(listener);
    }

    public double getAmount() {
        try {
            return Double.parseDouble(amountField.getText().trim());
        } catch (NumberFormatException e) {
            return -1; // Valore non valido
        }
    }
}
