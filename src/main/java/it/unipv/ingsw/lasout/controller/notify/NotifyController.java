package it.unipv.ingsw.lasout.controller.notify;

import it.unipv.ingsw.lasout.controller.Loadable;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.facade.group.GroupFacade;
import it.unipv.ingsw.lasout.facade.notify.INotifyFacade;
import it.unipv.ingsw.lasout.facade.user.ISessionFacade;
import it.unipv.ingsw.lasout.facade.user.IUserFacade;
import it.unipv.ingsw.lasout.facade.vault.VaultFacade;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.friend.FriendRequestNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.friend.PayRequestByUserNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.group.InviteGroupRequestNotifyAction;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.view.notify.NotifyPanel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class NotifyController {

    private GroupFacade groupFacade;
    private VaultFacade vaultFacade;
    private INotifyFacade notifyFacade;
    private ISessionFacade sessionFacade;
    private IUserFacade userFacade;

    private Set<Loadable> loadables = new HashSet<>();

    private NotifyPanel notifyPanel;

    public NotifyController() {

        userFacade =  LaVaultFacade.getInstance().getUserFacade();
        groupFacade  =  LaVaultFacade.getInstance().getGroupFacade();
        notifyFacade = LaVaultFacade.getInstance().getNotifyFacade();
        sessionFacade  = LaVaultFacade.getInstance().getSessionFacade();
        vaultFacade  =  LaVaultFacade.getInstance().getVaultFacade();

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

        User from =  notify.getFrom();
        User to =  notify.getTo();


        userFacade.makeFriendShipOneWay(from, to);
        userFacade.makeFriendShipOneWay(to, from);

        from.getFriends().add(to);
        to.getFriends().add(from);

        update();

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

    public void acceptPayRequestByUser(PayRequestByUserNotifyAction notifyAction) {

        vaultFacade.ritiroVault(notifyAction.getUser(),  notifyAction.getAmount());
        vaultFacade.depositoVault(notifyAction.getFrom(),  notifyAction.getAmount());
        update();

    }
}
