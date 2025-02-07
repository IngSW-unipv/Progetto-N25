package it.unipv.ingsw.lasout.model.vault;

public class Transaction {
	
	private int id;
	private double amount;
	private String notes;
	
	public Transaction(int id, double amount, String notes) {
		
		this.id = id;
		this.amount = amount;
		this.notes = notes;
	}

	public Transaction() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
