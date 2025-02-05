package it.unipv.ingsw.lasout.model.cashbook;

import it.unipv.ingsw.lasout.model.vault.Transaction;

import java.util.LinkedList;

public class Cashbook {
    private String name;
    private LinkedList<Transaction> transactionList;

    public Cashbook(){
    }

    public Cashbook(String name, LinkedList<Transaction> transactionList){
        this.name = name;
        this.transactionList=transactionList;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getTransactionList(){
        return name;
    }

    public void setTransactionList(LinkedList<Transaction> transactionList){
        this.transactionList=transactionList;
    }

}
