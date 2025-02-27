package it.unipv.ingsw.lasout.view.group.invite;

import it.unipv.ingsw.lasout.view.LaColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InviteRowPanel extends JPanel {
    private final JLabel infoLabel;
    private final JButton actionButton;

    public InviteRowPanel(String username) {
        // Layout orizzontale
        setLayout(new BorderLayout());
        setBackground(LaColor.SFONDO);

        infoLabel = new JLabel(username);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        actionButton = new JButton("Invita");
        actionButton.setPreferredSize(new Dimension(80, 30));
        // Eventuali impostazioni di stile per il bottone
        actionButton.setBackground(LaColor.FONT);
        actionButton.setBackground(LaColor.BTN_SFONDO);


        add(infoLabel, BorderLayout.CENTER);
        add(actionButton, BorderLayout.EAST);
    }

    public void addActionListener(ActionListener l) {
        actionButton.addActionListener(l);
    }

}
