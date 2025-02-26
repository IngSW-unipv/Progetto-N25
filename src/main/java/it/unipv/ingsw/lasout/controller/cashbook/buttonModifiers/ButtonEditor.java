package it.unipv.ingsw.lasout.controller.cashbook.buttonModifiers;

import it.unipv.ingsw.lasout.controller.cashbook.CashbookController;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.transaction.AutomaticTransaction;
import it.unipv.ingsw.lasout.model.transaction.ManualTransaction;
import it.unipv.ingsw.lasout.model.transaction.Transaction;
import it.unipv.ingsw.lasout.view.cashbook.transactions.EditTransactionDialog;

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

            // lancio il dialog per modificare la transazione
            Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(button);
            EditTransactionDialog editDialog = new EditTransactionDialog(parentFrame);
            //aggiorno i campi
            editDialog.setAmount(String.valueOf(transaction.getAmount()));
            editDialog.setDate(transaction.getDate());
            editDialog.setCategory(transaction.getCategory());
            editDialog.setNotes(transaction.getNotes());

            //non voglio che le transazioni automatiche siano modificabili
            try{
                ManualTransaction t = (ManualTransaction) transaction;
            } catch (ClassCastException e){
                AutomaticTransaction t = (AutomaticTransaction) transaction;
                editDialog.getAmountField().setEnabled(false);
                editDialog.getDateField().setEnabled(false);
                editDialog.getNotesField().setEnabled(false);
            }

            editDialog.addSaveButtonActionListener(e ->{
                try {
                    double newTxAmount = Double.parseDouble(editDialog.getAmountField().getText());
                    String newTxDate = editDialog.getDateField().getText();
                    String newTxCategory = editDialog.getCategoryField().getText();
                    String newTxNotes = editDialog.getNotesField().getText();

                    // Aggiorna la transazione (per le transazioni automatiche alcuni campi sono disabilitati)
                    transaction.setAmount(newTxAmount);
                    transaction.setDate(newTxDate);
                    transaction.setCategory(newTxCategory);
                    transaction.setNotes(newTxNotes);

                    // chiama la facade per aggiornare la transazione
                    boolean success = LaVaultFacade.getInstance().getTransactionFacade().editTransaction(transaction);

                    if(success) {
                        JOptionPane.showMessageDialog(
                                editDialog,
                                "Transaction edited successfully",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                editDialog,
                                "Unable to edit transaction",
                                "Edit Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }

                    CashbookController.setUpTransactionTable();
                    CashbookController.updateSummaryLabel();
                    editDialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            editDialog,
                            "Incorrect amount field format, only numbers and '.' allowed",
                            "Format error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    editDialog.getAmountField().setText(String.valueOf(transaction.getAmount()));
                }
            });

            editDialog.setVisible(true);
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