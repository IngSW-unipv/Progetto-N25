package it.unipv.ingsw.lasout.facade.cashbook;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.facade.user.ConcreteUserFacade;
import it.unipv.ingsw.lasout.facade.vault.VaultFacade;
import it.unipv.ingsw.lasout.model.cashbook.Cashbook;
import it.unipv.ingsw.lasout.model.cashbook.ICashbookDAO;
import it.unipv.ingsw.lasout.model.transaction.ModifiableTransaction;
import it.unipv.ingsw.lasout.model.transaction.Transaction;
import it.unipv.ingsw.lasout.model.transaction.exception.CannotEditTransactionException;
import it.unipv.ingsw.lasout.util.DaoFactory;
import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class CashbookFacade implements ICashbookFacade {

    private ICashbookDAO cashBookDAO;
    public CashbookFacade() {
        cashBookDAO = DaoFactory.getCashbookDAO();
    }

    private static CashbookFacade instance;
    public static CashbookFacade getInstance() {
        if (instance == null) {
            instance = new CashbookFacade();
        }
        return instance;
    }

    @Override
    public boolean saveCashbook(Cashbook cashbook) {
        try {
            cashBookDAO.save(cashbook);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Cashbook getCashbook(Cashbook carrierCashbook){
        try{
            return cashBookDAO.get(carrierCashbook);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean editCashbook(Cashbook cashbook){
        try{
            cashBookDAO.update(cashbook);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCashbook(Cashbook cashbook){
        try{
            cashBookDAO.delete(cashbook);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Cashbook> getUserCashbooks(User carrierUser){
        try{
            return cashBookDAO.getAllUserCashbooks(carrierUser);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean addTransaction(Cashbook cashbook, Transaction transaction) {
        try{
            Cashbook c = cashBookDAO.get(cashbook);
            c.addTransaction(transaction);
            cashBookDAO.update(c);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Serve a salvare una transazione conoscendo lo user che la ha eseguita
     * @param user
     * @param transaction
     * @return
     */
    public boolean addTransaction(User user, Transaction transaction) {
        Cashbook defaultCashbook = cashBookDAO.getDefaultCashbook(user);
        return addTransaction(defaultCashbook, transaction);
    }

    @Override
    public boolean editTransaction(Cashbook cashbook, Transaction transaction) {
        Cashbook c;
        try{
            c=getCashbook(cashbook);
            c.removeTransaction(transaction);
            cashBookDAO.update(c);
            editCashbook(c);
        } catch (CannotEditTransactionException e) {
            return false;
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean removeTransaction(Cashbook cashbook, Transaction transaction){
        try{
            Cashbook c = getCashbook(cashbook);
            c.removeTransaction((ModifiableTransaction) transaction);
            cashBookDAO.update(c);
            editCashbook(c);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
