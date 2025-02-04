package it.unipv.ingsw.lasout.model.group;

import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;

public class Group {



    private long id;
    private User admin;
    private List<User> members;

    public Group(long id) {
        this.id = id;
    }
    public Group(){}


    public List<User> getMembers() {
        return members;
    }
    public void setMembers(List<User> members) {
        this.members = members;
    }


    public User getAdmin() {return admin;}
    public void setAdmin(User admin) {this.admin = admin;}


    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", admin=" + admin +
                ", members=" + members +
                '}';
    }
}
