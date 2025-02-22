package it.unipv.ingsw.lasout.model.vault.paymentmethod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.group.exception.CantDeleteException;
import it.unipv.ingsw.lasout.model.group.exception.CantSaveException;
import it.unipv.ingsw.lasout.model.group.exception.NoResoultException;
import it.unipv.ingsw.lasout.model.vault.Vault;

public class CurrentAccount implements PaymentMethod {

	private int id;
	private String iban;
	private int vault_id;

	public CurrentAccount(int id, String iban, int vault_id) {
		this.id = id;
		this.iban = iban;
		this.vault_id = vault_id;
	}

	public CurrentAccount(String iban, int vault_id) {
		 this.iban = iban;
		 this.vault_id = vault_id;
	}
	
	public CurrentAccount() {
		
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVault_id() {
		return vault_id;
	}

	public void setVault_id(int vault_id) {
		this.vault_id = vault_id;
	}

	@Override
	public String getMethodName() {

		return "CurrentAccount";

	}

	@Override
	public boolean autorizza() {

		return true;
	}

	@Override
	public void delete(PaymentMethod paymentmethod) throws Exception {

		DBQuery query = DatabaseUtil.getInstance().createQuery("DELETE FROM \\'currentaccount\\' WHERE id = ?", ((CurrentAccount)paymentmethod).getId());
		DatabaseUtil.getInstance().executeQuery(query);

		ResultSet rs = query.getResultSet();

		if (rs != null)
			throw new CantDeleteException("Can't delete account");

		query.close();
	}

	@Override
	public void save(PaymentMethod paymentmethod) throws Exception {

		DBQuery query = DatabaseUtil.getInstance().createQuery("INSERT INTO \\'currentaccount\\' (iban, id_vault) VALUES (?, ?);",
				((CurrentAccount)paymentmethod).getIban(), ((CurrentAccount)paymentmethod).getVault_id());

		DatabaseUtil.getInstance().executeQuery(query);

		ResultSet rs = query.getResultSet();

		if (rs != null)
			throw new CantSaveException("Current account not saved");

		query.close();
	}

	@Override
	public void setVault(Vault vault) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PaymentMethod> getAll(PaymentMethod paymentmethod) throws Exception {
		DBQuery query = DatabaseUtil.getInstance().createQuery("SELECT * FROM \\'currentaccount\\';");
		DatabaseUtil.getInstance().executeQuery(query);

		ResultSet rs = query.getResultSet();

		List<PaymentMethod> ca = new ArrayList<PaymentMethod>();

		if (rs == null) {
			System.out.println("Errore: ResultSet è null!");
			return ca; // Restituisco lista vuota
		}

		while (rs.next()) {

			CurrentAccount currentaccount = new CurrentAccount();

			int id = rs.getInt("id");
			String iban = rs.getString("iban");
			int vault_id = rs.getInt("id_vault");

			currentaccount.setId(id);
			currentaccount.setIban(iban);
			currentaccount.setVault_id(vault_id);

			ca.add(currentaccount);
		}
		
		query.close();
		return ca;
	}

	@Override
	public PaymentMethod get(PaymentMethod paymentmethod) throws Exception {

		DBQuery query = DatabaseUtil.getInstance().createQuery("SELECT * FROM \\'currentaccount\\' WHERE id = ?;",
				((CurrentAccount)paymentmethod).getId());
		DatabaseUtil.getInstance().executeQuery(query);

		ResultSet rs = query.getResultSet();

		if (!rs.next())
			throw new NoResoultException("No resoult found");

		CurrentAccount ca = new CurrentAccount();

		ca.setIban(rs.getString("iban"));
		
		query.close();

		return ca;
	}

	@Override
	public void saveInPaymentMethod(Vault v, PaymentMethod p) throws Exception {
		DBQuery query = DatabaseUtil.getInstance().createQuery("INSERT INTO \\'paymentmethod\\' (id_vault, type, id_paymentmethod) "
				+ "VALUES (?, ?, ?)", v.getId(), p.getMethodName(), ((CurrentAccount)p).getId());
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet rs = query.getResultSet();
		
		if(rs != null) throw new CantSaveException("Paymentmethod not saved");
		query.close();
		
	}

	@Override
	public void getId(PaymentMethod p) throws Exception {
		DBQuery query = DatabaseUtil.getInstance().createQuery("SELECT id FROM \\'currentaccount\\' where iban = ?", ((CurrentAccount) p).getIban());
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		if (result == null) {
	        System.out.println("Errore: ResultSet è null!");
	    }
		
		if (!result.next()) {
		    System.out.println("Nessun record trovato ");
		    throw new SQLException("Nessun record trovato");
		}
		
		int id = result.getInt("id");
		
		((CurrentAccount) p).setId(id);
		
		query.close();
	}

	@Override
	public void deleteInPaymentMethod(PaymentMethod p) throws Exception{
		DBQuery query = DatabaseUtil.getInstance().createQuery("DELETE FROM \\'paymentmethod\\' WHERE id_paymentmethod = ? AND type = ?", ((CurrentAccount)p).getId(), p.getMethodName());
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		if (result != null) throw new CantDeleteException("Can't delete card");
		
		query.close();
		
	}

}
