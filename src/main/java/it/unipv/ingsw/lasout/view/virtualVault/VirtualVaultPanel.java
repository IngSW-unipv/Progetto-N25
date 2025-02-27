package it.unipv.ingsw.lasout.view.virtualVault;

import it.unipv.ingsw.lasout.view.LaColor;
import it.unipv.ingsw.lasout.view.mainview.MainUIView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VirtualVaultPanel extends JPanel {

    private MainUIView parentFrame;            // Riferimento al frame che contiene questo pannello

    //Deve mettere il VirtualVaultItem che è una classe al di fuori con dentro getter setter, dentro panel nuovo metodo addVirtualVaultItem

    private JComboBox<VirtualVaultItem> virtualVaultComboBox;   // Menu a tendina con "seleziona virtualVault" di base
    private JPanel infoPanel;                  // Pannello per visualizzare le informazioni del vault
    // Sotto-pannello dove mostriamo il nome e il saldo
    private JPanel infoContentPanel;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton addVirtualVaultButton;            // Pulsante "Aggiungi VirtualVault"
    private JButton deleteVirtualVaultButton;         // Pulsante "Elimina VirtualVault"
    private JLabel comboLabel;                       // Label in altro per mostrare il nome selezionato


    public VirtualVaultPanel(MainUIView parentFrame) {
        this.parentFrame = parentFrame;
        initUI();
    }

    private void initUI() {
        // Layout principale: suddivisione in nord, centro e sud
        setLayout(new BorderLayout(10, 10));
        setBackground(LaColor.SFONDO);

        // --- TOP PANEL: label e combo box ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(getBackground());

        comboLabel = new JLabel("Seleziona VirtualVault:");

        virtualVaultComboBox = new JComboBox<>();
        // Imposta correttamente l'elemento di default
        virtualVaultComboBox.addItem(new VirtualVaultItem(0, "Selezione VirtualVault"));
        virtualVaultComboBox.setBackground(LaColor.BTN_SFONDO);
        virtualVaultComboBox.setForeground(LaColor.FONT);

        topPanel.add(comboLabel);
        topPanel.add(virtualVaultComboBox);
        add(topPanel, BorderLayout.NORTH);

        // --- CENTER: pannello per le info ---
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(LaColor.SFONDO);
        infoPanel.setBorder(BorderFactory.createLineBorder(LaColor.SFONDO_SCURO, 15));
        // Imposta una dimensione preferita (
        infoPanel.setPreferredSize(new Dimension(400, 200));

        // Sotto-pannello per le informazioni
        infoContentPanel = new JPanel(new GridBagLayout());
        infoContentPanel.setBackground(LaColor.SFONDO);
        infoPanel.add(infoContentPanel, BorderLayout.CENTER);

        //Pannello per i pulsanti "Deposita" e "Preleva"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(LaColor.SFONDO);

        depositButton = new JButton("Deposita");
        depositButton.setBackground(LaColor.BTN_SFONDO);
        depositButton.setForeground(LaColor.FONT);

        withdrawButton = new JButton("Preleva");
        withdrawButton.setBackground(LaColor.BTN_SFONDO);
        withdrawButton.setForeground(LaColor.FONT);

        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);

        infoPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Aggiungiamo il pannello centrale al FRAME
        add(infoPanel, BorderLayout.CENTER);

        // --- BOTTOM PANEL: pulsanti Aggiungi , Elimina
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        addVirtualVaultButton = new JButton("Aggiungi VirtualVault");
        deleteVirtualVaultButton = new JButton("Elimina VirtualVault");
        bottomPanel.add(addVirtualVaultButton);
        bottomPanel.add(deleteVirtualVaultButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }


    //------------------------Metodi per la combo box-------------------------------------------------------------
    public VirtualVaultItem getSelectedVault() {return (VirtualVaultItem) virtualVaultComboBox.getSelectedItem();}
    public void addVirtualVaultItem(VirtualVaultItem virtualVaultItem) {virtualVaultComboBox.addItem(virtualVaultItem);}
    public void resetComboBox(){virtualVaultComboBox.removeAllItems();};
    public void addComboBoxListener(ActionListener actionListener) {virtualVaultComboBox.addActionListener(actionListener);}
    //Metodo per modificare il testo della label
    public void setNameLabelText(String name) {
        comboLabel.setText(name);
    }

    //-----------------------Metodi per il pannello al centro-------------------------------------------------
    /**
     * Metodo per aggiornare il pannello centrale con le informazioni del VirtualVault.
     *
     * @param name    Il nome del VirtualVault da visualizzare.
     * @param balance Il saldo del VirtualVault da visualizzare.
     */

    public void updateCentralInfo(String name, String balance) {
        //Resetto la parte di testo ma non i pulsanti
        infoContentPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        //gbc.fill = GridBagConstraints.NONE;

        // Label per il nome
        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 28));
        gbc.gridy = 0;
        infoContentPanel.add(nameLabel, gbc);

        // Label per il saldo
        JLabel balanceLabel = new JLabel(balance, SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 28));
        gbc.gridy = 1;
        infoContentPanel.add(balanceLabel, gbc);

        infoContentPanel.revalidate();
        infoContentPanel.repaint();
    }


    //--------Metodi per i pulsanti prelievo e deposito----------
    public JButton getDepositButton() {
        return depositButton;
    }

    public JButton getWithdrawButton() {
        return withdrawButton;
    }

    //-----------------Metodi per i pulsanti-----------------
    public JButton getAddVirtualVaultButton() {
        return addVirtualVaultButton;
    }
    public void addVirtualVaultButtonListener(ActionListener actionListener) {addVirtualVaultButton.addActionListener(actionListener);}

    //----------------
    public JButton getDeleteVirtualVaultButton() {
        return deleteVirtualVaultButton;
    }

    //Metodo per ottenere la JComboBox per il controller
    public JComboBox<VirtualVaultItem> getVaultComboBox() {
        return virtualVaultComboBox;
    }
}
