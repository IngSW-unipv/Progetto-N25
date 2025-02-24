package it.unipv.ingsw.lasout.model.transaction.exception;

public class CannotDeleteAutomaticTransactionException extends RuntimeException {
    public CannotDeleteAutomaticTransactionException() {
        super("Cannot delete automatic transaction");
    }
}
