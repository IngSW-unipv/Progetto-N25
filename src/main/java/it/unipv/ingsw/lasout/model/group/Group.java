package it.unipv.ingsw.lasout.model.group;

import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;

public class Group {

    private long id;
    private String name;
    private User admin;
    private List<User> members;
    private List<Spesa> spese;

    public Group(long id) {
        this.id = id;
    }
    public Group(){}
    public Group(String name, User Admin, List<User> members){
        this.name = name;
        this.admin = Admin;
        this.members = members;
    }
    public Group(long id, String name, User Admin, List<User> members){
        this.id = id;
        this.name = name;
        this.admin = Admin;
        this.members = members;
    }


    public List<User> getMembers() {
        return members;
    }
    public void setMembers(List<User> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Spesa> getSpese() {
        return spese;
    }

    public void setSpese(List<Spesa> spese) {
        this.spese = spese;
    }

    public User getAdmin() {return admin;}
    public void setAdmin(User admin) {this.admin = admin;}

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", admin=" + admin +
                ", members=" + members +
                ", spese=" + spese +
                '}';
    }
}
