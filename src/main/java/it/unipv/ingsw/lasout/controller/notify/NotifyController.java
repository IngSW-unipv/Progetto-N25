package it.unipv.ingsw.lasout.controller.notify;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.facade.friend.IFriendFacade;
import it.unipv.ingsw.lasout.facade.notify.INotifyFacade;
import it.unipv.ingsw.lasout.facade.user.ISessionFacade;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.notify.INotifyDAO;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.friend.FriendRequestNotifyAction;
import it.unipv.ingsw.lasout.util.DaoFactory;
import it.unipv.ingsw.lasout.view.group.GroupItem;
import it.unipv.ingsw.lasout.view.notify.NotifyPanel;

import java.util.Collection;
import java.util.List;

public class NotifyController {

    private static INotifyFacade notifyFacade;
    private static ISessionFacade sessionFacade;
    private static IFriendFacade friendFacade;
    private static NotifyPanel notifyPanel;


    public NotifyController() {


        notifyFacade = LaVaultFacade.getInstance().getNotifyFacade();
        sessionFacade  = LaVaultFacade.getInstance().getSessionFacade();
        friendFacade = LaVaultFacade.getInstance().getFriendFacade();

    }

    public static void load() {

        setUpJComboBox();
    }
    public static void setUpJComboBox(){
        notifyPanel.updateView();
    }

    public void update(){
        notifyPanel.updateView();
    }

    public void delete(Notify notify){

    }


    public Collection<Notify> getAll() throws Exception {
        return notifyFacade.getAll(sessionFacade.getLoggedUser());
    }

    public void setNotifyPanel(NotifyPanel notifyPanel) {
        this.notifyPanel = notifyPanel;
    }

    public void acceptFriendRequest(FriendRequestNotifyAction notify) {

        friendFacade.registerFriend(notify.getTo(), notify.getFrom());

    }
}
