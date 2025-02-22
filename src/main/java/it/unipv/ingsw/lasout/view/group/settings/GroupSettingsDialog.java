package it.unipv.ingsw.lasout.view.group.settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GroupSettingsDialog extends JDialog {

    private JButton eliminaUtenti;
    private JButton lasciaGruppo;
    private JButton eliminaGruppo;
    private RemoveParticipantDialog remuveDialog;

    public GroupSettingsDialog(Frame parent) {
        super(parent, "Impostazioni Gruppo", true);

        // Imposta dimensioni del dialog
        setSize(250, 300);
        setLocationRelativeTo(parent);

        // Creiamo un pannello principale con BoxLayout in asse Y
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(230, 240, 250));

        // Creiamo i pulsanti
        eliminaUtenti = new JButton("Elimina utenti");
        lasciaGruppo = new JButton("Lascia gruppo");
        eliminaGruppo = new JButton("Elimina gruppo");

        // Allineamento orizzontale al centro
        eliminaUtenti.setAlignmentX(Component.CENTER_ALIGNMENT);
        lasciaGruppo.setAlignmentX(Component.CENTER_ALIGNMENT);
        eliminaGruppo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Impostiamo una dimensione preferita/massima più grande
        Dimension buttonSize = new Dimension(180, 100);
        eliminaUtenti.setMaximumSize(buttonSize);
        lasciaGruppo.setMaximumSize(buttonSize);
        eliminaGruppo.setMaximumSize(buttonSize);

        eliminaUtenti.setForeground(Color.DARK_GRAY);
        lasciaGruppo.setForeground(Color.DARK_GRAY);
        eliminaGruppo.setForeground(Color.DARK_GRAY);

        eliminaUtenti.setBackground(new Color(176, 196, 222));
        lasciaGruppo.setBackground(new Color(176, 196, 222));
        eliminaGruppo.setBackground(new Color(176, 196, 222));

        remuveDialog = new RemoveParticipantDialog(parent);

        // e spazi verticali tra i pulsanti
        panel.add(Box.createVerticalGlue());
        panel.add(eliminaUtenti);
        panel.add(Box.createVerticalStrut(40)); // spazio tra i pulsanti
        panel.add(lasciaGruppo);
        panel.add(Box.createVerticalStrut(40));
        panel.add(eliminaGruppo);
        panel.add(Box.createVerticalGlue());

        // Aggiungiamo il pannello al dialog
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    // Metodi per aggiungere i listener su ciascun pulsante
    public void addEliminateUserListener(ActionListener l) {
        eliminaUtenti.addActionListener(l);
    }

    public void addLeaveGroupListener(ActionListener l) {
        lasciaGruppo.addActionListener(l);
    }

    public void addDelateGroupListener(ActionListener l) {
        eliminaGruppo.addActionListener(l);
    }

    public RemoveParticipantDialog getRemuveDialog() {
        return remuveDialog;
    }
}