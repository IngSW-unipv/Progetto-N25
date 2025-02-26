package it.unipv.ingsw.lasout.view.virtualVault;

import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.facade.virtualVault.ConcreteVirtualVaultFacade;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddVirtualVaultDialog extends JDialog {
    private JTextField nameField;
    private JTextField balanceField;
    private JButton saveButton;
    private JButton cancelButton;

    public AddVirtualVaultDialog(JFrame parent) {
        super(parent, "Nuovo VirtualVault", true);
        initUI();
    }

    private void initUI() {
        // Layout con GridLayout: 3 righe e 2 colonne
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Riga 1: Nome
        panel.add(new JLabel("Nome:"));
        nameField = new JTextField();
        panel.add(nameField);

        // Riga 2: Saldo
        panel.add(new JLabel("Saldo:"));
        balanceField = new JTextField();
        panel.add(balanceField);

        // Riga 3: Pulsanti
        saveButton = new JButton("Salva");
        cancelButton = new JButton("Annulla");
        panel.add(saveButton);
        panel.add(cancelButton);

        add(panel);
        pack();
        setLocationRelativeTo(getParent());

        // Azione per annullare: chiude la finestra
        cancelButton.addActionListener(e -> dispose());
    }

    public void addSaveActionListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public String getVaultName() {
        return nameField.getText().trim();
    }

    public double getVaultBalance() {
        try {
            return Double.parseDouble(balanceField.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}


