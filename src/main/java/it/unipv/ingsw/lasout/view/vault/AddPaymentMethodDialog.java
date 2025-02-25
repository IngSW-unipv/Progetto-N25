package it.unipv.ingsw.lasout.view.vault;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPaymentMethodDialog extends JDialog{
	
	private JComboBox<String> methodCombo;
    private JPanel cardsPanel;
    private CardLayout cardLayout;
    private JButton confirmButton, cancelButton;
    
    // Campi per CreditCard
    private JTextField ccNumberField, ccMonthField, ccYearField, ccCVVField;
    // Campi per PayPal
    private JTextField ppNumberField;
    // Campi per Conto Corrente
    private JTextField ibanField;
    
    public AddPaymentMethodDialog(Frame owner) {
        super(owner, "Aggiungi Metodo di Pagamento", true);
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10,10));
        
        // TOP: ComboBox per la selezione del tipo
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        methodCombo = new JComboBox<>();
        methodCombo.addItem("CreditCard");
        methodCombo.addItem("PayPal");
        methodCombo.addItem("CurrentAccount");
        topPanel.add(new JLabel("Seleziona il metodo:"));
        topPanel.add(methodCombo);
        add(topPanel, BorderLayout.NORTH);
        
        // CENTER: Pannello dinamico per i campi, con CardLayout
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        
        // Pannello per Credit Card
        JPanel ccPanel = new JPanel(new GridLayout(4,2,5,5));
        ccPanel.add(new JLabel("Numero Carta:"));
        ccNumberField = new JTextField(15);
        ccPanel.add(ccNumberField);
        
        ccPanel.add(new JLabel("Mese:"));
        ccMonthField = new JTextField(2);
        ccPanel.add(ccMonthField);
        
        ccPanel.add(new JLabel("Anno:"));
        ccYearField = new JTextField(4);
        ccPanel.add(ccYearField);
        
        ccPanel.add(new JLabel("CVV:"));
        ccCVVField = new JTextField(3);
        ccPanel.add(ccCVVField);
        
        // Pannello per PayPal
        JPanel ppPanel = new JPanel(new GridLayout(1,2,5,5));
        ppPanel.add(new JLabel("Numero Carta:"));
        ppNumberField = new JTextField(15);
        ppPanel.add(ppNumberField);
        
        // Pannello per Conto Corrente
        JPanel caPanel = new JPanel(new GridLayout(1,2,5,5));
        caPanel.add(new JLabel("IBAN:"));
        ibanField = new JTextField(20);
        caPanel.add(ibanField);
        
        // Aggiungo i pannelli al cardsPanel
        cardsPanel.add(ccPanel, "CreditCard");
        cardsPanel.add(ppPanel, "PayPal");
        cardsPanel.add(caPanel, "CurrentAccount");
        
        add(cardsPanel, BorderLayout.CENTER);
        
        // Listener per cambiare il pannello in base al tipo selezionato
        methodCombo.addActionListener(e -> {
            String selected = (String) methodCombo.getSelectedItem();
            cardLayout.show(cardsPanel, selected);
        });
        
        // BOTTOM: Pulsanti per confermare o annullare
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmButton = new JButton("Conferma");
        cancelButton = new JButton("Annulla");
        bottomPanel.add(confirmButton);
        bottomPanel.add(cancelButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(getOwner());
    }
    
    // Metodi getter per recuperare i dati inseriti
    public String getSelectedType() {
        return (String) methodCombo.getSelectedItem();
    }
    
    public String getCcNumber() { return ccNumberField.getText(); }
    public String getCcMonth() { return ccMonthField.getText(); }
    public String getCcYear() { return ccYearField.getText(); }
    public String getCcCVV() { return ccCVVField.getText(); }
    
    public String getPpNumber() { return ppNumberField.getText(); }
    
    public String getIban() { return ibanField.getText(); }
    
    public void addConfirmListener(ActionListener al) {
        confirmButton.addActionListener(al);
    }
    
    public void addCancelListener(ActionListener al) {
        cancelButton.addActionListener(al);
    }
}
