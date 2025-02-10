package it.unipv.ingsw.lasout.model.group.exception;

public class CantSaveException extends RuntimeException {
    public CantSaveException(String message) {
        super("Can't save pojo" +message);
    }
}
