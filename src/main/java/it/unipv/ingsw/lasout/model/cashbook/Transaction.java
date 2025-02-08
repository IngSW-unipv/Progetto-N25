package it.unipv.ingsw.lasout.model.cashbook;

public class Transaction {
	
	private int id;
	private double amount;
	private int senderID;
	private int receiverID;
	private int type;
	private String notes;

	public Transaction() {

	}

	public Transaction(int id) {
		this.id = id;
	}

	public Transaction(int id, double amount) {
		this.id = id;
		this.amount = amount;
	}

	public Transaction(int id, double amount, int senderID, int receiverID) {
		this.id = id;
		this.amount = amount;
		this.senderID = senderID;
		this.receiverID = receiverID;
	}

	public Transaction(int id, double amount, int senderID, int receiverID, int type, String notes) {
		this.id = id;
		this.amount = amount;
		this.senderID = senderID;
		this.receiverID = receiverID;
		this.type = type;
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

	public int getSenderID() {
		return senderID;
	}

	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}

	public int getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(int receiverID) {
		this.receiverID = receiverID;
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
