package it.unipv.ingsw.lasout.facade.user;

import it.unipv.ingsw.lasout.model.user.User;

public interface ISessionFacade {

    /**
     * Metodo che restituisce un true se l'utente ha effettuato con successo il login
     * @return
     */
    boolean isLogged();

    /**
     * Metodo che restituisce l'user con solo il suo id
     * @return
     */
    User getLoggedUser();

    /**
     * Metodo per il login che prende in ingresso i dati di un utente e tramite "UserDAO":
     * 1) setta un utente (loggedUser) con solo il suo id;
     * 2) modifica la variabile booleana (true se ha loggato correttamente; false (settata di default dal costruttore) negli altri casi)
     * @param user utente fittizio con le sue credenziali: username, password ed email
     */
    void login(User user);

    /**
     * Metodo che slogga l'utente inserito se e solo se è presente nel db e ha già effettuato il login allora posso fargli fare il logout
     */
    void logout();
}
