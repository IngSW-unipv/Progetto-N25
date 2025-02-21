package it.unipv.ingsw.lasout.facade.user;

import it.unipv.ingsw.lasout.model.cashbook.Cashbook;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;

//interfaccia del ConcreteUserFacade
public interface IUserFacade {

    /**
     * Metodo di aggiunta dell'account di un utente al DB.
     * Cerco che l'utente NON sia già presente nel DB, almeno userDAO.userNotSearchedForCreateAccount lancerà un eccezione
     * di "utente non trovato" che verrà presa dal catch e solo dopo esserci entrato potrò salvare il nuovo account dell'utente nel DB
     * @param userCarrier utente fittizio con le sue credenziali: username, email e password
     */
    boolean createAccount(User userCarrier);

    /**
     * Metodo di eliminazione dell'account di un certo utente.
     * Cerco l'utente con logica di discriminazione di username-password o email-password e controllo che
     * sia presente nel DB almeno posso cancellare il suo account.
     * @param userCarrier utente loggato preso dal DB
     *
     * TODO 1: Il sistema chiede all’utente su quale conto\carta inviare tutto il denaro del suo Vault
     *         Il sistema verifica che le informazioni siano valide
     *         Le informazioni vengono approvate
     * TODO 2: è un problema se non si riaggiornano tutti gli indici dell'utente? e quindi eliminando un utente rimangano dei buchi di numeri (elimino dema(id=3) e gli indici saranno poi 1 2 4 5 6)
     */
    boolean deleteAccount(User userCarrier);

    /**
     * Metodo che aggiorna la password di un utente che è già registrato nell'applicazione.
     * Cerco l'utente con logica di discriminazione di username-password o email-password e controllo che
     * sia presente nel DB almeno posso procedere a modificargli la password.
     * @param userCarrier utente loggato preso dal DB
     * @return true se la password è stata cambiata correttamente (false per gli altri casi in cui non si è riusciti a modificarla)
     */
    boolean updatePassword(User userCarrier, String newPassword);

    /**
     * Ritorna la lista di gruppi di un utente
     * @return lista di gruppi a cui l'utente loggato partecipa
     */
    List<Group> getGroupOfLoggedUser();

    User getUser(User userCarrier);

    List<Cashbook> getCashbookOfLoggedUser();
}
