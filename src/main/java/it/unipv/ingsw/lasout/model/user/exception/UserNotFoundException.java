package it.unipv.ingsw.lasout.model.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        System.out.println("User not found: " + message);
    }
}
