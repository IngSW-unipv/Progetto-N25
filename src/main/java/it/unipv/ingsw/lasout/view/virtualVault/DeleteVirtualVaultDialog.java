package it.unipv.ingsw.lasout.view.virtualVault;

import it.unipv.ingsw.lasout.view.LaColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteVirtualVaultDialog extends JDialog {

    private JComboBox<VirtualVaultItem> vaultComboBox;
    private JButton deleteButton;
    private JButton cancelButton;

    public DeleteVirtualVaultDialog(JFrame parent, List<VirtualVaultItem> vaultItems) {
        super(parent, "Elimina VirtualVault", true);
        initUI(vaultItems);
    }

    private void initUI(List<VirtualVaultItem> vaultItems) {
        // Utilizziamo un layout GridBagLayout per flessibilità
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(LaColor.SFONDO);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Riga 1: Label per la selezione
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(new JLabel("Seleziona VirtualVault da eliminare:"), gbc);

        // Riga 2: ComboBox con i vault passati come parametro
        vaultComboBox = new JComboBox<>();
        for(VirtualVaultItem item : vaultItems){
            vaultComboBox.addItem(item);
        }
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(vaultComboBox, gbc);

        // Riga 3: Pulsante "Elimina" e "Annulla"
        deleteButton = new JButton("Elimina");
        cancelButton = new JButton("Annulla");
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        panel.add(deleteButton, gbc);
        gbc.gridx = 1;
        panel.add(cancelButton, gbc);

        add(panel);
        pack();
        setLocationRelativeTo(getParent());

        // Azione per annullare: chiude il dialog
        cancelButton.addActionListener(e -> dispose());
    }

    // Metodo per ottenere il VirtualVaultItem selezionato
    public VirtualVaultItem getSelectedVaultItem() {
        return (VirtualVaultItem) vaultComboBox.getSelectedItem();
    }

    // Metodo per aggiungere un ActionListener al pulsante "Elimina"
    public void addDeleteActionListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }
}
