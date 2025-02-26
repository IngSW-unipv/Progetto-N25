package it.unipv.ingsw.lasout.view.group;

public class GroupItem {
    private final int id;
    private final String name;

    public GroupItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // toString decide come viene visualizzato l'oggetto nella comboBox
    @Override
    public String toString() {
        return name;

    }
}