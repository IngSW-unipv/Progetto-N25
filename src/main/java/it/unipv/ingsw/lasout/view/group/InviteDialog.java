package it.unipv.ingsw.lasout.view.group;

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
        inviteContentPanel.setBackground(Color.WHITE);

        // Crea lo scroll pane che contiene il pannello
        scrollPane = new JScrollPane(inviteContentPanel);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    // Metodo per aggiungere una riga (InviteRowPanel)
    public void addInviteRow(InviteRowPanel row) {
        inviteContentPanel.add(row);
        inviteContentPanel.revalidate();
        inviteContentPanel.repaint();
    }

    // Metodo per svuotare il pannello
    public void clearInviteRows() {
        inviteContentPanel.removeAll();
        inviteContentPanel.revalidate();
        inviteContentPanel.repaint();
    }

    // Getter per il pannello
    public JPanel getInviteContentPanel() {
        return inviteContentPanel;
    }
}
