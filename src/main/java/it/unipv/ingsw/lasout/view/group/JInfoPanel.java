package it.unipv.ingsw.lasout.view.group;

import it.unipv.ingsw.lasout.model.group.spesa.Spesa;

import javax.swing.*;
import java.awt.*;

public class JInfoPanel extends JPanel {
    private JScrollPane leftScrollPane;
    private JScrollPane rightScrollPane;
    private JPanel leftPanel;  // Contenitore per aggiungere "righe" (es. spese)
    private JPanel rightPanel; // Contenitore per debiti o altre informazioni

    public JInfoPanel() {
        // Imposta il layout con GridBagLayout
        setLayout(new GridBagLayout());
        setBackground(Color.RED); // Sfondo generale, o cambia se preferisci

        // Crea i pannelli interni con layout verticale
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Crea gli scroll pane per ciascun pannello
        leftScrollPane = new JScrollPane(leftPanel);
        rightScrollPane = new JScrollPane(rightPanel);

        // Imposta eventuali bordi per gli scroll pane
        leftScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        rightScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 0;
        gbc.weighty = 1.0;

        // Imposta il pannello sinistro al 40% (peso 0.4)
        gbc.gridx = 0;
        gbc.weightx = 0.65;
        add(leftScrollPane, gbc);

        // Imposta il pannello destro al 60% (peso 0.6)
        gbc.gridx = 1;
        gbc.weightx = 0.35;
        add(rightScrollPane, gbc);
    }

    // Getter per il pannello sinistro: il controller può aggiungere "righe" qui
    public JPanel getLeftPanel() {
        return leftPanel;
    }

    // Getter per il pannello destro
    public JPanel getRightPanel() {
        return rightPanel;
    }

    public void addExpenseLine(ExpenseRowPanel ex){
        leftPanel.add(ex);
    }

}