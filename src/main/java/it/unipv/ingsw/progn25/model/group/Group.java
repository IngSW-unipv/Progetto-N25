package it.unipv.ingsw.progn25.model.group;

import it.unipv.ingsw.progn25.model.User;

import java.util.ArrayList;

public class Group {

    private ArrayList<User> participants;
    private User admin;

    public Group(ArrayList<User> participants) {
        admin=participants.get(0);
        this.participants=participants;
    }

    public boolean addParticipant(User performer, User participant) {
        if(admin==performer) return participants.add(participant);
        return false;
    }

    public boolean removeParticipant(User performer, User participant) {
        if(admin==performer) return participants.remove(participant);
        return false;
    }

}
