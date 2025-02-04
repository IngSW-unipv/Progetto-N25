package it.unipv.ingsw.lasout.model.vault;

public class Vault {
	private double saldo;

	public Vault(double saldo) {
		this.saldo = saldo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}
