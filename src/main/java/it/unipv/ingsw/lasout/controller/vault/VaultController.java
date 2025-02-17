package it.unipv.ingsw.lasout.controller.vault;

import it.unipv.ingsw.lasout.view.vault.VaultPanel;

public class VaultController {

    private VaultPanel vaultPanel;
    private int count; // Manteniamo qui il valore del contatore

    public VaultController(VaultPanel vaultPanel) {
        this.vaultPanel = vaultPanel;
        this.count = 0; // inizialmente 0
        initController();
    }

    private void initController() {
        // Al click del bottone incrementiamo il contatore e aggiorniamo la label
        vaultPanel.addIncrementListener(e -> {
            count++;
            vaultPanel.setCountLabel(count);
        });
    }
}
