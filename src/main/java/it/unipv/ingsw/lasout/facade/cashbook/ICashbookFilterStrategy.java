package it.unipv.ingsw.lasout.facade.cashbook;

import it.unipv.ingsw.lasout.model.transaction.Transaction;

public interface ICashbookFilterStrategy {

    public boolean filter(Transaction transaction);

}
