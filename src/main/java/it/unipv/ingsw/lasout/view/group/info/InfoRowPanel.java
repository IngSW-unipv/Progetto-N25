package it.unipv.ingsw.lasout.view.group.info;

import javax.swing.*;
import java.awt.*;

public class InfoRowPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel directionLabel;
    private JLabel amountLabel;
    private JLabel a;
    private JLabel nameLabelTow;

    public InfoRowPanel(String name, String direction, String amount, String a, String nameTow) {
        // Usa un layout a griglia per distribuire equamente i tre elementi
        setLayout(new GridLayout(1, 3, 5, 0));
        setBackground(Color.WHITE);

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

    // Getter e setter per aggiornare il contenuto
    public String getNameText() {
        return nameLabel.getText();
    }

    public void setNameText(String name) {
        nameLabel.setText(name);
    }

    public String getDirectionText() {
        return directionLabel.getText();
    }

    public void setDirectionText(String direction) {
        directionLabel.setText(direction);
    }

    public String getAmountText() {
        return amountLabel.getText();
    }

    public void setAmountText(String amount) {
        amountLabel.setText(amount);
    }
}
