package it.unipv.ingsw.lasout.model;

import java.util.List;

public class Group {

    private int id;
    private User admin;
    private List<User> members;

    public Group(int id) {
        this.id = id;
    }

    public List<User> getMembers() {
        return members;
    }
    public void setMembers(List<User> members) {
        this.members = members;
    }


    public User getAdmin() {return admin;}
    public void setAdmin(User admin) {this.admin = admin;}


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", admin=" + admin +
                ", members=" + members +
                '}';
    }
}
