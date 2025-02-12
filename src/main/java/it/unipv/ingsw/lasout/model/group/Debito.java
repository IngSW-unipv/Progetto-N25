package it.unipv.ingsw.lasout.model.group;

import it.unipv.ingsw.lasout.model.user.User;

public class Debito {
    private User creditore;
    private User debitore;
    private double debito;

    public Debito(User creditore, User debitore, double debito) {
        this.creditore = creditore;
        this.debitore = debitore;
        this.debito = debito;
    }

    public void addDebito(double add) {
        debito += add;
    }

    public User getCreditore() {
        return creditore;
    }

    public void setCreditore(User creditore) {
        this.creditore = creditore;
    }

    public User getDebitore() {
        return debitore;
    }

    public void setDebitore(User debitore) {
        this.debitore = debitore;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }
}
