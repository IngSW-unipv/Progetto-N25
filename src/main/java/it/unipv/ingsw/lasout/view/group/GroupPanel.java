package it.unipv.ingsw.lasout.view.group;

import it.unipv.ingsw.lasout.view.LaColor;
import it.unipv.ingsw.lasout.view.group.aggiunta.NewGroupDialog;
import it.unipv.ingsw.lasout.view.group.info.JInfoPanel;
import it.unipv.ingsw.lasout.view.group.invite.InviteDialog;
import it.unipv.ingsw.lasout.view.group.settings.GroupSettingsDialog;
import it.unipv.ingsw.lasout.view.group.spesa.AddExpenseDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GroupPanel extends JPanel {

    private final JComboBox<GroupItem> comboBox;
    private final JButton nuovoGruppoBtn;
    private final JButton impostazioniBtn;
    private final JLabel nameLabel;
    private final JButton invitaBtn;
    private final JButton aggiungiSpesaBtn;
    private final JButton finalizzaBtn;
    private final GroupSettingsDialog settingsDialog;
    private final AddExpenseDialog addExpenseDialog;
    private final NewGroupDialog newGroupDialog;
    private final JInfoPanel infoPanel;
    private final InviteDialog inviteDialog;

    public GroupPanel(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(LaColor.SFONDO); // Sfondo marroncino per il pannello principale

        // ============ TOP BAR (comboBox + bottone "Nuovo Gruppo") ============
        JPanel topBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topBarPanel.setBackground(getBackground());

        comboBox = new JComboBox<>();
        nuovoGruppoBtn = new JButton("Nuovo Gruppo");

        topBarPanel.add(comboBox);
        topBarPanel.add(nuovoGruppoBtn);

        add(topBarPanel, BorderLayout.NORTH);
        nuovoGruppoBtn.setBackground(LaColor.BTN_SFONDO);
        nuovoGruppoBtn.setForeground(LaColor.FONT);

        comboBox.setBackground(LaColor.BTN_SFONDO);
        comboBox.setForeground(LaColor.FONT);

        newGroupDialog = new NewGroupDialog(frame);


        // ============ CENTRO (pannello blu) ============
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(LaColor.SFONDO);
        centerPanel.setBorder(BorderFactory.createLineBorder(LaColor.SFONDO, 15));

        // PANNELLO SUPERIORE nel centerPanel, con BORDERLAYOUT
        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.setOpaque(false); //resta colr sfondo

        // A SINISTRA LA LABEL
        JPanel topLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topLeftPanel.setOpaque(false);

        nameLabel = new JLabel("");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(25, 60, 0, 0));
        topLeftPanel.add(nameLabel);

        // A DESTRA IL PULSANTE IMPOSTAZIONI
        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        topRightPanel.setOpaque(false);
        impostazioniBtn = new JButton("Impostazioni");
        impostazioniBtn.setPreferredSize(new Dimension(120, 50));
        impostazioniBtn.setBackground(LaColor.BTN_SFONDO);
        impostazioniBtn.setForeground(LaColor.FONT);
        impostazioniBtn.setFont(new Font("Arial", Font.BOLD, 13));
        impostazioniBtn.setEnabled(false);
        topRightPanel.add(impostazioniBtn);
        settingsDialog = new GroupSettingsDialog(frame);

        // aggiungo i due pannelli al topContainer
        topContainer.add(topLeftPanel, BorderLayout.WEST);
        topContainer.add(topRightPanel, BorderLayout.EAST);

        // inserisco topContainer nella parte alta del centerPanel
        centerPanel.add(topContainer, BorderLayout.NORTH);

        // ============ PANNELLO ROSSO (infoPanel) al centro del centerPanel ============

        infoPanel = new JInfoPanel();
        infoPanel.setPreferredSize(new Dimension(500, 300));
        infoPanel.setBorder(BorderFactory.createLineBorder(LaColor.SFONDO, 15));
        centerPanel.add(infoPanel, BorderLayout.CENTER);

        centerPanel.add(infoPanel, BorderLayout.CENTER);

        // ============ PANNELLO CON 3 BOTTONI IN BASSO ============
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setOpaque(false);

        invitaBtn = new JButton("Invita");
        aggiungiSpesaBtn = new JButton("Aggiungi spesa");
        finalizzaBtn = new JButton("Finalizza");

        Dimension sideButtonSize = new Dimension(150, 50);
        Dimension centerButtonSize = new Dimension(200, 80);

        invitaBtn.setPreferredSize(sideButtonSize);
        finalizzaBtn.setPreferredSize(sideButtonSize);
        aggiungiSpesaBtn.setPreferredSize(centerButtonSize);

        Font biggerFont = new Font("Arial", Font.BOLD, 16);
        invitaBtn.setFont(biggerFont);
        aggiungiSpesaBtn.setFont(biggerFont);
        finalizzaBtn.setFont(biggerFont);

        invitaBtn.setBackground(LaColor.BTN_SFONDO);
        aggiungiSpesaBtn.setBackground(LaColor.BTN_SFONDO);
        finalizzaBtn.setBackground(LaColor.BTN_SFONDO);

        invitaBtn.setForeground(LaColor.FONT);
        aggiungiSpesaBtn.setForeground(LaColor.FONT);
        finalizzaBtn.setForeground(LaColor.FONT);

        bottomPanel.add(invitaBtn);
        bottomPanel.add(aggiungiSpesaBtn);
        bottomPanel.add(finalizzaBtn);

        inviteDialog = new InviteDialog(frame);
        addExpenseDialog = new AddExpenseDialog(frame);
        aggiungiSpesaBtn.setEnabled(false);
        finalizzaBtn.setEnabled(false);
        invitaBtn.setEnabled(false);

        centerPanel.add(bottomPanel, BorderLayout.SOUTH);

        // aggiungo centerPanel al centro di GroupPanel
        add(centerPanel, BorderLayout.CENTER);
    }

    /* ================= Metodi per la comboBox ================= */
    public GroupItem getSelectedGroup() {
        return (GroupItem) comboBox.getSelectedItem();
    }

    public void addGroupItem(GroupItem groupItem) {
        comboBox.addItem(groupItem);
    }

    public void resetComboBox() {
        comboBox.removeAllItems();
    }

    public void addComboBoxListener(ActionListener listener) {
        comboBox.addActionListener(listener);
    }

    /* ================= Metodi per manipolare la label titolo ================= */
    public void setNameLabelText(String text) {
        nameLabel.setText(text);
    }

    /* ================= Pannello rosso ================= */
    public JInfoPanel getInfoPanel() {
        return infoPanel;
    }

    public void resetJInfoPanelLeft() {
        infoPanel.getLeftPanel().removeAll();
    }

    public void resetJInfoPanelRight() {
        infoPanel.getRightPanel().removeAll();
    }

    /* ================= Tasto impostazioni ================= */

    public JButton getImpostazioniBtn() {
        return impostazioniBtn;
    }

    public GroupSettingsDialog getSettingsDialog() {
        return settingsDialog;
    }

    public void addImpostazioniListener(ActionListener l) {
        impostazioniBtn.addActionListener(l);
    }


    /* =========== Tasto aggiungi spesa=============*/
    public AddExpenseDialog getAddExpenseDialog() {
        return addExpenseDialog;
    }

    public void addAggiungiSpesaListener(ActionListener l) {
        aggiungiSpesaBtn.addActionListener(l);
    }

    public JButton getAggiungiSpesaBtn() {
        return aggiungiSpesaBtn;
    }

    /* ========== Tasto nuovo gruppo ============= */
    public NewGroupDialog getNewGroupDialog() {
        return newGroupDialog;
    }

    public void addNuovoGruppoListener(ActionListener l) {
        nuovoGruppoBtn.addActionListener(l);
    }

    /*=========== Tasto finalizza===============*/

    public JButton getFinalizzaBtn() {
        return finalizzaBtn;
    }

    public void addFinalizzaListener(ActionListener l) {
        finalizzaBtn.addActionListener(l);
    }

    /* ============= Tasto invita ================= */
    public void addInvitaListener(ActionListener l) {
        invitaBtn.addActionListener(l);
    }

    public InviteDialog getInviteDialog() {
        return inviteDialog;
    }

    public JButton getInvitaBtn() {
        return invitaBtn;
    }

}
