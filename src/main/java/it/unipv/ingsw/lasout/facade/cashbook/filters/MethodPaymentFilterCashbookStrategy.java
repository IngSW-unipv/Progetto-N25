package it.unipv.ingsw.lasout.facade.cashbook;

import it.unipv.ingsw.lasout.model.transaction.Transaction;

public class MethodPaymentFilterCashbookStrategy implements ICashbookFilterStrategy{

    private String methodPaymentFilter;

    @Override
    public boolean filter(Transaction transaction) {
        //return transaction.getMethodPaymentType().equals(methodPaymentFilter);
        return false;
    }
}