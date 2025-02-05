package it.unipv.ingsw.lasout.model.cashbook;

import java.util.LinkedList;

public class Cashbook {
    private String name;
    private LinkedList<Movement> transactionList;

    public Cashbook(){
    }

    public Cashbook(String name, LinkedList<Movement> transactionList){
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

    public void setTransactionList(LinkedList<Movement> transactionList){
        this.transactionList=transactionList;
    }

}
