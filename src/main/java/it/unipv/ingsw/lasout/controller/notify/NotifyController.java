package it.unipv.ingsw.lasout.controller.notify;

import it.unipv.ingsw.lasout.controller.Loadable;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.facade.friend.IFriendFacade;
import it.unipv.ingsw.lasout.facade.group.GroupFacade;
import it.unipv.ingsw.lasout.facade.notify.INotifyFacade;
import it.unipv.ingsw.lasout.facade.user.ISessionFacade;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.friend.FriendRequestNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.group.InviteGroupRequestNotifyAction;
import it.unipv.ingsw.lasout.view.notify.NotifyPanel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class NotifyController {

    private static GroupFacade groupFacade;
    private static INotifyFacade notifyFacade;
    private static ISessionFacade sessionFacade;
    private static IFriendFacade friendFacade;

    private Set<Loadable> loadables = new HashSet<>();

    private NotifyPanel notifyPanel;

    public NotifyController() {

        groupFacade  =  LaVaultFacade.getInstance().getGroupFacade();
        notifyFacade = LaVaultFacade.getInstance().getNotifyFacade();
        sessionFacade  = LaVaultFacade.getInstance().getSessionFacade();
        friendFacade = LaVaultFacade.getInstance().getFriendFacade();

    }

    public void update(){
        loadables.forEach(Loadable::reload);
    }

    public void load(){
        getNotifyPanel().reload();
    }

    public void subscribe(Loadable updatable) {
        loadables.add(updatable);
    }

    public Collection<Notify> getAll() throws Exception {
        return notifyFacade.getAll(sessionFacade.getLoggedUser());
    }

    //public void setNotifyPanel(NotifyPanel notifyPanel) {
    //    this.notifyPanel = notifyPanel;
    //}

    public void acceptFriendRequest(FriendRequestNotifyAction notify) {

        friendFacade.registerFriend(notify.getTo(), notify.getFrom());

    }

    public void deleteNotify(Notify notify) {
        notifyFacade.delete(notify);
        update();
    }

    public void acceptGroupInvite(InviteGroupRequestNotifyAction notifyAction) {

        groupFacade.addUserToGroup(notifyAction.getGroup(), notifyAction.getUser());
        update();

    }

    public NotifyPanel getNotifyPanel() {
        return notifyPanel;
    }

    public void setNotifyPanel(NotifyPanel notifyPanel) {
        this.notifyPanel = notifyPanel;
    }
}
