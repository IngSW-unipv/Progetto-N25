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
    private JPanel infoPanel;                  // Pannello grigio per visualizzare le informazioni del vault
    private JButton addVirtualVaultButton;            // Pulsante "Aggiungi VirtualVault"
    private JButton deleteVirtualVaultButton;         // Pulsante "Elimina VirtualVault"
    private JLabel comboLabel;


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
        /*JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(getBackground());

        comboLabel = new JLabel("Seleziona VirtualVault:");
        virtualVaultComboBox = new JComboBox<>();
        // Aggiunge un elemento di default
        virtualVaultComboBox.addItem(virtualVaultComboBox.getItemAt(0));
        //Setto colori che mi servono
        virtualVaultComboBox.setBackground(LaColor.BTN_SFONDO);
        virtualVaultComboBox.setForeground(LaColor.FONT);

        topPanel.add(comboLabel);
        topPanel.add(virtualVaultComboBox);
        add(topPanel, BorderLayout.NORTH);
*/
        // --- CENTER: pannello grigio per le info ---
        infoPanel = new JPanel();
        infoPanel.setBackground(LaColor.SFONDO);
        infoPanel.setBorder(BorderFactory.createLineBorder(LaColor.SFONDO_SCURO, 15));
        // Imposta una dimensione preferita (
        infoPanel.setPreferredSize(new Dimension(400, 200));
        add(infoPanel, BorderLayout.CENTER);

        // --- BOTTOM PANEL: pulsanti ---
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
    public JPanel getInfoPanel() {
        return infoPanel;
    }
    public void resetInfoPanel() {infoPanel.removeAll();}

    //-----------------
    public JButton getAddVirtualVaultButton() {
        return addVirtualVaultButton;
    }
    //----------------
    public JButton getDeleteVirtualVaultButton() {
        return deleteVirtualVaultButton;
    }




}
