package it.unipv.ingsw.lasout.view.group.settings;

import it.unipv.ingsw.lasout.view.LaColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ParticipantRowPanel extends JPanel {
    private final JLabel nameLabel;
    private final JButton espelliButton;

    public ParticipantRowPanel(String participantName) {
        // Usa BorderLayout per disporre la label e il bottone
        setLayout(new BorderLayout(5, 0));
        setBackground(LaColor.SFONDO);

        // Crea la label con il nome del partecipante
        nameLabel = new JLabel(participantName);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(nameLabel, BorderLayout.CENTER);

        // Crea il bottone "espelli"
        espelliButton = new JButton("espelli");
        // Imposta la dimensione fissa per il bottone
        Dimension btnDim = new Dimension(80, 25);
        espelliButton.setPreferredSize(btnDim);
        espelliButton.setMaximumSize(btnDim);
        espelliButton.setMinimumSize(btnDim);
        espelliButton.setForeground(LaColor.FONT);
        espelliButton.setBackground(LaColor.BTN_SFONDO);
        add(espelliButton, BorderLayout.EAST);

        // Imposta l'altezza della riga a 25 e lascia la larghezza adattabile
        setPreferredSize(new Dimension(0, 25));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
    }

    // Metodo per registrare un listener sul bottone "espelli"
    public void addEspelliListener(ActionListener l) {
        espelliButton.addActionListener(l);
    }

}
