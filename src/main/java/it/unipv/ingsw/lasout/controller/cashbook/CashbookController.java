package it.unipv.ingsw.lasout.controller.cashbook;

import it.unipv.ingsw.lasout.controller.cashbook.buttonModifiers.ButtonEditor;
import it.unipv.ingsw.lasout.controller.cashbook.buttonModifiers.ButtonRenderer;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.cashbook.Cashbook;
import it.unipv.ingsw.lasout.model.transaction.Transaction;
import it.unipv.ingsw.lasout.view.cashbook.CashbookPanel;
import it.unipv.ingsw.lasout.view.cashbook.CashbookItem;
import it.unipv.ingsw.lasout.view.cashbook.transactions.AddTransactionDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CashbookController {
    private static CashbookPanel cashbookPanel;
    private static Cashbook activeCashbook;

    // da utilizzare in MainUIView
    public CashbookController(CashbookPanel panel) {
        cashbookPanel = panel;
        initController();
    }

    // operazioni eseguite al caricamento del panel
    public static void load() {
        setUpJComboBox();
    }

    public static void initController() {
        // listener del combo box
        cashbookPanel.addComboBoxListener(e -> {
            CashbookItem selected = (CashbookItem) cashbookPanel.getSelectedCashbook();

            // azioni da eseguire quando viene selezionato un cashbook dal combo box
            if (selected != null && selected.getId() != -1) {
                activeCashbook = LaVaultFacade.getInstance().getCashbookFacade().getCashbook(new Cashbook(selected.getId()));
                // test
                System.out.println("Id cashbook selezionato: "+selected.getId());

                CashbookController.setUpTransactionTable();
                CashbookController.updateSummaryLabel();
            }
        });

        cashbookPanel.addNewTransactionsButtonListener( e->{
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cashbookPanel);
            AddTransactionDialog dialog = new AddTransactionDialog(frame);

            dialog.setVisible(true);
        });


    }

    public static void setUpJComboBox() {
        // svuoto la combo box
        cashbookPanel.resetComboBox();

        // voce di default "Seleziona Cashbook..."
        cashbookPanel.addCashbookItem(new CashbookItem(-1, "Seleziona Cashbook..."));

        List<Cashbook> userCashbooks = LaVaultFacade.getInstance().getUserFacade().getCashbookOfLoggedUser();
        for (Cashbook c : userCashbooks) {
            cashbookPanel.addCashbookItem(
                new CashbookItem(c.getId(), c.getName())
            );
        }
    }

    public static Cashbook getActiveCashbook() {
        return activeCashbook;
    }

    public static void updateSummaryLabel() {
        double total = LaVaultFacade.getInstance().getCashbookFacade().calculateSummary(activeCashbook);
        cashbookPanel.setSummaryText("Sommario: "+String.format( "%.2f", total));
    }

    public static void setUpTransactionTable() {
        DefaultTableModel model = cashbookPanel.getTableModel();
        Cashbook selectedCashbook = LaVaultFacade.getInstance().getCashbookFacade().getCashbook(new Cashbook(cashbookPanel.getSelectedCashbook().getId()));
        List<Transaction> transactionList=selectedCashbook.getTransactionList();

        // cancella righe delle precedenti selezioni
        model.setRowCount(0);

        // creazione di ogni riga della tabella
        for (Transaction t : transactionList) {
            Object[] rowData = new Object[] {
                    t.getDate(),
                    t.getAmount(),
                    t.getCategory(),
                    t.getNotes(),
                    "Edit"          // placeholder che poi viene coperto dal bottone
            };
            model.addRow(rowData);

            // aggiungo bottone all'ultima cella
            JTable table = cashbookPanel.getTransactionsTable();
            table.getColumn("Edit").setCellRenderer(new ButtonRenderer());
            table.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox()));

        }
    }
}

