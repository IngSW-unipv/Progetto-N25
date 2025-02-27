package it.unipv.ingsw.lasout.model.notify.action.group;

import it.unipv.ingsw.lasout.controller.notify.NotifyController;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.user.User;

import javax.swing.*;

public class InviteGroupRequestNotifyAction implements INotifyAction {

    private Group group;
    private User user;

    @Override
    public void build(NotifyController notifyController, JPanel buttonPanel) {

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

    public static class GroupBuilder extends Notify.Builder {

        private Group group;

        public GroupBuilder() {
            this.action = new InviteGroupRequestNotifyAction();
        }

        public GroupBuilder sendTo(User user){
            this.to(user);
            return this;
        }

        public GroupBuilder group(Group group){
            this.group = group;
            return this;
        }

        public Notify build(){
            Notify notify = super.build();
            InviteGroupRequestNotifyAction action = (InviteGroupRequestNotifyAction) this.action;
            action.setGroup(group);
            action.setUser(sendTo);

            notify.setUser(sendTo);

            return notify;
        }


    }
}
