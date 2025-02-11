package it.unipv.ingsw.lasout.model.cashbook;

import it.unipv.ingsw.lasout.model.transaction.ModifiableTransaction;
import it.unipv.ingsw.lasout.model.transaction.Transaction;
import it.unipv.ingsw.lasout.model.transaction.exception.CannotEditTransactionException;

import java.util.List;

public class Cashbook {
    private int id;
    private int userId;
    private String name;
    private List<Transaction> transactionList;


    public Cashbook(){
    }

    public Cashbook(int id) {
        this.id = id;
    }

    public Cashbook(int id, int userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public Cashbook(int id, int userId, String name, List<Transaction> transactionList) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.transactionList = transactionList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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



    @Override
    public String toString() {
        return String.format("Cashbook{id=%d, name='%s'}", id, name);
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
