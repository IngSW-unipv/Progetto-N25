package it.unipv.ingsw.lasout.facade.group;

import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.spesa.Spesa;
import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;

public interface GroupFacade {


    /**
     * Permette di creare un nuovo gruppo
     *
     * @param group pojo del gruppo che si vuole creare
     * @return conferma di successo
     */
    boolean newGroup(Group group);

    /**
     * Restituisce un group pojo con i suoi attributi
     *
     * @param group carry con l'id del gruppo ricercato
     * @return Group pojo contente le info del gruppo
     */
    Group getGroup(Group group);

    /**
     * permette di editare i dati di un gruppo sul db
     *
     * @param group pojo del con le informazioni del gurppo che si vuole modificare l'id indica i dati che andranno riscritti
     * @return conferma di successo
     */
    boolean editGroup(Group group);

    /**
     * Permette di eliminare un gruppo dal db
     *
     * @param group carry contentente l'id del gruppo da eliminare
     * @return conferma di successo
     */
    boolean deleteGroup(Group group);

    /**
     * Elimina una lista di utenti da un gruppo
     *
     * @param group    carry con l'id del gruppo da cui si vogliono eliminare gli user
     * @param userList Lista di Carry con l'id delle persone che si vogliono eliminare
     * @return conferma di successo
     */
    boolean removeUserFromGroup(Group group, List<User> userList);

    /**
     * Elimina un utente da un gruppo
     *
     * @param group carry con l'id del gruppo da cui si vuole cancellare
     * @param user  carry con l'id che si vuole rimuovere
     * @return conferma di successo
     */
    boolean removeUserFromGroup(Group group, User user);

    /**
     * Aggiungi un user al gruppo
     *
     * @param group carry con l'id del gruppo da cui si vuole aggiungere gli user
     * @param user
     * @return confemra di successo
     */
    boolean addUserToGroup(Group group, User user);

    /**
     * leaveGroup permette ad un utente di lasciare un gruppo
     *
     * @param group carry del gruppo che si vuole lasciare
     * @param user  carry con l'id del utente che vuole lasciare il gruppo
     * @return conferma di successo
     */
    boolean leaveGroup(Group group, User user);

    /**
     * permette di lasciare un gruppo del utente della sessione
     *
     * @param group carry con l'id del gruppo che si vuole lasciare
     * @return confemra di successo
     */
    boolean leaveGroup(Group group);

    /**
     * Permette di aggiungere una spesa di gruppo alla lista delle sepse di un gruppo
     *
     * @param group carry con l'id del gruppo a cui si vuole aggiungere la spesa
     * @param spesa pojo della sepsa che si vuole aggiungiere
     * @return conferma di successo
     */
    boolean addSpesaToGroup(Group group, Spesa spesa);

    /**
     * Permette di rimuovere una spesa di gruppo  della lista delle spese di gruppo
     *
     * @param group carry con l'id del gruppo di cui si vuole rimuovere la spesa
     * @param spesa carry con l'id della sepsa che si vuole eliminare
     * @return conferma di successo
     */
    boolean remuveSpesaFromGroup(Group group, Spesa spesa);

    /**
     * Restituisce la lista di spese di un gruppo limite 100
     *
     * @param group carry con l'id del gruppo di cui si vuole la lista
     * @return lista delle spese
     */
    List<Spesa> getSpeseFromGroup(Group group);

}
