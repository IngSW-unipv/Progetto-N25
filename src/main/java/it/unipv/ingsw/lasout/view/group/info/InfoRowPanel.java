package it.unipv.ingsw.lasout.view.group.info;

import it.unipv.ingsw.lasout.view.LaColor;

import javax.swing.*;
import java.awt.*;

public class InfoRowPanel extends JPanel {
    private final JLabel nameLabel;
    private final JLabel directionLabel;
    private final JLabel amountLabel;
    private final JLabel a;
    private final JLabel nameLabelTow;

    public InfoRowPanel(String name, String direction, String amount, String a, String nameTow) {
        // Usa un layout a griglia per distribuire equamente i tre elementi
        setLayout(new GridLayout(1, 3, 5, 0));
        setBackground(LaColor.SFONDO_CHIARO);

        nameLabel = new JLabel(name);
        directionLabel = new JLabel(direction);
        amountLabel = new JLabel(amount);
        this.a = new JLabel(a);
        nameLabelTow = new JLabel(nameTow);

        // Centra il testo nelle label
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        directionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.a.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabelTow.setHorizontalAlignment(SwingConstants.CENTER);
        setPreferredSize(new Dimension(70, 30));
        setMaximumSize(new Dimension(300, 30));

        add(nameLabel);
        add(directionLabel);
        add(amountLabel);
        add(this.a);
        add(nameLabelTow);
    }
}
