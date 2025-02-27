package it.unipv.ingsw.lasout.facade.friend;

import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;

public interface IFriendFacade {


    /**
     * Crea una nuova amicizia tra user1 e user2 e viceversa !
     * @param user1 primo user
     * @param user2 secondo user
     * @return true se è riuscito a creare l'amiciza di almeno uno dei due (se l'altro è già presente) false altrimenti
     */
    boolean registerFriend(User user1, User user2);

    /**
     * Toglie amicizia da ambi lati tra user1 e user2
     * @param userone primo user
     * @param usertwo secondo user
     * @return true se è riuscito a rimuovere l'amiciza di almeno uno dei due (se l'altro non era già presente) false altrimenti
     */
    boolean removeFriend(User userone, User usertwo);


    List<User> getFriends(User userone);
}
