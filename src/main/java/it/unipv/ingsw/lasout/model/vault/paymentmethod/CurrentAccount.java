package it.unipv.ingsw.lasout.model.vault.paymentmethod;

import java.sql.ResultSet;
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
		CurrentAccount ccParam = (CurrentAccount) paymentmethod;

		DBQuery query = DatabaseUtil.getInstance().createQuery("DELETE FROM \\'currentaccount\\' WHERE id = ?", ccParam.getId());
		DatabaseUtil.getInstance().executeQuery(query);

		ResultSet rs = query.getResultSet();

		if (rs != null)
			throw new CantDeleteException("Can't delete account");

		query.close();
	}

	@Override
	public void save(PaymentMethod paymentmethod) throws Exception {
		CurrentAccount caParam = (CurrentAccount) paymentmethod;

		DBQuery query = DatabaseUtil.getInstance().createQuery("INSERT INTO \\'currentaccount\\' (iban, vault_id) VALUES (?, ?);",
				caParam.getIban(), caParam.getVault_id());

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
			int vault_id = rs.getInt("vault_id");

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

		CurrentAccount caParam = (CurrentAccount) paymentmethod;

		DBQuery query = DatabaseUtil.getInstance().createQuery("SELECT * FROM \\'currentaccount\\' WHERE id = ?;",
				caParam.getId());
		DatabaseUtil.getInstance().executeQuery(query);

		ResultSet rs = query.getResultSet();

		if (!rs.next())
			throw new NoResoultException("No resoult found");

		CurrentAccount ca = new CurrentAccount();

		ca.setIban(rs.getString("iban"));
		
		query.close();

		return ca;
	}
}
