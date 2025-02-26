package it.unipv.ingsw.lasout.model.notify;

import it.unipv.ingsw.lasout.model.notify.action.EmptyNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.friend.FriendRequestNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.friend.PayRequestByUserNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.group.InviteGroupRequestNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.group.PayRequestByGroupNotifyAction;
import it.unipv.ingsw.lasout.model.user.User;

public class Notify {


    private Long id;
    private String description;
    private User user;

    //Uso una HAS-A perchè mi è più versalite di una generalizzazione, non fa parte strettamente del dominio
    private INotifyAction notifyAction;

    public Notify() {
    }


    public Notify(Long id) {
        this.id = id;
        this.notifyAction = new EmptyNotifyAction();
    }
    public Notify(Long id, String description) {
        this(id);
        this.id = id;
        this.description = description;
    }


    public long getUserID(){
        return user.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public INotifyAction getNotifyAction() {
        return notifyAction;
    }

    public void setNotifyAction(INotifyAction notifyAction) {
        this.notifyAction = notifyAction;
    }

    @Override
    public String toString() {
        return "Notify{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", notifyAction=" + notifyAction +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNotifyType() {
        return notifyAction.type();
    }


    public static class Builder {

        protected User sendTo;
        protected INotifyAction action;
        protected String description;
        protected Long id;

        public static Builder empty(){
            return new Builder();
        }

        public static InviteGroupRequestNotifyAction.GroupBuilder groupRequestNotify(){
            return new InviteGroupRequestNotifyAction.GroupBuilder();
        }

        public static PayRequestByGroupNotifyAction.PayRequestByGroupBuilder payRequestByGroup() {
            return new PayRequestByGroupNotifyAction.PayRequestByGroupBuilder();
        }

        public static PayRequestByUserNotifyAction.PayRequestByUserBuilder payRequestByUser() {
            return new PayRequestByUserNotifyAction.PayRequestByUserBuilder();
        }

        public static FriendRequestNotifyAction.FriendRequestBuilder friendRequest() {
            return new FriendRequestNotifyAction.FriendRequestBuilder();
        }

        public Builder to(User  user){
            this.sendTo = user;
            return this;
        }

        public Builder  description(String description){
            this.description = description;
            return this;
        }

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Notify build(){
            Notify notify = new Notify();
            notify.setUser(sendTo);
            notify.setDescription(description);
            notify.setId(id);
            if(this.action != null){
                notify.setNotifyAction(action);
            }
            return notify;
        }



    }
}
