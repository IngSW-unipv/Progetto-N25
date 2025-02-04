package it.unipv.ingsw.lasout.model.grup;

import it.unipv.ingsw.lasout.model.User;
import it.unipv.ingsw.lasout.model.grup.exception.NotAdminException;

import java.util.List;

public class GroupBean {

    private List<User> members;
    private User admin;

    public GroupBean(List <User> members) {
        this.members = members;
        admin=members.get(0);
    }

    public boolean addMember(User performer, User user) throws NotAdminException {
        if(admin.equals(performer)) return members.add(user);
        else throw new NotAdminException("performer are not admin");
    }

    public boolean removeMember(User performer, User user) throws NotAdminException {
        if(admin.equals(performer)) return members.remove(user);
        else throw new NotAdminException("performer are not admin");
    }

    public boolean leaveGroup(User performer) throws NotAdminException {
        if(admin.equals(performer)) return members.remove(performer);
        else throw new NotAdminException("performer are not admin");
    }

    public boolean changeAdmin(User performer, User newAdmin){
        if(admin.equals(performer))if(members.contains(newAdmin)){
            admin=newAdmin;
            return true;
        }
        return false;
    }

}
