package it.unipv.ingsw.lasout.model.vault;

public class Transaction {
	
	private int id;
	private double importo;
	private String descrizione;
	
	public Transaction(int id, double importo, String descrizione) {
		
		this.id = id;
		this.importo = importo;
		this.descrizione = descrizione;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
}
