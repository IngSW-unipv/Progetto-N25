package it.unipv.ingsw.lasout.view.vault;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class PaymentMethodView extends JPanel{
	private JComboBox<String> paymentTypeComboBox;
    private JButton addMethodButton;
    private JButton removeMethodButton;

    public PaymentMethodView() {
        // Uso un layout FlowLayout per posizionare i componenti in linea, allineati a destra
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setOpaque(false); // Rende il pannello trasparente

        // Creo il JComboBox con le opzioni dei metodi di pagamento
        paymentTypeComboBox = new JComboBox<>();
        paymentTypeComboBox.addItem("Seleziona Metodo...");
        paymentTypeComboBox.addItem("CreditCard");
        paymentTypeComboBox.addItem("PayPal");
        paymentTypeComboBox.addItem("CurrentAccount");

        // Creo i pulsanti per aggiungere e rimuovere il metodo
        addMethodButton = new JButton("Aggiungi Metodo");

        // Aggiungi i componenti al pannello
        add(paymentTypeComboBox);
        add(addMethodButton);
    }

    public String getSelectedPaymentType() {
        return (String) paymentTypeComboBox.getSelectedItem();
    }

    public void addAddMethodListener(ActionListener listener) {
        addMethodButton.addActionListener(listener);
    }

    public void addRemoveMethodListener(ActionListener listener) {
        removeMethodButton.addActionListener(listener);
    }
}
