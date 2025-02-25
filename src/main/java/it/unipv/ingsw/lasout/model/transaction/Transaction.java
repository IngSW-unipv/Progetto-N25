package it.unipv.ingsw.lasout.model.transaction;

import java.util.Locale;

public abstract class Transaction {
	
	protected int id;
	protected double amount;
	protected String date;
	protected String category;
	protected String notes;

	public Transaction() {

	}

	/**
	 * Costruttore apposito per le query
	 */
	public Transaction(int id) {
		this.id = id;
	}

	public Transaction(int id, double amount) {
		this.id = id;
		this.amount = amount;
	}

	/**
	 * Costruttore per test veloci
	 */
	public Transaction(int id, double amount, String date) {
		this.id = id;
		this.amount = amount;
		this.date = date;
	}

	/**
	 * Costruttore completo
	 */
	public Transaction(int id, double amount, String date, String category, String notes) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.category = category;
		this.notes = notes;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if (getClass() != obj.getClass()) return false;
		Transaction t = (Transaction) obj;
		return 	this.id == t.id &&
				this.amount == t.amount &&
				this.date.equals(t.date) &&
				this.category.equals(t.category) &&
				this.notes.equals(t.notes);
	}

}
