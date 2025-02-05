package it.unipv.ingsw.lasout.model.cashbook;

import java.util.LinkedList;

public class Cashbook {
    LinkedList<Integer> transactionList;


    public void addTransaction(int amount){
        transactionList.add(amount);
    }

    public void removeTransaction(int amount){
        transactionList.remove(amount);
    }

    @Override
    public String toString(){
        return "Test";
    }
}
