package it.unipv.ingsw.lasout.view.friends;

import it.unipv.ingsw.lasout.view.LaColor;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class FriendsPanel extends JPanel {

    private JButton button;
    private ListPanel listPanel;

    public FriendsPanel() {
        setLayout(new BorderLayout());
        setBackground(LaColor.SFONDO);


        // Contenitore centrale per etichetta + listPanel
        JPanel centerContainer = new JPanel(new GridBagLayout());
        centerContainer.setOpaque(false);

        // Etichetta "Lista Amici:" sopra il listPanel
        JLabel label = new JLabel("Lista Amici:");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        label.setForeground(LaColor.FONT);
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.anchor = GridBagConstraints.CENTER;
        centerContainer.add(label, gbcLabel);

        // ListPanel con i bordi laterali
        listPanel = new ListPanel();
        listPanel.setBackground(LaColor.SFONDO);
        listPanel.setBorder(new EmptyBorder(0, 120, 0, 120));
        listPanel.setPreferredSize(new Dimension(400, 300));

        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 1;
        gbcPanel.fill = GridBagConstraints.BOTH;
        gbcPanel.weightx = 1.0;
        gbcPanel.weighty = 1.0;
        centerContainer.add(listPanel, gbcPanel);

        add(centerContainer, BorderLayout.CENTER);

        // Pannello inferiore con il bottone
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setOpaque(false);
        bottomPanel.add(Box.createVerticalStrut(15));

        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonContainer.setBackground(LaColor.SFONDO);
        button = new JButton("Aggiugni Amico");
        button.setForeground(LaColor.FONT);
        button.setBackground(LaColor.BTN_SFONDO);
        button.setPreferredSize(new Dimension(175, 60));
        buttonContainer.add(button);
        bottomPanel.add(buttonContainer);
        bottomPanel.add(Box.createVerticalStrut(20));

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JButton getButton() {
        return button;
    }

    public ListPanel getListPanel() {
        return listPanel;
    }
}
