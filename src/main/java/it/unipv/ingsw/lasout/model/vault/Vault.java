package it.unipv.ingsw.lasout.model.vault;

import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;


public class Vault {
	
	private long id;
	private User user;
	private double saldo;
	private List<PaymentMethod> methods;

	public Vault(long id, User user, double saldo, List<PaymentMethod> methods) {
		this.id = id;
		this.user = user;
		this.saldo = saldo;
		this.methods = methods;
	}

	public Vault(long id) {
		this.id = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public List<PaymentMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<PaymentMethod> methods) {
		this.methods = methods;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
    public String toString() {
        return "Vault{" +
                "id=" + id +
                ", utente=" + user +
                ", Metodi di pagamento=" + methods +
                '}';
    }

}
