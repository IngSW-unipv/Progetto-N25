package it.unipv.ingsw.lasout.view.group;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InviteRowPanel extends JPanel {
    private JLabel infoLabel;
    private JButton actionButton;

    public InviteRowPanel(String username) {
        // Layout orizzontale
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        infoLabel = new JLabel(username);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        actionButton = new JButton("Invita");
        actionButton.setPreferredSize(new Dimension(80, 30));
        // Eventuali impostazioni di stile per il bottone
        actionButton.setBackground(Color.LIGHT_GRAY);

        add(infoLabel, BorderLayout.CENTER);
        add(actionButton, BorderLayout.EAST);
    }

    public void addActionListener(ActionListener l) {
        actionButton.addActionListener(l);
    }

    public JLabel getInfoLabel() {
        return infoLabel;
    }
}
