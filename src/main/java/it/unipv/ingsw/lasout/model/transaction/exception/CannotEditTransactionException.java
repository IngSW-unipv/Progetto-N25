package it.unipv.ingsw.lasout.model.transaction.exception;

public class CannotEditTransactionException extends RuntimeException {
    public CannotEditTransactionException(String message) {
        super("Cannot edit transaction exception");
    }

    public CannotEditTransactionException() {

    }
}
