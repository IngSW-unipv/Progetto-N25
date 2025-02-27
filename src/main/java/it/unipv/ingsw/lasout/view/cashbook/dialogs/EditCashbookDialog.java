package it.unipv.ingsw.lasout.view.cashbook.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditCashbookDialog extends JDialog {

    private JLabel titleLabel;
    private JTextField nameField;
    private JButton saveButton;
    private JButton deleteButton;

    public EditCashbookDialog(Frame parent) {
        // finestra modale
        super(parent, true);

        // Imposta titolo e dimensioni
        setTitle("Edit Cashbook");
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Pannello principale con BoxLayout verticale
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Titolo in alto
        titleLabel = new JLabel("Edit Cashbook", SwingConstants.CENTER);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        // Spaziatura
        mainPanel.add(Box.createVerticalStrut(20));

        // Sezione per il nome del Cashbook
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel nameLabel = new JLabel("Name:");
        namePanel.add(nameLabel);

        nameField = new JTextField(15);
        namePanel.add(nameField);
        mainPanel.add(namePanel);

        // Spaziatura
        mainPanel.add(Box.createVerticalStrut(20));

        // Pannello dei pulsanti in basso a destra
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        saveButton = new JButton("Save");
        deleteButton = new JButton("Delete Cashbook");
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        mainPanel.add(buttonPanel);

        getContentPane().add(mainPanel);
    }

    /* ----- Getter e setter per i campi ----- */

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(String text) {
        nameField.setText(text);
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    /* ----- Listener ----- */

    public void addSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }
}
