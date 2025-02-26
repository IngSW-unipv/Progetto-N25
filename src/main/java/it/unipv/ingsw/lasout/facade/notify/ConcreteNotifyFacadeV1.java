package it.unipv.ingsw.lasout.facade.notify;

import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.notify.INotifyDAO;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.user.User;

public class ConcreteNotifyFacadeV1 implements NotifyFacade{

    private INotifyDAO notifyDAO;

    @Override
    public boolean sendNotify(Notify notify) {
        try {
            notifyDAO.save(notify);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean sendSystemNotify(User user, String message) {
        Notify notify = Notify.Builder.empty()
                .to(user)
                .description(message)
                .build();
        return sendNotify(notify);
    }

    @Override
    public boolean sendFriendRequest(User from, User to) {
        Notify notify = Notify.Builder.friendRequest()
                .sendTo(to)
                .from(from)
                .build();
        return sendNotify(notify);
    }

    @Override
    public boolean sendPayRequestByUser(User from, User to, double value) {
        Notify notify = Notify.Builder.payRequestByUser()
                .sendTo(to)
                .amount(value)
                .from(from)
                .build();
        return sendNotify(notify);
    }

    @Override
    public boolean sendPayRequestByGroup(Group from, User to, double value) {
        Notify notify = Notify.Builder.payRequestByGroup()
                .sendTo(to)
                .amount(value)
                .group(from)
                .build();
        return sendNotify(notify);
    }

    @Override
    public boolean sendInviteGroupRequest(Group from, User to) {

        Notify notify = Notify.Builder.groupRequestNotify()
                .sendTo(to)
                .group(from)
                .build();
        return sendNotify(notify);

    }


}
