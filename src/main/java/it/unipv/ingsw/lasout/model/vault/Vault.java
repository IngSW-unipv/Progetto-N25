package it.unipv.ingsw.lasout.model.vault;

import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;

public class Vault implements IVault{
	
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

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setID(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBalance(double balance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOwner(User owner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IVaultData getIVaultData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIVaultData(IVaultData ivaultData) {
		// TODO Auto-generated method stub
		
	}

}
