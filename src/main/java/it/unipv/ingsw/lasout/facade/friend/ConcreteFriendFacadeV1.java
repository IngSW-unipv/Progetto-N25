package it.unipv.ingsw.lasout.facade.friend;

import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;

public class ConcreteFriendFacadeV1 implements IFriendFacade {



    @Override
    public boolean registerFriend(User user1, User user2) {
        return false;
    }

    @Override
    public boolean removeFriend(User userone, User usertwo) {
        return false;
    }

    @Override
    public List<User> getFriends(User userone) {
        return List.of();
    }
}
