package it.unipv.ingsw.lasout.view.group.invite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InviteRowPanel extends JPanel {
    private JLabel infoLabel;
    private JButton actionButton;

    public InviteRowPanel(String username) {
        // Layout orizzontale
        setLayout(new BorderLayout());
        setBackground(new Color(230, 240, 250));

        infoLabel = new JLabel(username);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        actionButton = new JButton("Invita");
        actionButton.setPreferredSize(new Dimension(80, 30));
        // Eventuali impostazioni di stile per il bottone
        actionButton.setBackground(Color.DARK_GRAY);
        actionButton.setBackground(new Color(176, 196, 222));


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
