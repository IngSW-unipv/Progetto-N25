package it.unipv.ingsw.lasout.facade.user;

import it.unipv.ingsw.lasout.model.user.User;

//interfaccia del ConcreteUserFacade
public interface IUserFacade {

    /**
     * Metodo di aggiunta dell'account di un utente al DB
     * @param userCarrier utente fittizio con le sue credenziali: username, password ed email
     */
    boolean createAccount(User userCarrier);

    /**
     * Metodo di eliminazione dell'account di un certo utente
     * @param userCarrier
     *
     * TODO 1: Il sistema chiede all’utente su quale conto\carta inviare tutto il denaro del suo Vault
     *         Il sistema verifica che le informazioni siano valide
     *         Le informazioni vengono approvate
     * TODO 2: è un problema se non si riaggiornano tutti gli indici dell'utente? e quindi eliminando un utente rimangano dei buchi di numeri (elimino dema(id=3) e gli indici saranno poi 1 2 4 5 6)
     */
    boolean deleteAccount(User userCarrier);

    /**
     * Metodo che aggiorna la password di un utente che è già registrato nell'applicazione
     * @param userCarrier utente con le sue credenziali (username, email e password)
     * @param newPassword nuova password che l'utente vuole aggiornare dell'account
     * @return true se la password è stata cambiata correttamente (false per gli altri casi in cui non si è riusciti a modificarla)
     */
    boolean updateAccount(User userCarrier,String newPassword);

}
