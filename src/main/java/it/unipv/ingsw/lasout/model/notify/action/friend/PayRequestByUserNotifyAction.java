package it.unipv.ingsw.lasout.model.notify.action.friend;

import it.unipv.ingsw.lasout.controller.notify.ButtonNotifyAction;
import it.unipv.ingsw.lasout.controller.notify.NotifyController;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.persistence.INotifyActionPersistence;
import it.unipv.ingsw.lasout.model.user.User;

import javax.swing.*;

public class PayRequestByUserNotifyAction implements INotifyAction {

    private User from;
    private User user;
    private double amount;

    @Override
    public void build(NotifyController notifyController, JPanel buttonPanel) {
        JButton accept = new JButton("Paga");
        JButton refuse = new JButton("Rifiuta");



        accept.addActionListener((e)->{
            ButtonNotifyAction notifyAction = (ButtonNotifyAction) e;
            PayRequestByUserNotifyAction request = (PayRequestByUserNotifyAction) notifyAction.getNotify().getNotifyAction();
            notifyController.acceptPayRequestByUser(request);
            notifyController.deleteNotify(notifyAction.getNotify());
            JOptionPane.showMessageDialog(buttonPanel, request.amount + "€ sono stati scalati dal tuo  saldo");
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
        return "payrequestbyuser";
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
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


    public static class PayRequestByUserBuilder extends Notify.Builder {
        private User from;
        private double value;

        public PayRequestByUserBuilder() {
            this.action = new PayRequestByUserNotifyAction();
        }

        public PayRequestByUserNotifyAction.PayRequestByUserBuilder sendTo(User user){
            this.to(user);
            return this;
        }

        public PayRequestByUserNotifyAction.PayRequestByUserBuilder from(User user){
            this.from = user;
            return this;
        }

        public  PayRequestByUserNotifyAction.PayRequestByUserBuilder amount(double value){
            this.value = value;
            return this;
        }

        public Notify build(){
            Notify notify = super.build();
            PayRequestByUserNotifyAction action = (PayRequestByUserNotifyAction) this.action;
            action.setFrom(from);
            action.setUser(sendTo);
            action.setAmount(value);

            notify.setUser(sendTo);
            return notify;
        }


    }
}
