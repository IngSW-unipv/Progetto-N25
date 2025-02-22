package it.unipv.ingsw.lasout.view.group.aggiunta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewGroupDialog extends JDialog {

    private JTextField nomeGruppoField;
    private JButton creaButton;

    public NewGroupDialog(Frame parent) {
        super(parent, "Crea Nuovo Gruppo", true);
        setSize(300, 200);

        setLocationRelativeTo(parent);

        // Pannello principale con BoxLayout verticale e sfondo blu
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(230, 240, 250));

        panel.add(Box.createVerticalGlue());

        // Label sopra il campo di testo
        JLabel label = new JLabel("Nome gruppo:");
        label.setForeground(Color.DARK_GRAY);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(5));

        // Campo di testo con bordo
        nomeGruppoField = new JTextField();
        nomeGruppoField.setMaximumSize(new Dimension(250, 30));
        nomeGruppoField.setBorder(BorderFactory.createLineBorder(new Color(200, 210, 230), 2));
        nomeGruppoField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(nomeGruppoField);
        panel.add(Box.createVerticalStrut(20));


        // Bottone "Crea" centrato, di dimensioni maggiori, con sfondo nero
        creaButton = new JButton("Crea");
        creaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        creaButton.setPreferredSize(new Dimension(150, 40));
        creaButton.setMaximumSize(new Dimension(150, 40));
        creaButton.setBackground(new Color(176, 196, 222));
        creaButton.setForeground(Color.DARK_GRAY);
        creaButton.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(creaButton);

        panel.add(Box.createVerticalGlue());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    public void addCreaListener(ActionListener l) {
        creaButton.addActionListener(l);
    }

    public String getNomeGruppoText() {
        return nomeGruppoField.getText();
    }

    public JTextField getNomeGruppoField() {
        return nomeGruppoField;
    }
}