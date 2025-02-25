package it.unipv.ingsw.lasout.view.group.invite;

import it.unipv.ingsw.lasout.view.LaColor;

import javax.swing.*;
import java.awt.*;

public class InviteDialog extends JDialog {
    private JPanel inviteContentPanel; // Pannello dove verranno aggiunte le righe
    private JScrollPane scrollPane;

    public InviteDialog(Frame parent) {
        super(parent, "Invita Utenti", true);
        setSize(250, 300);
        setLocationRelativeTo(parent);

        // Crea il pannello interno con BoxLayout verticale
        inviteContentPanel = new JPanel();
        inviteContentPanel.setLayout(new BoxLayout(inviteContentPanel, BoxLayout.Y_AXIS));
        inviteContentPanel.setBackground(LaColor.SFONDO);

        // Crea lo scroll pane che contiene il pannello
        scrollPane = new JScrollPane(inviteContentPanel);
        scrollPane.setBorder(BorderFactory.createLineBorder(LaColor.SFONDO_SCURO, 1));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    // Metodo per aggiungere una riga (InviteRowPanel)
    public void addInviteRow(InviteRowPanel row) {
        inviteContentPanel.add(row);
        inviteContentPanel.revalidate();
        inviteContentPanel.repaint();
    }
}
