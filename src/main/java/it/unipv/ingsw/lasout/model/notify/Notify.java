package it.unipv.ingsw.lasout.model.notify;

public class Notify {


    private int id;
    private String description;

    //Non fa parte del dominio
    private NotifyAction notifyAction;

    public Notify(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
