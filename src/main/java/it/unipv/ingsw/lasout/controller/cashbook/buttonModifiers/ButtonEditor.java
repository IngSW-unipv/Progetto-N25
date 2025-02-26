package it.unipv.ingsw.lasout.controller.cashbook.buttonModifiers;

import it.unipv.ingsw.lasout.model.transaction.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static it.unipv.ingsw.lasout.controller.cashbook.CashbookController.getActiveCashbook;


// mostra il bottone e gestisce le azioni eseguite
public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private int selectedRow;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        selectedRow = row;
        label = (value == null) ? "Edit" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        // azioni da eseguire se schiacciato
        if (isPushed) {
            // recupera la transazione associata alla riga in cui si trova il bottone
            Transaction transaction = getActiveCashbook().getTransactionList().get(selectedRow);

            // test, da rimpiazzare con interfaccia
            System.out.println("Edit transaction: " + transaction);
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}