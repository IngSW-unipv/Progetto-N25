package it.unipv.ingsw.lasout.model.vault;

import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.PaymentMethod;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;

import java.util.List;

public class Vault implements IVault{
	
	private IVault vault;
	private int id;
	private User user;
	private VirtualVault vv_id;
	private double saldo;
	private List<PaymentMethod> methods;

	public Vault(IVault vault, int id, User user, double saldo, List<PaymentMethod> methods) {
		this.vault = vault;
		this.id = id;
		this.user = user;
		this.saldo = saldo;
		this.methods = methods;
	}

	public Vault() {
	}
	
	public Vault(int id) {
		this.id = id;
	}

	public Vault(IVault vault) {
        this.vault = vault;
        vault.setIVaultData(new VaultData());
    }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	public IVault getVault() {
		return vault;
	}

	public void setVault(IVault vault) {
		this.vault = vault;
	}

	public VirtualVault getVv_id() {
		return vv_id;
	}

	public void setVv_id(VirtualVault vv_id) {
		this.vv_id = vv_id;
	}

	@Override
    public String toString() {
        return "Vault{" +
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
		return user;
	}

	@Override
	public void setOwner(User owner) {
		this.user = owner;		
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
