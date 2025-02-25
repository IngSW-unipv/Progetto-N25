package it.unipv.ingsw.lasout.view.virtualVault;

import it.unipv.ingsw.lasout.view.mainview.MainUIView;

import javax.swing.*;
import java.awt.*;

public class VirtualVaultPanel extends JPanel {

    private MainUIView parentFrame;            // Riferimento al frame che contiene questo pannello

    private JComboBox<String> vaultComboBox;   // Menu a tendina con "seleziona vault" di base
    private JPanel infoPanel;                  // Pannello grigio per visualizzare le informazioni del vault
    private JButton addVaultButton;            // Pulsante "Aggiungi VirtualVault"
    private JButton deleteVaultButton;         // Pulsante "Elimina VirtualVault"

    public VirtualVaultPanel(MainUIView parentFrame) {
        this.parentFrame = parentFrame;
        initUI();
    }

    private void initUI() {
        // Layout principale: suddivisione in nord, centro e sud
        setLayout(new BorderLayout(10, 10));

        // --- TOP PANEL: label e combo box ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel comboLabel = new JLabel("Seleziona VirtualVault:");
        vaultComboBox = new JComboBox<>();
        // Aggiunge un elemento di default
        vaultComboBox.addItem("seleziona vault");

        topPanel.add(comboLabel);
        topPanel.add(vaultComboBox);
        add(topPanel, BorderLayout.NORTH);

        // --- CENTER: pannello grigio per le info ---
        infoPanel = new JPanel();
        infoPanel.setBackground(Color.LIGHT_GRAY);
        // Imposta una dimensione preferita (facoltativo, per simulare il riquadro)
        infoPanel.setPreferredSize(new Dimension(400, 200));
        add(infoPanel, BorderLayout.CENTER);

        // --- BOTTOM PANEL: pulsanti ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        addVaultButton = new JButton("Aggiungi VirtualVault");
        deleteVaultButton = new JButton("Elimina VirtualVault");
        bottomPanel.add(addVaultButton);
        bottomPanel.add(deleteVaultButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Getter per permettere al Controller di accedere ai componenti
    public JComboBox<String> getVaultComboBox() {
        return vaultComboBox;
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public JButton getAddVaultButton() {
        return addVaultButton;
    }

    public JButton getDeleteVaultButton() {
        return deleteVaultButton;
    }
}
