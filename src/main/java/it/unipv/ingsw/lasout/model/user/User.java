package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.model.grup.Group;
import it.unipv.ingsw.lasout.model.notify.Notify;

import java.util.ArrayList;
import java.util.List;

public class User {

    private long id;
    private String username;
    private String password;
    private List<Group> groups;
    private List<User> friends;
    private List<Notify> notifies;

    public User() {
        this.groups = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.notifies = new ArrayList<>();
    }

    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;

    }


    public void setId(long id) {this.id = id;}
    public long getId() {return id;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public void setFriends(List<User> friends) {this.friends = friends;}
    public List<User> getFriends() {return friends;}

    public List<Notify> getNotifies() {
        return notifies;
    }

    public void setNotifies(List<Notify> notifies) {
        this.notifies = notifies;
    }
}
