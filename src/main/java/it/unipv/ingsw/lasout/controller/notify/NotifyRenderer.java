package it.unipv.ingsw.lasout.controller.notify;

import it.unipv.ingsw.lasout.model.notify.Notify;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotifyRenderer extends JPanel implements ListCellRenderer<Notify> {

    private NotifyController notifyController;

    private JLabel label;
    private JButton trashButton;
    private JPanel buttonPanel;

    public NotifyRenderer(NotifyController controller) {
        this.notifyController = controller;

        setLayout(new BorderLayout());
        setOpaque(true);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Pannello superiore per la label e l'icona
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        // Etichetta per il testo
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.LEFT);

        // Icona del cestino come JButton ridimensionato
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/icons/trash.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        trashButton = new JButton(scaledIcon);
        trashButton.setPreferredSize(new Dimension(20, 20));
        trashButton.setContentAreaFilled(false);
        trashButton.setBorderPainted(false);
        trashButton.setFocusPainted(false);
        trashButton.setOpaque(false);
        trashButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Aggiunge la label e l'icona al topPanel
        topPanel.add(label, BorderLayout.WEST);
        topPanel.add(trashButton, BorderLayout.EAST);

        // Pannello per i bottoni
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        buttonPanel.setOpaque(false);


        // Spazio vuoto tra la label e i bottoni
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setPreferredSize(new Dimension(0, 10)); // Spazio verticale di 10 pixel

        // Aggiunge i componenti al pannello principale
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Notify> list, Notify value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {

        label.setText(value != null ? value.getDescription() : "");

        buttonPanel.removeAll();

        value.getNotifyAction().build(notifyController, buttonPanel);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            label.setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            label.setForeground(list.getForeground());
        }

        return this;
    }

    public JButton getTrashButton() {
        return trashButton;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }
}
