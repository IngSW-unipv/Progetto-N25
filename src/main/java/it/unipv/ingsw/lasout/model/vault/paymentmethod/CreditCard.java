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

public class CreditCard implements PaymentMethod {

	private int id;
	private String numeroCarta;
	private int mese;
	private int anno;
	private int cvv;
	private int id_vault;

	public CreditCard(int id, String numeroCarta, int mese, int anno, int cvv, int id_vault) {
		this.id = id;
		this.numeroCarta = numeroCarta;
		this.mese = mese;
		this.anno = anno;
		this.cvv = cvv;
		this.id_vault = id_vault;
	}
	
	public CreditCard(String numeroCarta, int mese, int anno, int cvv, int id_vault) {
		this.numeroCarta = numeroCarta;
		this.mese = mese;
		this.anno = anno;
		this.cvv = cvv;
		this.id_vault = id_vault;
	}

	public CreditCard() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public int getMese() {
		return mese;
	}

	public void setMese(int mese) {
		this.mese = mese;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public int getId_vault() {
		return id_vault;
	}

	public void setId_vault(int id_vault) {
		this.id_vault = id_vault;
	}

	@Override
	public String getMethodName() {

		return "CreditCard";
	}

	@Override
	public boolean autorizza() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PaymentMethod> getAll(PaymentMethod paymentmethod) throws Exception {

		DBQuery query = DatabaseUtil.getInstance().createQuery("SELECT * FROM \\'creditcard\\';");
		DatabaseUtil.getInstance().executeQuery(query);

		ResultSet rs = query.getResultSet();

		List<PaymentMethod> cc = new ArrayList<PaymentMethod>();

		if (rs == null) {
			System.out.println("Errore: ResultSet è null!");
			return cc; // Restituisco lista vuota
		}

		while (rs.next()) {

			CreditCard creditcard = new CreditCard();

			int id = rs.getInt("id");
			String numcard = rs.getString("numerocarta");
			int mese = rs.getInt("mese");
			int anno = rs.getInt("anno");
			int cvv = rs.getInt("cvv");
			int id_vault= rs.getInt("vault_id");
			
			creditcard.setId(id);
			creditcard.setNumeroCarta(numcard);
			creditcard.setMese(mese);
			creditcard.setAnno(anno);
			creditcard.setCvv(cvv);
			creditcard.setId_vault(id_vault);;
			
			
			cc.add(creditcard);
		}
		
		query.close();

		return cc;
	}

	@Override
	public PaymentMethod get(PaymentMethod paymentmethod) throws Exception {
		
		CreditCard ccParam = (CreditCard) paymentmethod;

		DBQuery query = DatabaseUtil.getInstance().createQuery("SELECT * FROM \\'creditcard\\' WHERE id = ?;",
				ccParam.getId());
		DatabaseUtil.getInstance().executeQuery(query);

		ResultSet rs = query.getResultSet();

		if (!rs.next()) throw new NoResoultException("No resoult found");

		CreditCard cc = new CreditCard();
		
		cc.setNumeroCarta(rs.getString("numerocarta"));
		cc.setMese(rs.getInt("mese"));
		cc.setAnno(rs.getInt("anno"));
		cc.setCvv(rs.getInt("cvv"));	
		cc.setId_vault(rs.getInt("vault_id"));

		query.close();
		return cc;

	}

	@Override
	public void delete(PaymentMethod paymentmethod) throws Exception {
		CreditCard ccParam = (CreditCard) paymentmethod;
		
		DBQuery query = DatabaseUtil.getInstance().createQuery("DELETE FROM \\'creditcard\\' WHERE id = ?", ccParam.getId());
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet rs = query.getResultSet();
		
		if (rs != null) throw new CantDeleteException("Can't delete card");
		
		query.close();
	}

	@Override
	public void save(PaymentMethod paymentmethod) throws Exception {
		
//		CreditCard ccParam = (CreditCard) paymentmethod;

		DBQuery query = DatabaseUtil.getInstance().createQuery("INSERT INTO \\'creditcard\\' (numerocarta, mese, anno, cvv, vault_id) VALUES\r\n"
				+ "	(?, ?, ?, ?, ?);", ((CreditCard) paymentmethod).getNumeroCarta(), ((CreditCard) paymentmethod).getMese(), ((CreditCard) paymentmethod).getAnno(), ((CreditCard) paymentmethod).getCvv(), ((CreditCard) paymentmethod).getId_vault());
		System.out.println("" + ((CreditCard) paymentmethod).getNumeroCarta());
		
		DatabaseUtil.getInstance().executeQuery(query);

		ResultSet rs = query.getResultSet();

		if (rs != null) throw new CantSaveException("Card not saved");
		
		query.close();
	}

	@Override
	public void setVault(Vault vault) {
		// TODO Auto-generated method stub

	}

}
