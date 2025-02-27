package it.unipv.ingsw.lasout.view.friends;

import it.unipv.ingsw.lasout.view.LaColor;

import javax.swing.*;
import java.awt.*;

public class ListPanel extends JPanel {
    private JPanel rowsContainer;

    public ListPanel() {
        setLayout(new BorderLayout());
        setBackground(LaColor.SFONDO);

        // Contenitore verticale per le righe
        rowsContainer = new JPanel();
        rowsContainer.setLayout(new BoxLayout(rowsContainer, BoxLayout.Y_AXIS));
        rowsContainer.setBackground(Color.LIGHT_GRAY);
        // Allineamento a sinistra, così le righe si estendono a piena larghezza
        rowsContainer.setAlignmentX(LEFT_ALIGNMENT);

        // Aggiungiamo delle righe di esempio
        for (int i = 0; i < 15; i++) {
            AmicoRowPanel row = new AmicoRowPanel("Amico " + (i + 1));
            rowsContainer.add(row);
        }

        // JScrollPane per lo scroll verticale
        JScrollPane scrollPane = new JScrollPane(
                rowsContainer,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void addRow(AmicoRowPanel row) {
        rowsContainer.add(row);
        rowsContainer.revalidate();
        rowsContainer.repaint();
    }

    public JPanel getRowsContainer() {
        return rowsContainer;
    }
}
