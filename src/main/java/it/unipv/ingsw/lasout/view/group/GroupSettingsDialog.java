package it.unipv.ingsw.lasout.view.group;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GroupSettingsDialog extends JDialog {

    private JButton btnOption1;
    private JButton btnOption2;
    private JButton btnOption3;

    public GroupSettingsDialog(Frame parent) {
        // true = dialogo modale, cioè blocca la finestra sottostante finché non lo chiudi
        super(parent, "Impostazioni Gruppo", true);

        // Imposta dimensioni del dialog
        setSize(300, 200);
        setLocationRelativeTo(parent); // Centra rispetto alla finestra "parent"

        // Layout verticale
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        // In alternativa:
        // setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Crea i pulsanti
        btnOption1 = new JButton("Opzione 1");
        btnOption2 = new JButton("Opzione 2");
        btnOption3 = new JButton("Opzione 3");

        // Se vuoi che siano disposti esattamente uno sotto l'altro,
        // puoi usare un BoxLayout o un GridLayout(3,1)
        // Esempio con BoxLayout:
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(btnOption1);
        panel.add(Box.createVerticalStrut(10)); // spazio verticale di 10 px
        panel.add(btnOption2);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnOption3);

        add(panel);

        // Non chiudere la finestra quando clicchi "X", se preferisci
        // setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    // Metodi per aggiungere i listener su ciascun pulsante
    public void addOption1Listener(ActionListener l) {
        btnOption1.addActionListener(l);
    }

    public void addOption2Listener(ActionListener l) {
        btnOption2.addActionListener(l);
    }

    public void addOption3Listener(ActionListener l) {
        btnOption3.addActionListener(l);
    }
}
