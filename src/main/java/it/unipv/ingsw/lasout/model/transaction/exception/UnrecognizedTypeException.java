package it.unipv.ingsw.lasout.model.transaction.exception;

public class UnrecognizedTypeException extends RuntimeException {
    public UnrecognizedTypeException(String message) {
        super(message);
    }
}
