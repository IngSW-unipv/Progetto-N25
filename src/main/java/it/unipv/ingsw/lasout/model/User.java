package it.unipv.ingsw.lasout.model;

import it.unipv.ingsw.lasout.model.grup.Group;

import java.util.List;

public class User {

    private long id;
    private List<Group> groups;


    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
