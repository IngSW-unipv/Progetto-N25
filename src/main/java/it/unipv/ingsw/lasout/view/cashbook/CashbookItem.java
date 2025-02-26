package it.unipv.ingsw.lasout.view.cashbook;

public class CashbookItem {
    private int id;
    private String name;

    public CashbookItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // ComboBox chiama sempre toString() il nome sull'elenco
    @Override
    public String toString() {
        return name;
    }
}
