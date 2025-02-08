package it.unipv.ingsw.lasout.model.transaction.exception;

public class CannotEditAutomaticTransaction extends RuntimeException {
    public CannotEditAutomaticTransaction(String message) {
        super(message);
    }
}
