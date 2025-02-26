package it.unipv.ingsw.lasout.view.group.settings;

import it.unipv.ingsw.lasout.view.LaColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GroupSettingsDialog extends JDialog {

    private JButton eliminaUtenti;
    private JButton lasciaGruppo;
    private JButton eliminaGruppo;
    private RemoveParticipantDialog remuveDialog;
    private JLabel admin;

    public GroupSettingsDialog(Frame parent) {
        super(parent, "Impostazioni Gruppo", true);

        // Imposta dimensioni del dialog
        setSize(250, 300);
        setLocationRelativeTo(parent);

        //pannello principale con BoxLayout in asse Y
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(LaColor.SFONDO);

        admin =new JLabel();
        eliminaUtenti = new JButton("Elimina utenti");
        lasciaGruppo = new JButton("Lascia gruppo");
        eliminaGruppo = new JButton("Elimina gruppo");

        // Allineamento orizzontale al centro
        admin.setAlignmentX(Component.CENTER_ALIGNMENT);
        eliminaUtenti.setAlignmentX(Component.CENTER_ALIGNMENT);
        lasciaGruppo.setAlignmentX(Component.CENTER_ALIGNMENT);
        eliminaGruppo.setAlignmentX(Component.CENTER_ALIGNMENT);

        //dimensione preferita/massima
        Dimension buttonSize = new Dimension(180, 100);
        eliminaUtenti.setMaximumSize(buttonSize);
        lasciaGruppo.setMaximumSize(buttonSize);
        eliminaGruppo.setMaximumSize(buttonSize);

        admin.setForeground(LaColor.FONT);
        eliminaUtenti.setForeground(LaColor.FONT);
        lasciaGruppo.setForeground(LaColor.FONT);
        eliminaGruppo.setForeground(LaColor.FONT);

        eliminaUtenti.setBackground(LaColor.BTN_SFONDO);
        lasciaGruppo.setBackground(LaColor.BTN_SFONDO);
        eliminaGruppo.setBackground(LaColor.BTN_SFONDO);

        remuveDialog = new RemoveParticipantDialog(parent);

        //spazi verticali tra i pulsanti
        panel.add(Box.createVerticalGlue());
        panel.add(admin);
        panel.add(Box.createVerticalStrut(40));
        panel.add(eliminaUtenti);
        panel.add(Box.createVerticalStrut(40)); // spazio tra i pulsanti
        panel.add(lasciaGruppo);
        panel.add(Box.createVerticalStrut(40));
        panel.add(eliminaGruppo);
        panel.add(Box.createVerticalGlue());

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

    public void setAmin(String a) {
        admin.setText(a);
    }
}