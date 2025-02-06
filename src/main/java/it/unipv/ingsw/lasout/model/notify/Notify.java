package it.unipv.ingsw.lasout.model.notify;

import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.user.User;

public class Notify {


    private int id;
    private String description;
    private User user;

    //Uso una HAS-A perchè mi è più versalite di una generalizzazione, non fa parte strettamente del dominio
    private INotifyAction notifyAction;

    public Notify(int id, User user) {
        this.id = id;
        this.user = user;
    }


    public long getUserID(){
        return user.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
