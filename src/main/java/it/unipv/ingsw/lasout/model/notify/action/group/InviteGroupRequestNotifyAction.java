package it.unipv.ingsw.lasout.model.notify.action.group;

import it.unipv.ingsw.lasout.controller.notify.ButtonNotifyAction;
import it.unipv.ingsw.lasout.controller.notify.NotifyController;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.friend.FriendRequestNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.persistence.INotifyActionPersistence;
import it.unipv.ingsw.lasout.model.user.User;

import javax.swing.*;

public class InviteGroupRequestNotifyAction implements INotifyAction {

    private Group group;
    private User user;
    private INotifyActionPersistence persistence;

    @Override
    public void build(NotifyController notifyController, JPanel buttonPanel) {

        JButton accept = new JButton("Accetta");
        JButton refuse = new JButton("Rifiuta");



        accept.addActionListener((e)->{
            ButtonNotifyAction notifyAction = (ButtonNotifyAction) e;
            notifyController.acceptGroupInvite((InviteGroupRequestNotifyAction) notifyAction.getNotify().getNotifyAction());
            notifyController.deleteNotify(notifyAction.getNotify());
        });

        refuse.addActionListener((e)->{
            ButtonNotifyAction notifyAction = (ButtonNotifyAction) e;
            notifyController.deleteNotify(notifyAction.getNotify());
        });

        buttonPanel.add(accept);
        buttonPanel.add(refuse);


    }


    @Override
    public String type() {
        return "groupinvite";
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
