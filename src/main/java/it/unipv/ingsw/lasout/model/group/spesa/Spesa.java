package it.unipv.ingsw.lasout.model.group.spesa;

import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.user.User;

public class Spesa {

    private int id;
    private String note;
    private double amount;
    private User esecutore;
    private Group group;

    public Spesa(int id, User esecutore, Group group, double amount, String note) {
        this.id = id;
        this.note = note;
        this.amount = amount;
        this.esecutore = esecutore;
        this.group = group;
    }

    public Spesa(User esecutore, Group group, double amount, String note) {
        this.esecutore = esecutore;
        this.group = group;
        this.note = note;
        this.amount = amount;
    }

    public Spesa() {
    }

    public Spesa(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getEsecutore() {
        return esecutore;
    }

    public void setEsecutore(User esecutore) {
        this.esecutore = esecutore;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Spesa{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", amount=" + amount +
                ", esecutore=" + esecutore +
                ", group=" + group +
                '}';
    }
}
