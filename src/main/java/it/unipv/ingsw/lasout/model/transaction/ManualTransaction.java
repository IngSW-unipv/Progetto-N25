package it.unipv.ingsw.lasout.model.transaction;

import java.util.Locale;

public class ManualTransaction extends ModifiableTransaction {
    public ManualTransaction() {
    }

    public ManualTransaction(double amount, String date, String notes) {
        super(amount, date, notes);
    }

    public ManualTransaction(double amount, String date, String category, String notes) {
        super(amount, date, category, notes);
    }

    public ManualTransaction(int id) {
        super(id);
    }

    public ManualTransaction(int id, double amount) {
        super(id, amount);
    }

    public ManualTransaction(int id, double amount, String date) {
        super(id, amount, date);
    }

    public ManualTransaction(int id, double amount, String date, String category, String notes) {
        super(id, amount, date, category, notes);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "ManualTransaction{id=%d, amount=%.2f, date='%s', category='%s', note='%s'}",
                super.getId(), super.getAmount(), super.getDate(), super.getCategory(), super.getNotes());
    }
}
