package it.unipv.ingsw.lasout.model.user.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super("User "+message+" already exist in the Database");
    }
}
