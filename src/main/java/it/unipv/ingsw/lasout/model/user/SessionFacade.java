package it.unipv.ingsw.lasout.model.user;

public interface SessionFacade {
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
}
