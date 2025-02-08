package it.unipv.ingsw.lasout.model.cashbook.exception;

public class CashbookAlreadyExisting extends RuntimeException {
    public CashbookAlreadyExisting(String message) {
        super(message);
    }
}
