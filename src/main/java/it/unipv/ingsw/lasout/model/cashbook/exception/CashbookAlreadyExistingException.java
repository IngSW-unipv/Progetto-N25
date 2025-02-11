package it.unipv.ingsw.lasout.model.cashbook.exception;

public class CashbookAlreadyExistingException extends RuntimeException {
    public CashbookAlreadyExistingException(String message) {
        super(message);
    }
}
