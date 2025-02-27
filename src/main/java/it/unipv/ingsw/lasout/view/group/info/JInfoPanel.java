package it.unipv.ingsw.lasout.view.group.info;

import it.unipv.ingsw.lasout.view.LaColor;

import javax.swing.*;
import java.awt.*;

public class JInfoPanel extends JPanel {
    private final JScrollPane leftScrollPane;
    private final JScrollPane rightScrollPane;
    private final JPanel leftPanel;
    private final JPanel rightPanel;

    public JInfoPanel() {
        // Imposta il layout con GridBagLayout
        setLayout(new GridBagLayout());
        setBackground(LaColor.SFONDO_SCURO);
        // Crea i pannelli interni con layout verticale
        leftPanel = new JPanel();
        leftPanel.setBackground(LaColor.SFONDO_CHIARO);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel = new JPanel();
        rightPanel.setBackground(LaColor.SFONDO_CHIARO);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Crea gli scroll pane per ciascun pannello
        leftScrollPane = new JScrollPane(leftPanel);
        rightScrollPane = new JScrollPane(rightPanel);

        // Imposta bordi per gli scroll pane
        leftScrollPane.setBorder(BorderFactory.createLineBorder(LaColor.SFONDO_SCURO, 1));
        rightScrollPane.setBorder(BorderFactory.createLineBorder(LaColor.SFONDO_SCURO, 1));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 0;
        gbc.weighty = 1.0;

        // Imposta il pannello sinistro
        gbc.gridx = 0;
        gbc.weightx = 0.60;
        add(leftScrollPane, gbc);

        // Imposta il pannello destro
        gbc.gridx = 1;
        gbc.weightx = 0.40;
        add(rightScrollPane, gbc);

        rightScrollPane.setPreferredSize(new Dimension(60, rightScrollPane.getPreferredSize().height));
        rightScrollPane.setMinimumSize(new Dimension(60, rightScrollPane.getMinimumSize().height));
        rightScrollPane.setMaximumSize(new Dimension(60, Integer.MAX_VALUE));


    }

    // Getter per il pannello sinistro
    public JPanel getLeftPanel() {
        return leftPanel;
    }

    // Getter per il pannello destro
    public JPanel getRightPanel() {
        return rightPanel;
    }

    public void addExpenseLine(ExpenseRowPanel ex) {
        leftPanel.add(ex);
    }

    public void addInfoLine(InfoRowPanel info) {
        rightPanel.add(info);
    }

}