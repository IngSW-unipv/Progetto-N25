package it.unipv.ingsw.lasout.model.transaction;

import java.util.Locale;

public class AutomaticTransaction extends ImmutableTransaction {
    public AutomaticTransaction() {
    }

    public AutomaticTransaction(int id) {
        super(id);
    }

    public AutomaticTransaction(int id, double amount) {
        super(id, amount);
    }

    public AutomaticTransaction(int id, double amount, String date) {
        super(id, amount, date);
    }

    public AutomaticTransaction(int id, double amount, String date, String category, String notes) {
        super(id, amount, date, category, notes);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "AutomaticTransaction{id=%d, amount=%.2f, date='%s', category='%s', note='%s'}",
                super.getId(), super.getAmount(), super.getDate(), super.getCategory(), super.getNotes());
    }
}
