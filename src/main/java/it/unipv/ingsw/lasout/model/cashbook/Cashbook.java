package it.unipv.ingsw.lasout.model.cashbook;

import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.vault.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Cashbook {
    private int id;
    private String name;
    private List<Transaction> transactionList;


    public Cashbook(){
    }

    public Cashbook(int id) {
        this.id = id;
    }

    public Cashbook(int id, String name, List<Transaction> transactionList) {
        this.id = id;
        this.name = name;
        this.transactionList = transactionList;
    }


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
