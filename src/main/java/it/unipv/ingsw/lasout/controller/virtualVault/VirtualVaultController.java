package it.unipv.ingsw.lasout.controller.virtualVault;

import it.unipv.ingsw.lasout.view.virtualVault.VirtualVaultPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VirtualVaultController {

    private VirtualVaultPanel view;

    public VirtualVaultController(VirtualVaultPanel view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // Action listener per la JComboBox: al cambio selezione mostriamo un avviso
        view.getVaultComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) view.getVaultComboBox().getSelectedItem();
                JOptionPane.showMessageDialog(view, "Hai selezionato: " + selected);
            }
        });

        // Action listener per il pulsante "Aggiungi VirtualVault"
        view.getAddVaultButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(view, "Aggiungi VirtualVault eseguito!");
            }
        });

        // Action listener per il pulsante "Elimina VirtualVault"
        view.getDeleteVaultButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(view, "Elimina VirtualVault eseguito!");
            }
        });
    }
}
