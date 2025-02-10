package it.unipv.ingsw.lasout.model.group.exception;

public class CantDeleteException extends RuntimeException {
    public CantDeleteException(String message) {
        super("Cant delete"+message);
    }
}
