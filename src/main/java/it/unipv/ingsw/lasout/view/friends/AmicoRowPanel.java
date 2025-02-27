package it.unipv.ingsw.lasout.view.friends;

import it.unipv.ingsw.lasout.view.LaColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AmicoRowPanel extends JPanel {
    private JLabel label;
    private JButton button;

    public AmicoRowPanel(String text) {

        setLayout(new GridBagLayout());
        setBackground(LaColor.SFONDO);


        setAlignmentX(LEFT_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.fill = GridBagConstraints.BOTH;


        label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setForeground(LaColor.FONT);
        gbc.gridx = 0;
        gbc.weightx = 0.75;
        add(label, gbc);


        button = new JButton("Elimina");
        button.setBackground(LaColor.BTN_SFONDO);
        button.setForeground(LaColor.FONT);
        gbc.gridx = 1;
        gbc.weightx = 0.25;
        add(button, gbc);
    }

    public JLabel getLabel() {
        return label;
    }

    public void addBtnLis(ActionListener lis){
        button.addActionListener(lis);
    }
}
