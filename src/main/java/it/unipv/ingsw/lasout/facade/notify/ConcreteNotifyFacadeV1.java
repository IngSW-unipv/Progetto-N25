package it.unipv.ingsw.lasout.facade.notify;

import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.notify.INotifyDAO;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;
import it.unipv.ingsw.lasout.util.DaoFactory;
import it.unipv.ingsw.lasout.util.TextUtil;

import java.util.Collection;
import java.util.List;

public class ConcreteNotifyFacadeV1 implements INotifyFacade{

    private INotifyDAO notifyDAO;

    public ConcreteNotifyFacadeV1() {
        notifyDAO = DaoFactory.getNotifyDAO();
    }

    @Override
    public boolean sendNotify(Notify notify) {
        try {
            notifyDAO.save(notify);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
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
        String username = null;
        try {
            if(from.getUsername() == null ) username  =  DaoFactory.getUserDAO().getRaw(from).getUsername();
        } catch (Exception e) {
        }
        Notify notify = Notify.Builder.friendRequest()
                .sendTo(to)
                .from(from)
                .description(username  + " ti ha inviato una richiesta di amicizia !"/*TextUtil.getInstance().format(TextUtil.Text.FRIENDREQUEST, username)*/)
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

    @Override
    public Collection<Notify> getAll(User loggedUser) throws Exception{
        return notifyDAO.notifiesOf(loggedUser);
    }


}
