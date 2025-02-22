package it.unipv.ingsw.lasout.model.vault.paymentmethod;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.group.exception.CantSaveException;
import it.unipv.ingsw.lasout.model.vault.Vault;

public class PaymentMethodDAO implements IPaymentMethodDAO {

	private static final Logger LOGGER = Logger.getLogger(PaymentMethodDAO.class.getName());

	private static final PaymentMethodDAO INSTANCE = new PaymentMethodDAO();

	public static PaymentMethodDAO getInstance() {
		return INSTANCE;
	}
	
	private static final String GET_RAW = "SELECT * FROM \\'paymentmethod\\' WHERE id = ?;";
	private static final String GET_PAYMENTMETHOD_BY_VAULT_ID = "SELECT * FROM \\'paymentmethod\\' WHERE id_vault = ?;";
	

	private final Map<String, Class<?>> classes = new HashMap<>();

	private PaymentMethodDAO() {
		populateMap();
	}

	private void populateMap() {
		Properties prop = new Properties();

		try {
			prop.load(PaymentMethodDAO.class.getResourceAsStream("/methodpaymentfactories.properties"));
		} catch (IOException e) {
			LOGGER.severe("Could not load the file");
		}

		prop.forEach((key, value) -> {
			try {
				classes.put((String) key, Class.forName(value.toString()));
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		});
	}
	
public List<PaymentMethod> getAllPaymentMethod(Vault v) throws Exception {
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(GET_PAYMENTMETHOD_BY_VAULT_ID, v.getId());
		DatabaseUtil.getInstance().executeQuery(query);
		
		if (query == null) {
	        throw new RuntimeException("Errore: la query è NULL!");
	    }

		ResultSet result = query.getResultSet();
		
		PaymentMethodFactory factory = new PaymentMethodFactory();
		
		List<PaymentMethod> methods = new ArrayList<PaymentMethod>();
		
		if (result == null) {
	        System.out.println("Errore: ResultSet è null!");
	        return methods; // Restituisco lista vuota
	    }
		
		while (result.next()) {
			
			PaymentMethod method = factory.get(result.getString("type"));
			
		    method.setId(result.getInt("id"));
			
			method.setVault(new Vault(result.getInt("id_vault")));
			
			methods.add(method);
	    }	
		
		return methods;
	}
	
	

	@Override
	public PaymentMethod getRaw(PaymentMethod oggetto) throws Exception {
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(GET_RAW);
		DatabaseUtil.getInstance().executeQuery(query);

		ResultSet result = query.getResultSet();
		
		PaymentMethodFactory factory = new PaymentMethodFactory();
		
		PaymentMethod method = factory.get(result.getString("tipo"));
		
		method.setVault(new Vault(result.getInt("vault_id")));
		
		return method;
	}

	@Override
	public PaymentMethod get(PaymentMethod oggetto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentMethod> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(PaymentMethod t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(PaymentMethod t) throws Exception {	
	}

	@Override
	public void delete(PaymentMethod t) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
