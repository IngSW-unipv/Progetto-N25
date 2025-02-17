package it.unipv.ingsw.lasout.view.group;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GroupPanel extends JPanel {
    private JButton actionButton;  // esempio di bottone per un'azione specifica

    public GroupPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(205, 133, 63)); // colore di sfondo personalizzato

        JLabel titleLabel = new JLabel("Interfaccia Gruppi");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        actionButton = new JButton("Esegui Azione");
        add(actionButton, BorderLayout.CENTER);
    }

    // Metodo per registrare un listener al bottone, così il controller può aggiungerci la logica
    public void addActionButtonListener(ActionListener listener) {
        actionButton.addActionListener(listener);
    }
}
