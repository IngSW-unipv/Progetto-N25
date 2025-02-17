package it.unipv.ingsw.lasout.view.vault;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VaultPanel extends JPanel {

    private JLabel countLabel;       // Etichetta che mostra il valore del contatore
    private JButton incrementButton; // Bottone che incrementa il contatore

    public VaultPanel() {
        setLayout(new BorderLayout());

        // Esempio di sfondo personalizzato
        setBackground(new Color(139, 69, 19)); // un colore marrone simile

        // Inizialmente impostiamo il contatore a 0
        countLabel = new JLabel("Contatore: 0");
        countLabel.setFont(new Font("Arial", Font.BOLD, 18));
        countLabel.setHorizontalAlignment(JLabel.CENTER);

        incrementButton = new JButton("Incrementa Contatore");

        // Aggiungiamo i componenti al layout
        add(countLabel, BorderLayout.NORTH);
        add(incrementButton, BorderLayout.CENTER);
    }

    /**
     * Metodo per aggiornare il testo della label quando cambia il contatore.
     *
     * @param newCount nuovo valore del contatore
     */
    public void setCountLabel(int newCount) {
        countLabel.setText("Contatore: " + newCount);
    }

    /**
     * Metodo per registrare un listener sul bottone di incremento.
     */
    public void addIncrementListener(ActionListener listener) {
        incrementButton.addActionListener(listener);
    }
}