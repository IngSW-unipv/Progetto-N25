package it.unipv.ingsw.lasout.controller.notify;

import it.unipv.ingsw.lasout.model.notify.Notify;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        // Etichetta per il testo
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.LEFT);


        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/trash.png")));
        Image scaledImage = originalIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        trashButton = new JButton(scaledIcon);
        trashButton.setPreferredSize(new Dimension(20, 20));
        trashButton.setContentAreaFilled(false);
        trashButton.setBorderPainted(false);
        trashButton.setFocusPainted(false);
        trashButton.setOpaque(false);
        trashButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        trashButton.addActionListener(e->{
            ButtonNotifyAction notifyAction = (ButtonNotifyAction) e;
            notifyController.deleteNotify(notifyAction.getNotify());
        });

        topPanel.add(label, BorderLayout.WEST);
        topPanel.add(trashButton, BorderLayout.EAST);


        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

        setBorder(new MatteBorder(0, 0, 1, 0, new Color(87, 87, 87)));

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setPreferredSize(new Dimension(0, 10)); // Spazio verticale di 10 pixel


        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Notify> list, Notify value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {

        label.setText(value != null ? value.getDescription() : "");

        buttonPanel.removeAll();

        System.out.println(value +  " - " + value.getNotifyAction());
        if(value != null && value.getNotifyAction() != null) value.getNotifyAction().build(notifyController, buttonPanel);

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
