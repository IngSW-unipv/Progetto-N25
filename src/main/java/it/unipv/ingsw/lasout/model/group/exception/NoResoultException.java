package it.unipv.ingsw.lasout.model.group.exception;

public class NoResoultException extends RuntimeException {
    public NoResoultException(String message) {
        super("the resoult set has no resoult"+ message);
    }
}
