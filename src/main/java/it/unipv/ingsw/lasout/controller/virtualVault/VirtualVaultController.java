package it.unipv.ingsw.lasout.controller.virtualVault;

import it.unipv.ingsw.lasout.view.virtualVault.VirtualVaultPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VirtualVaultController {

    private VirtualVaultPanel view;

    public VirtualVaultController(VirtualVaultPanel view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // Aggiunge l’action listener al bottone presente nella view
        view.getCreateVaultButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Stampa in console il messaggio richiesto
                System.out.println("ciao");
            }
        });
    }
}