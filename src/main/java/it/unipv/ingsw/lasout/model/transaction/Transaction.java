package it.unipv.ingsw.lasout.model.transaction;

import java.util.Locale;
import java.util.Objects;

public class Transaction {
	
	private int id;
	private int type;
	private double amount;
	private String date;
	private String category;
	private String notes;

	public Transaction() {

	}

	/**
	 * Costruttore apposito per le query
	 */
	public Transaction(int id) {
		this.id = id;
	}

	/**
	 * Costruttore per test veloci
	 */
	public Transaction(int id, int type, double amount) {
		this.id = id;
		this.type = type;
		this.amount = amount;
	}

	/**
	 * Costruttore completo
	 */
	public Transaction(int id, int type, double amount, String date, String category, String notes) {
		this.id = id;
		this.type = type;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
	public String toString() {
		return String.format(Locale.US, "Transaction{id=%d, type=%d, amount=%.2f, date='%s', category='%s', note='%s'}",
								id, type, amount, date, category, notes);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if (getClass() != obj.getClass()) return false;
		Transaction t = (Transaction) obj;
		return 	this.id == t.id &&
				this.type == t.type &&
				this.amount == t.amount &&
				this.date.equals(t.date) &&
				this.category.equals(t.category) &&
				this.notes.equals(t.notes);
	}

}
