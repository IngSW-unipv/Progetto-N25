package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.notify.Notify;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe Bean di "user" con solo attributi, costruttori, getters e setters
 */
public class User {


    //attributi utente
    private long id;
    private String username;
    private String password;
    private String email;
    private List<Group> groups;
    private List<User> friends;
    private List<Notify> notifies;


    /**
     * COSTRUTTORE
     * @param id
     */
    public User(long id){
        this.id = id;
    }

    /**
     * COSTRUTTORE
     * @param username
     * @param password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @param username
     * @param password
     * @param email
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * COSTRUTTORE
     * @param id
     * @param username
     * @param password
     */
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @param id
     * @param username
     * @param password
     * @param email
     */
    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * COSTRUTTORE
     */
    public User() {
         this.groups = new ArrayList<>();
         this.friends = new ArrayList<>();
         this.notifies = new ArrayList<>();
    }

    /**
     * COSTRUTTORE
     * @param id
     */
    public User(int id) {
        this.id = id;
        this.groups = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.notifies = new ArrayList<>();
    }




    /**
     *getter e setter
     */

    public void setId(long id) {this.id = id;}
    public long getId() {return id;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}


    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

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
                ", email='" + email + '\'' +
                ", groups=" + groups +
                ", friends=" + friends +
                ", notifies=" + notifies +
                '}';
    }
}
