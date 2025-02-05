package it.unipv.ingsw.lasout.model.vault;

public class PaymentMethodFactory {

	public static PaymentMethod createPayment(String method, String numeroCarta, String dataScadenza, String cvv, String iban) {
        switch (method.toLowerCase()) {
            case "carta_credito":
                return new CreditCard(numeroCarta, dataScadenza, cvv);
            case "paypal":
                return new PayPal(numeroCarta);
            case "conto_corrente":
                return new CurrentAccount(iban);
            default:
                throw new IllegalArgumentException("Metodo di pagamento '" + method + "' non supportato.");
        }
    }
}
