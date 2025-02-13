package it.unipv.ingsw.lasout.facade.user;

import it.unipv.ingsw.lasout.model.user.User;

public interface ISessionFacade {

    /**
     * Metodo che restituisce un true se l'utente è loggato e false se non ha fatto precedentemente il login
     * @return true o false
     */
    boolean isLogged();

    /**
     * Metodo che restituisce l'user con tutti i suoi dati
     * @return user
     */
    User getLoggedUser();

    /**
     * Metodo per il serchUser che prende in ingresso i dati di un utente e tramite "UserDAO":
     * 1) setta un utente (loggedUser) con tutti i suoi dati;
     * 2) modifica la variabile booleana (true se ha loggato correttamente; false (settata di default dal costruttore) negli altri casi)
     * @param user utente fittizio con le sue credenziali username-password o email-password
     */
    void login(User user);

    /**
     * Metodo che slogga l'utente inserito se e solo se:
     * 1) è presente nel DB;
     * 2) ha già effettuato il login allora posso fargli fare il logout
     */
    void logout();
}
