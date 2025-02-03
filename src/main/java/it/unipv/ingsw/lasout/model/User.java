package it.unipv.ingsw.lasout.model;

import java.util.List;

public class User {

    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
