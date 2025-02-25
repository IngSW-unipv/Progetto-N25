package it.unipv.ingsw.lasout.view.group.settings;

import it.unipv.ingsw.lasout.view.LaColor;

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
        contentPanel.setBackground(LaColor.SFONDO);

        // Crea lo scroll pane che contiene il pannello
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createLineBorder(LaColor.SFONDO_SCURO, 1));

        // Imposta il layout del JDialog
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

    }

    // Metodo per aggiungere una "riga"
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
}
