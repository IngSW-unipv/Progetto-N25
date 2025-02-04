package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.notify.Notify;

import java.util.ArrayList;
import java.util.List;

//classe Bean di "user" con solo attributi, costruttore, getters e setters
public class User {
    //attributi utente
    private long id;
    private String username;
    private String password;
    private List<Group> groups;
    private List<User> friends;
    private List<Notify> notifies;

    /**
     * Costruttore di un oggetto User.
     *
     * @param groups
     * @param friends
     * @param notifies
     */
     public User() {
         this.groups = new ArrayList<>();
         this.friends = new ArrayList<>();
         this.notifies = new ArrayList<>();
     }

    //getters e setters
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", groups=" + groups +
                ", friends=" + friends +
                ", notifies=" + notifies +
                '}';
    }
}
