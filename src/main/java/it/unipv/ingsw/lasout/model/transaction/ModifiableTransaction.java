package it.unipv.ingsw.lasout.model.transaction;

public abstract class ModifiableTransaction extends Transaction {
    public ModifiableTransaction() {
    }

    public ModifiableTransaction(int id) {
        super(id);
    }

    public ModifiableTransaction(int id, double amount, String date) {
        super(id, amount, date);
    }

    public ModifiableTransaction(int id, double amount, String date, String category, String notes) {
        super(id, amount, date, category, notes);
    }

    public ModifiableTransaction(int id, double amount) {
        super(id, amount);
    }

    public ModifiableTransaction(double amount, String date, String notes) {
        super(amount, date, notes);
    }

    public ModifiableTransaction(double amount, String date, String category, String notes) {
        super(amount, date, category, notes);
    }
}
