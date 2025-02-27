package it.unipv.ingsw.lasout.model.notify.action.group;

import it.unipv.ingsw.lasout.controller.notify.NotifyController;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.user.User;

import javax.swing.*;

public class PayRequestByGroupNotifyAction implements INotifyAction {

    private Group group;
    private User user;
    private double amount;

    @Override
    public void build(NotifyController notifyController, JPanel buttonPanel) {

    }

    @Override
    public String type() {
        return "notifypayrequestbygroup";
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static class PayRequestByGroupBuilder extends Notify.Builder {

        private Group group;
        private double value;

        public PayRequestByGroupBuilder() {
            this.action = new PayRequestByGroupNotifyAction();
        }

        public PayRequestByGroupNotifyAction.PayRequestByGroupBuilder sendTo(User user){
            this.to(user);
            return this;
        }

        public PayRequestByGroupNotifyAction.PayRequestByGroupBuilder group(Group group){
            this.group = group;
            return this;
        }

        public  PayRequestByGroupNotifyAction.PayRequestByGroupBuilder amount(double value){
            this.value = value;
            return this;
        }

        public Notify build(){
            Notify notify = super.build();
            PayRequestByGroupNotifyAction action = (PayRequestByGroupNotifyAction) this.action;
            action.setGroup(group);
            action.setUser(sendTo);
            action.setAmount(value);

            notify.setUser(sendTo);
            return notify;
        }


    }
}
