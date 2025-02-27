package it.unipv.ingsw.lasout.controller.cashbook;

import it.unipv.ingsw.lasout.controller.cashbook.buttonModifiers.ButtonEditor;
import it.unipv.ingsw.lasout.controller.cashbook.buttonModifiers.ButtonRenderer;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.cashbook.Cashbook;
import it.unipv.ingsw.lasout.model.cashbook.exception.CannotDeleteDefaultCashbookException;
import it.unipv.ingsw.lasout.model.cashbook.exception.CashbookAlreadyExistingException;
import it.unipv.ingsw.lasout.model.transaction.ManualTransaction;
import it.unipv.ingsw.lasout.model.transaction.Transaction;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.view.cashbook.CashbookPanel;
import it.unipv.ingsw.lasout.view.cashbook.CashbookItem;
import it.unipv.ingsw.lasout.view.cashbook.dialogs.AddCashbookDialog;
import it.unipv.ingsw.lasout.view.cashbook.dialogs.EditCashbookDialog;
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
        setUpCashbookComboBox();
    }

    public static void initController() {
        // listener del combo box
        cashbookPanel.addCashbookComboBoxListener(e -> {
            CashbookItem selected = (CashbookItem) cashbookPanel.getSelectedCashbook();

            // azioni da eseguire quando viene selezionato un cashbook dal combo box
            if (selected != null && selected.getId() != -1) {
                activeCashbook = LaVaultFacade.getInstance().getCashbookFacade().getCashbook(new Cashbook(selected.getId()));

                //test
                System.out.println("Id cashbook selezionato: " + selected.getId());

                //azioni al resto del panel
                CashbookController.setUpTransactionTable();
                CashbookController.updateSummaryLabel();
            } else {
                // se seleziono "Seleziona Cashbook..."
                activeCashbook = null;
                // resetto righe tabella
                DefaultTableModel model = cashbookPanel.getTableModel();
                model.setRowCount(0);

                cashbookPanel.setSummaryText("Sommario: -");
                System.out.println("Default selected: activeCashbook reset e tabella svuotata");
            }
        });

        cashbookPanel.addNewTransactionsButtonListener( e->{
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cashbookPanel);
            AddTransactionDialog addTransactionDialog = new AddTransactionDialog(frame);

            // aggiungo listener al bottone della finestra appena creata
            addTransactionDialog.addSaveButtonActionListener(e1 -> {
                try{
                    double amount = Double.parseDouble(addTransactionDialog.getAmountField().getText());
                    String date = addTransactionDialog.getDateField().getText();
                    String category = addTransactionDialog.getCategoryField().getText();
                    String notes = addTransactionDialog.getNotesField().getText();

                    ManualTransaction transaction = new ManualTransaction(amount, date, category, notes);
                    System.out.println(activeCashbook.toString());

                    try{
                        LaVaultFacade.getInstance().getCashbookFacade().addTransaction(activeCashbook, transaction);
                        JOptionPane.showMessageDialog(
                                addTransactionDialog,
                                "Transaction added successfully",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } catch (NullPointerException npe) {
                        JOptionPane.showMessageDialog(
                                addTransactionDialog,
                                "Unable to add transaction",
                                "Save Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(
                            addTransactionDialog,
                            "Incorrect amount field format, only numbers and '.' allowed",
                            "Format error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    addTransactionDialog.getAmountField().setText("0");
                }catch (Exception exception) {
                    JOptionPane.showMessageDialog(
                            addTransactionDialog,
                            exception.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } finally {
                    CashbookController.setUpTransactionTable();
                    CashbookController.updateSummaryLabel();
                    addTransactionDialog.dispose();
                }
            });
            // non mostrare se nessun cashbook è selezionato
            CashbookItem selected = (CashbookItem) cashbookPanel.getSelectedCashbook();
            if (selected != null && selected.getId() != -1) {
                addTransactionDialog.setVisible(true);
            }

        });

        cashbookPanel.addEditCashbookButtonListener(editEvent -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cashbookPanel);
            EditCashbookDialog editCashbookDialog = new EditCashbookDialog(frame);

            editCashbookDialog.setNameField(cashbookPanel.getSelectedCashbook().getName());

            editCashbookDialog.addDeleteButtonListener(e1 ->{
                try {
                    LaVaultFacade.getInstance().getCashbookFacade().deleteCashbook(activeCashbook);
                    JOptionPane.showMessageDialog(
                            editCashbookDialog,
                            "Cashbook deleted successfully",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } catch (CannotDeleteDefaultCashbookException exception) {
                    JOptionPane.showMessageDialog(
                            editCashbookDialog,
                            exception.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } finally{
                    setUpCashbookComboBox();
                    setUpTransactionTable();
                    updateSummaryLabel();
                    editCashbookDialog.dispose();
                }
            });

            editCashbookDialog.addSaveButtonListener(saveEvent ->{
                String newName = editCashbookDialog.getNameField().getText();
                activeCashbook.setName(newName);
                try {
                    LaVaultFacade.getInstance().getCashbookFacade().editCashbook(activeCashbook);
                    JOptionPane.showMessageDialog(
                            editCashbookDialog,
                            "Cashbook edited successfully",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(
                            editCashbookDialog,
                            exception.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } finally{
                    setUpCashbookComboBox();
                    editCashbookDialog.dispose();
                }
            });

            // non mostrare se nessun cashbook è selezionato
            CashbookItem selected = (CashbookItem) cashbookPanel.getSelectedCashbook();
            if (selected != null && selected.getId() != -1) {
                editCashbookDialog.setVisible(true);
            }
        });

        cashbookPanel.addCreateCashbookButtonListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cashbookPanel);
            AddCashbookDialog addCashbookDialog = new AddCashbookDialog(frame);

            addCashbookDialog.addSaveButtonListener(e1 -> {
                User loggedUser = LaVaultFacade.getInstance().getSessionFacade().getLoggedUser();
                String name = addCashbookDialog.getNameField().getText();
                Cashbook newCashbook = new Cashbook(loggedUser, name, false);

                try {
                    LaVaultFacade.getInstance().getCashbookFacade().saveCashbook(newCashbook);
                    JOptionPane.showMessageDialog(
                            addCashbookDialog,
                            "Cashbook Added successfully",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (NullPointerException npe) {
                    JOptionPane.showMessageDialog(
                            addCashbookDialog,
                            npe.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } catch (CashbookAlreadyExistingException exception) {
                    JOptionPane.showMessageDialog(
                            addCashbookDialog,
                            exception.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } finally {
                    setUpCashbookComboBox();
                    addCashbookDialog.dispose();
                }
            });

            addCashbookDialog.setVisible(true);

        });


    }

    public static void setUpCashbookComboBox() {
        // svuoto la combo box
        cashbookPanel.resetCashbookComboBox();

        // voce di default "Seleziona Cashbook..."
        cashbookPanel.addCashbookItem(new CashbookItem(-1, "Seleziona Cashbook..."));

        List<Cashbook> userCashbooks = LaVaultFacade.getInstance().getUserFacade().getCashbookOfLoggedUser();
        for (Cashbook c : userCashbooks) {
            cashbookPanel.addCashbookItem(
                new CashbookItem(c.getId(), c.getName())
            );
        }

        cashbookPanel.getCashbookComboBox().setSelectedIndex(0);
    }

    public static Cashbook getActiveCashbook() {
        return activeCashbook;
    }

    public static void updateSummaryLabel() {
        double total = LaVaultFacade.getInstance().getCashbookFacade().calculateSummary(activeCashbook);
        cashbookPanel.setSummaryText("Sommario: "+String.format( "%.2f", total));
    }

    public static void setUpTransactionTable() {
        JTable table = cashbookPanel.getTransactionsTable();
        // se la tabella è in modalità editing per impedire malfunzionamento bottone
        if (table.isEditing()) {
            table.getCellEditor().cancelCellEditing();
        }

        DefaultTableModel model = cashbookPanel.getTableModel();
        // reimposta le intestazioni
        model.setColumnIdentifiers(new Object[]{"Date", "Amount", "Category", "Notes", "Edit"});

        CashbookItem selectedItem = cashbookPanel.getSelectedCashbook();
        if (selectedItem == null || selectedItem.getId() == -1) {
            model.setRowCount(0);
            return;
        }

        Cashbook selectedCashbook = LaVaultFacade.getInstance()
                .getCashbookFacade().getCashbook(new Cashbook(selectedItem.getId()));
        if (selectedCashbook == null) {
            model.setRowCount(0);
            return;
        }
        activeCashbook = selectedCashbook;

        // svuota tabella prima di caricare
        model.setRowCount(0);

        // imposta i bottoni edit
        table.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        table.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox()));

        List<Transaction> transactionList = selectedCashbook.getTransactionList();
        if (transactionList != null) {
            for (Transaction t : transactionList) {
                Object[] rowData = {
                        t.getDate(),
                        t.getAmount(),
                        t.getCategory(),
                        t.getNotes(),
                        "Edit"              // placeholder che verrà sostituito dal bottone
                };
                model.addRow(rowData);
            }
        }
    }

}

