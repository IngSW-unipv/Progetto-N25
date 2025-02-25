package it.unipv.ingsw.lasout.facade.cashbook.filters;

import it.unipv.ingsw.lasout.model.transaction.Transaction;

public class ExpenseGreaterThanFilterCashbookStrategy implements ICashbookFilterStrategy {

    private double importo;

    @Override
    public boolean filter(Transaction transaction) {
        return transaction.getAmount() >= importo;
    }
}