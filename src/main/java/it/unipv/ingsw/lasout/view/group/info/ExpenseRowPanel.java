package it.unipv.ingsw.lasout.view.group.info;

import it.unipv.ingsw.lasout.view.LaColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ExpenseRowPanel extends JPanel {

    private final JLabel nameLabel;
    private final JLabel importoLabel;
    private final JLabel descrizioneLabel;
    private final JButton cancButton;

    public ExpenseRowPanel(String nome, String importo, String descrizione) {
        // Imposta un layout basato su GridBagLayout per gestire la divisione della larghezza
        setLayout(new GridBagLayout());
        setBackground(LaColor.SFONDO_CHIARO);

        // Creazione dei componenti
        nameLabel = new JLabel(nome);
        nameLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
        importoLabel = new JLabel(importo + "€");
        descrizioneLabel = new JLabel(descrizione);
        cancButton = new JButton("Canc");


        cancButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancButton.setBackground(LaColor.SFONDO_CHIARO);

        // Per gestire le tre label un pannello interno
        JPanel labelPanel = new JPanel(new GridLayout(1, 3, 5, 0));
        labelPanel.setOpaque(false);
        labelPanel.add(nameLabel);
        labelPanel.add(importoLabel);
        labelPanel.add(descrizioneLabel);

        // Configuro i constraints per la labelPanel e il bottone
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.weighty = 1.0;

        // LabelPanel occupa la largezza
        gbc.gridx = 0;
        gbc.weightx = 0.995;
        add(labelPanel, gbc);

        // Bottone occupa il restante
        gbc.gridx = 1;
        gbc.weightx = 0.005;
        add(cancButton, gbc);

        // Imposta un'altezza fissa
        setPreferredSize(new Dimension(0, 25));
        setMinimumSize(new Dimension(Integer.MAX_VALUE, 25));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));

    }

    // Metodo per registrare il listener sul bottone "Canc"
    public void addCancListener(ActionListener l) {
        cancButton.addActionListener(l);
    }
}
