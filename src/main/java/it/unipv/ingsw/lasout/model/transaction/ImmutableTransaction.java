package it.unipv.ingsw.lasout.model.transaction;

public abstract class ImmutableTransaction extends Transaction{
    public ImmutableTransaction() {
    }

    public ImmutableTransaction(int id) {
        super(id);
    }

    public ImmutableTransaction(int id, double amount) {
        super(id, amount);
    }

    public ImmutableTransaction(int id, double amount, String date) {
        super(id, amount, date);
    }

    public ImmutableTransaction(int id, double amount, String date, String category, String notes) {
        super(id, amount, date, category, notes);
    }

}
