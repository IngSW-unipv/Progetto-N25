package it.unipv.ingsw.lasout.view.group;

import javax.swing.*;
import java.awt.*;

public class RemoveParticipantDialog extends JDialog {
    private JPanel contentPanel;  // pannello interno dove aggiungerai le righe
    private JScrollPane scrollPane;

    public RemoveParticipantDialog(Frame parent) {
        super(parent, "Elimina Partecipante", true);
        setSize(200, 250);
        setLocationRelativeTo(parent);

        // Crea il pannello interno con un layout verticale
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        // Crea lo scroll pane che contiene il pannello
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Imposta il layout del JDialog
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

    }

    // Metodo per aggiungere una "riga" (un JPanel) al pannello interno
    public void addParticipantRow(ParticipantRowPanel row) {
        contentPanel.add(row);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void clearRows() {
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Getter per il pannello interno, se necessario
    public JPanel getContentPanel() {
        return contentPanel;
    }
}
