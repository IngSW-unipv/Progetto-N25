package it.unipv.ingsw.lasout.view.cashbook.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddCashbookDialog extends JDialog {

    private JLabel titleLabel;
    private JTextField nameField;
    private JButton saveButton;

    public AddCashbookDialog(Frame parent) {
        super(parent, true);

        setTitle("Add Cashbook");
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        titleLabel = new JLabel("Create Cashbook", SwingConstants.CENTER);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        // spazio
        mainPanel.add(Box.createVerticalStrut(20));

        // sezione textfield
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel nameLabel = new JLabel("Name:");
        namePanel.add(nameLabel);
        nameField = new JTextField(15);
        namePanel.add(nameField);
        mainPanel.add(namePanel);

        // spazio
        mainPanel.add(Box.createVerticalStrut(20));

        // save
        saveButton = new JButton("Save");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveButton);
        mainPanel.add(buttonPanel);

        getContentPane().add(mainPanel);
    }

    // getter e setter
    public JTextField getNameField() {
        return nameField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setNameField(String text) {
        nameField.setText(text);
    }

    //listener
    public void addSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

}