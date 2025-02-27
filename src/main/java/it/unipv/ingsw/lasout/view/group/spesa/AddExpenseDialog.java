package it.unipv.ingsw.lasout.view.group.spesa;

import it.unipv.ingsw.lasout.view.LaColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddExpenseDialog extends JDialog {

    private final JTextField field1;
    private final JTextField field2;
    private final JButton aggiungiButton;

    public AddExpenseDialog(Frame parent) {
        super(parent, "Aggiungi Spesa", true);

        setSize(350, 300);
        setLocationRelativeTo(parent);

        // Pannello principale con BoxLayout verticale
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(230, 240, 250));
        // Aggiungiamo glue per centrare verticalmente
        panel.add(Box.createVerticalGlue());

        // Campo 1
        JLabel label1 = new JLabel("Importo:");
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label1.setForeground(LaColor.FONT);
        panel.add(label1);
        panel.add(Box.createVerticalStrut(5));

        field1 = new JTextField();
        field1.setMaximumSize(new Dimension(250, 30));
        field1.setBorder(BorderFactory.createLineBorder(LaColor.SFONDO_SCURO, 2));
        field1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(field1);
        panel.add(Box.createVerticalStrut(20));

        // Campo 2
        JLabel label2 = new JLabel("Note:");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setForeground(LaColor.FONT);
        panel.add(label2);
        panel.add(Box.createVerticalStrut(5));

        field2 = new JTextField();
        field2.setMaximumSize(new Dimension(250, 30));
        field2.setBorder(BorderFactory.createLineBorder(LaColor.SFONDO_SCURO, 2));
        field2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(field2);
        panel.add(Box.createVerticalStrut(30));

        // Bottone "Aggiungi" centrato
        aggiungiButton = new JButton("Aggiungi");
        aggiungiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        aggiungiButton.setPreferredSize(new Dimension(150, 40));
        aggiungiButton.setMaximumSize(new Dimension(150, 40));
        //  colore di sfondo verdino e rendilo opaco per vederlo
        aggiungiButton.setBackground(LaColor.BTN_SFONDO);
        aggiungiButton.setOpaque(true);
        aggiungiButton.setForeground(LaColor.FONT);
        aggiungiButton.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(aggiungiButton);

        // Aggiunge glue in basso per centrare verticalmente
        panel.add(Box.createVerticalGlue());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    public void addAggiungiListener(ActionListener l) {
        aggiungiButton.addActionListener(l);
    }

    public String getField1Text() {
        return field1.getText();
    }

    public String getField2Text() {
        return field2.getText();
    }

    public JTextField getField1() {
        return field1;
    }

    public JTextField getField2() {
        return field2;
    }
}