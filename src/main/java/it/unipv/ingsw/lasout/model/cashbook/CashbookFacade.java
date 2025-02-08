package it.unipv.ingsw.lasout.model.cashbook;

public class CashbookFacade {
    private CashbookFacade() {

    }
    private static CashbookFacade instance;
    public static CashbookFacade getInstance() {
        if (instance == null) {
            instance = new CashbookFacade();
        }
        return instance;
    }


    public boolean addTransaction(Transaction transaction) {

    }


}
