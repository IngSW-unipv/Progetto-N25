package it.unipv.ingsw.lasout.model.notify.action.mysql.group;

import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.user.User;

public class InviteGroupRequestNotifyAction implements INotifyAction {

    private Group group;
    private User user;

    @Override
    public void build() {

    }

    @Override
    public Notify getNotify() {
        return null;
    }

    @Override
    public void setNotify(Notify notify) {

    }

    @Override
    public String type() {
        return "";
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "InviteGroupRequestNotifyAction{" +
                "group=" + group +
                ", user=" + user +
                '}';
    }
}
