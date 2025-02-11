package it.unipv.ingsw.lasout.facade.exception;

public class ExecutorNotAdminException extends RuntimeException {
    public ExecutorNotAdminException(String message) {
        super(message);
    }
}
