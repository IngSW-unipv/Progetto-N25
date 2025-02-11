package it.unipv.ingsw.lasout.model.notify.action;

import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.user.User;
import javafx.scene.layout.HBox;

public class InviteGroupRequestNotifyAction implements INotifyAction{

    private Group group;
    private User user;

    @Override
    public void build(HBox information, HBox footer) {

    }

    @Override
    public void load(Notify notify) throws Exception {

    }

    @Override
    public void delete(Notify notify) throws Exception {

    }

    @Override
    public void save(Notify notify) throws Exception {

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
}
