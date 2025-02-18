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

public class PayPal implements PaymentMethod{
	
	private int id;
	private String numeroCarta;
	private int vault_id;

	public PayPal(int id, String numeroCarta, int vault_id) {
		this.id = id;
		this.numeroCarta = numeroCarta;
		this.vault_id = vault_id;
	}
	
	public PayPal() {
		
	}
	
	public PayPal(String numerocarta, int vault_id) {
		this.numeroCarta = numerocarta;
		this.vault_id = vault_id;
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
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
		return "PayPal";
	}
	
	@Override
	public boolean autorizza() {
		return true;
	}

	@Override
	public List<PaymentMethod> getAll(PaymentMethod paymentmethod) throws Exception {
		DBQuery query = DatabaseUtil.getInstance().createQuery("SELECT * FROM \\'paypal\\';");
		DatabaseUtil.getInstance().executeQuery(query);

		ResultSet rs = query.getResultSet();

		List<PaymentMethod> pp = new ArrayList<PaymentMethod>();

		if (rs == null) {
			System.out.println("Errore: ResultSet è null!");
			return pp; // Restituisco lista vuota
		}

		while (rs.next()) {

			PayPal paypal = new PayPal();

			int id = rs.getInt("id");
			String numerocarta = rs.getString("numerocarta");
			int vault_id = rs.getInt("vault_id");

			paypal.setId(id);
			paypal.setNumeroCarta(numerocarta);
			paypal.setVault_id(vault_id);

			pp.add(paypal);
		}
		return pp;
	}

	@Override
	public PaymentMethod get(PaymentMethod paymentmethod) throws Exception {
		

		DBQuery query = DatabaseUtil.getInstance().createQuery("SELECT * FROM \\'paypal\\' WHERE id = ?;",
				((PayPal)paymentmethod).getId());
		DatabaseUtil.getInstance().executeQuery(query);

		ResultSet rs = query.getResultSet();

		if (!rs.next())
			throw new NoResoultException("No resoult found");

		PayPal pp = new PayPal();

		pp.setNumeroCarta(rs.getString("numerocarta"));
		pp.setVault_id(rs.getInt("vault_id"));	
		
		query.close();

		return pp;	
	}

	@Override
	public void delete(PaymentMethod paymentmethod) throws Exception {
		DBQuery query = DatabaseUtil.getInstance().createQuery("DELETE FROM \\'currentaccount\\' WHERE id = ?", ((PayPal)paymentmethod).getId());
		DatabaseUtil.getInstance().executeQuery(query);

		ResultSet rs = query.getResultSet();

		if (rs != null)
			throw new CantDeleteException("Can't delete PayPal method");

		query.close();		
	}

	@Override
	public void save(PaymentMethod paymentmethod) throws Exception {

		DBQuery query = DatabaseUtil.getInstance().createQuery("INSERT INTO \\'paypal\\' (numerocarta, vault_id) VALUES (?, ?);",
				((PayPal)paymentmethod).getNumeroCarta(), ((PayPal)paymentmethod).getVault_id());

		DatabaseUtil.getInstance().executeQuery(query);

		ResultSet rs = query.getResultSet();

		if (rs != null)
			throw new CantSaveException("PayPal not saved");

		query.close();		
	}

	@Override
	public void setVault(Vault vault) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveInPaymentMethod(Vault v, PaymentMethod p) throws Exception {
		DBQuery query = DatabaseUtil.getInstance().createQuery("INSERT INTO \\'paymentmethod\\' (id_vault, type) "
				+ "VALUES (?, ?)", v.getId(), p.getMethodName());
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet rs = query.getResultSet();
		
		if(rs != null) throw new CantSaveException("Paymentmethod not saved");
		query.close();
	}
	
}
