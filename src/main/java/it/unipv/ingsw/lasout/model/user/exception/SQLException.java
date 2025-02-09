package it.unipv.ingsw.lasout.model.user.exception;

public class SQLException extends RuntimeException {
    public SQLException(String message) {
        super("Error during the execution of the query: "+message);
    }
}
