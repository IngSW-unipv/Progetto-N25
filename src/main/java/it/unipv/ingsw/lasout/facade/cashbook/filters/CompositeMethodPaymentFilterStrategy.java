package it.unipv.ingsw.lasout.facade.cashbook.filters;

import it.unipv.ingsw.lasout.facade.cashbook.ICashbookFilterStrategy;
import it.unipv.ingsw.lasout.model.transaction.Transaction;

import java.util.List;

public class CompositeMethodPaymentFilterStrategy implements ICashbookFilterStrategy {

    private List<ICashbookFilterStrategy> filterCashbookStrategies;


    @Override
    public boolean filter(Transaction transaction) {
        for (ICashbookFilterStrategy filterCashbookStrategy : filterCashbookStrategies) {

            if(!filterCashbookStrategy.filter(transaction)) return false;

        }
        return true;
    }
}