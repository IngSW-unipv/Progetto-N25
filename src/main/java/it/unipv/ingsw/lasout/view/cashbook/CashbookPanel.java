package it.unipv.ingsw.lasout.view.cashbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

import it.unipv.ingsw.lasout.view.LaColor;

public class CashbookPanel extends JPanel {

    private JComboBox<CashbookItem> cashbookComboBox; // Menu a tendina
    private JLabel summaryLabel;                      // Sommario:
    private JButton editCashbookButton;                   // Pulsante Impostazioni
    private JTable transactionsTable;                 // Tabella per le transazioni
    private DefaultTableModel tableModel;             // Sono gli header della tabella
    private JButton addTransactionsButton;            // Pulsante Aggiungi Transazioni
    private JButton addCashbookButton;

    public CashbookPanel() {
        setLayout(new BorderLayout());
        setBackground(LaColor.SFONDO);

        // pannello superiore con menu a tendina, sommario, impostazioni
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(LaColor.SFONDO);

        // sottopannello in alto a sinistra per il menu a tendina
        JPanel leftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftTopPanel.setBackground(LaColor.SFONDO);
        cashbookComboBox = new JComboBox<>();
        cashbookComboBox.setBackground(LaColor.BTN_SFONDO);
        cashbookComboBox.setForeground(LaColor.FONT);
        leftTopPanel.add(cashbookComboBox);

        // pulsante add nel sottopannello in alto a sinistra
        addCashbookButton = new JButton("Add");
        addCashbookButton.setBackground(LaColor.BTN_SFONDO);
        addCashbookButton.setForeground(LaColor.FONT);
        leftTopPanel.add(addCashbookButton);

        // sottopannello in alto centrale contiene il label "Sommario:"
        JPanel centerTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerTopPanel.setBackground(getBackground());
        summaryLabel = new JLabel("Sommario:");
        summaryLabel.setForeground(LaColor.FONT);
        Font currentFont = summaryLabel.getFont();
        Font biggerFont = new Font(currentFont.getName(), currentFont.getStyle(), 18);
        summaryLabel.setFont(biggerFont);
        centerTopPanel.add(summaryLabel);

        // sottopannello destro contiene il pulsante Edit Cashbook
        JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        rightTopPanel.setBackground(LaColor.SFONDO);
        editCashbookButton = new JButton("Edit Cashbook");
        editCashbookButton.setBackground(LaColor.BTN_SFONDO);
        editCashbookButton.setForeground(LaColor.FONT);
        rightTopPanel.add(editCashbookButton);

        // compongo il topPanel
        topPanel.add(leftTopPanel, BorderLayout.WEST);
        topPanel.add(centerTopPanel, BorderLayout.CENTER);
        topPanel.add(rightTopPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // pannello con tabella e con possibilità di scrollare
        String[] columnNames = { "Date", "Amount", "Category", "Notes", "Edit" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            // rendo l'ultima colonna con il pulsante cliccabile
            @Override
            public boolean isCellEditable(int row, int column) {
                // rendo editabile solo l'ultima colonna
                return column == 4;
            }
        };
        transactionsTable = new JTable(tableModel);
        transactionsTable.setRowHeight(25);
        transactionsTable.setForeground(LaColor.FONT);
        transactionsTable.setBackground(LaColor.SFONDO_SCURO);
        transactionsTable.getTableHeader().setBackground(LaColor.BTN_SFONDO);
        transactionsTable.getTableHeader().setForeground(Color.BLACK);
        transactionsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        JScrollPane scrollPane = new JScrollPane(transactionsTable);
        scrollPane.getViewport().setBackground(LaColor.SFONDO);
        add(scrollPane, BorderLayout.CENTER);

        //pulsante aggiungi transazioni nel panel in fondo
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        bottomPanel.setBackground(LaColor.SFONDO);
        addTransactionsButton = new JButton("Aggiungi Transazioni");
        addTransactionsButton.setBackground(LaColor.BTN_SFONDO);
        addTransactionsButton.setForeground(LaColor.FONT);
        addTransactionsButton.setPreferredSize(new Dimension(200, 40));
        bottomPanel.add(addTransactionsButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /*
    * Comandi per combo box
    * */
    public void resetCashbookComboBox() {
        cashbookComboBox.removeAllItems();
    }

    public void addCashbookItem(CashbookItem item) {
        cashbookComboBox.addItem(item);
    }

    public CashbookItem getSelectedCashbook() {
        return (CashbookItem) cashbookComboBox.getSelectedItem();
    }

    public JComboBox<CashbookItem> getCashbookComboBox() {
        return cashbookComboBox;
    }

    /*
     * Getters
     **/
    public JButton getEditCashbookButton() {
        return editCashbookButton;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTransactionsTable() {
        return transactionsTable;
    }

    public JButton getAddTransactionsButton() {
        return addTransactionsButton;
    }

    /*
    * Setters
    **/
    public void setSummaryText(String text) {
        summaryLabel.setText(text);
    }

    /*
     * Listeners
     **/
    public void addCashbookComboBoxListener(ActionListener listener) {
        cashbookComboBox.addActionListener(listener);
    }

    public void addEditCashbookButtonListener(ActionListener listener) {
        editCashbookButton.addActionListener(listener);
    }

    public void addNewTransactionsButtonListener(ActionListener listener) {
        addTransactionsButton.addActionListener(listener);
    }

    public void addCreateCashbookButtonListener(ActionListener listener) {
        addCashbookButton.addActionListener(listener);
    }

}
