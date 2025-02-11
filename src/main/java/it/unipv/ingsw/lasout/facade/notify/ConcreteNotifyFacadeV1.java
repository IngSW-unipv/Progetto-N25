package it.unipv.ingsw.lasout.facade.notify;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.IGroupDao;
import it.unipv.ingsw.lasout.model.notify.INotifyDAO;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.InviteGroupRequestNotifyAction;
import it.unipv.ingsw.lasout.model.user.IUserDAO;
import it.unipv.ingsw.lasout.model.user.User;

import java.util.Properties;

public class ConcreteNotifyFacadeV1 implements NotifyFacade{

    private INotifyDAO notifyDAO;

    @Override
    public boolean sendNotify(Notify notify) {
        return true;
    }

    @Override
    public boolean sendSystemNotify(User user, String message) {
        return true;
    }

    @Override
    public boolean sendFriendRequest(User from, User to) {
        return true;
    }

    @Override
    public boolean sendInviteGroupRequest(Group from, User to) {

        Notify notify = Notify.Builder.groupRequestNotify()
                .sendTo(to)
                .group(from)
                .build();
        try {
            notifyDAO.save(notify);
        } catch (Exception e) {
            return false;
        }
        return true;

    }


}
