package it.unipv.ingsw.lasout.model.vault;

public interface PaymentMethod {
	boolean autorizza();
	String getMethodName();
}
