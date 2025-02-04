package it.unipv.ingsw.lasout.model.vault;

public class RegisteredUser implements User{
	private String nome;
	private String cognome;
    private String email;

	public RegisteredUser(String nome, String cognome, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public String getCognome() {
		return cognome;
	}

	@Override
	public String getEmail() {
		return email;
	}
}
