package it.unipv.ingsw.lasout.view.cashbook.transactions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditTransactionDialog extends JDialog {

    private JLabel titleLabel;
    private JTextField amountField;
    private JTextField dateField;
    private JTextField categoryField;
    private JTextField notesField;
    private JButton saveButton;
    private JButton deleteButton;

    public EditTransactionDialog(Frame parent) {
        super(parent, true);

        // dimensioni allineamento
        setTitle("Edit Transaction");
        setSize(350, 450);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // layout per componenti
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // label del titolo
        titleLabel = new JLabel("Edit Transaction", SwingConstants.CENTER);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        // spazio tra i campi
        mainPanel.add(Box.createVerticalStrut(20));

        // creazione textfields
        amountField = new JTextField(15);
        dateField = new JTextField(15);
        categoryField = new JTextField(15);
        notesField = new JTextField(15);

        // aggiunge label e textfield
        mainPanel.add(createRow("Amount:", amountField));
        mainPanel.add(createRow("Date:", dateField));
        mainPanel.add(createRow("Category:", categoryField));
        mainPanel.add(createRow("Notes:", notesField));

        // spazio tra fields e pulsanti
        mainPanel.add(Box.createVerticalStrut(20));

        // pannello pulsanti in basso a destra
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Save");
        deleteButton = new JButton("Delete Transaction");
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        mainPanel.add(buttonPanel);

        getContentPane().add(mainPanel);
    }

    /**
     * Crea la riga con label e field
     */
    private JPanel createRow(String labelText, JTextField textField) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel label = new JLabel(labelText);
        rowPanel.add(label);
        rowPanel.add(textField);
        return rowPanel;
    }

    // getters e setters

    public JTextField getAmountField() {
        return amountField;
    }

    public void setAmount(String amount) {
        this.amountField.setText(amount);
    }

    public JTextField getDateField() {
        return dateField;
    }

    public void setDate(String date) {
        this.dateField.setText(date);
    }

    public JTextField getCategoryField() {
        return categoryField;
    }

    public void setCategory(String category) {
        this.categoryField.setText(category);
    }

    public JTextField getNotesField() {
        return notesField;
    }

    public void setNotes(String notes) {
        this.notesField.setText(notes);
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDialogTitle(String newTitle) {
        this.titleLabel.setText(newTitle);
    }

    // Aggiunta di listener per i bottoni
    public void addSaveButtonActionListener(ActionListener actionListener) {
        saveButton.addActionListener(actionListener);
    }

    public void addDeleteButtonActionListener(ActionListener actionListener) {
        deleteButton.addActionListener(actionListener);
    }
}
