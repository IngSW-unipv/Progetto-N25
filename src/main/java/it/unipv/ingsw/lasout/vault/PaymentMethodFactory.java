package it.unipv.ingsw.lasout.vault;

public class PaymentMethodFactory {
	public static PaymentMethod createPayment(String method, RegisteredUser user, String numeroCarta, String dataScadenza, String cvv, String iban) {
        switch (method.toLowerCase()) {
            case "credit_card":
                return new CreditCard(user, numeroCarta, dataScadenza, cvv);
            case "paypal":
                return new PayPal(user);
            case "bank_transfer":
                return new CurrentAccount(user, iban);
            default:
                throw new IllegalArgumentException("Metodo di pagamento '" + method + "' non supportato.");
        }
    }
}
