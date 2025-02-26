package it.unipv.ingsw.lasout.model.cashbook;

import it.unipv.ingsw.lasout.model.transaction.ModifiableTransaction;
import it.unipv.ingsw.lasout.model.transaction.Transaction;
import it.unipv.ingsw.lasout.model.transaction.exception.CannotEditTransactionException;
import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;

public class Cashbook {
    private int id;
    private User user;
    private String name;
    private List<Transaction> transactionList;
    private boolean isDefault;

    public Cashbook(){
    }

    public Cashbook(int id) {
        this.id = id;
    }

    public Cashbook(int id, User user, String name, boolean isDefault) {
        this.id = id;
        this.user = user;
        this.name = name;
    }

    public Cashbook(int id, User user, String name, boolean isDefault, List<Transaction> transactionList) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.transactionList = transactionList;
    }

    public Cashbook(User user, String name, boolean isDefault) {
        this.user = user;
        this.name = name;
        this.isDefault = isDefault;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public boolean isDefault(){
        return isDefault;
    }

    public void setIsDefault(boolean isDefault){
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return String.format("Cashbook{id=%d, name='%s', isDefault='%s'}", id, name, isDefault);
    }


    public void removeTransaction(Transaction transaction) throws CannotEditTransactionException {
        try{
            transactionList.remove((ModifiableTransaction)transaction);
        } catch (Exception e) {
            throw new CannotEditTransactionException();
        }
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

}
