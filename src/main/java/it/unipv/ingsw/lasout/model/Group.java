package it.unipv.ingsw.lasout.model;

import java.util.List;

public class Group {

    private User admin;
    private List<User> members;

    public List<User> getMembers() {
        return members;
    }
    public void setMembers(List<User> members) {
        this.members = members;
    }

    public User getAdmin() {return admin;}
    public void setAdmin(User admin) {this.admin = admin;}

    public void cacca(){

    }

}
